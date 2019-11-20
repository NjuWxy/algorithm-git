import java.util.Scanner;

class InfiniteRecursiveStringQuery {
    public static void main (String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1;i<100;i++){
            stringBuilder.append(getChar(i));
        }
        System.out.println(stringBuilder.toString());
//        Scanner in = new Scanner(System.in);
//        int t = in.nextInt();
//        while (t-- > 0) {
//            long pos = in.nextLong();
//            if (pos == 0) {
//                System.out.println("");
//                continue;
//            }
//        }
    }

    private static char getChar(long pos){
        char res;
        final String PAT = "12345$54321";
        while (pos > PAT.length()) {
            long[] iter = findIter(pos);
            long start = (iter[0]+iter[1]) / 2;
            pos -= start;
        }
        if (pos <= 0) {
            res = '$';
        } else {
            res = PAT.charAt((int)pos-1);
        }
        return res;
    }

    private static long[] findIter(long pos) {
        long len = 5;
        int it = 0;
        while (pos > len) {
            it++;
            len = 2*len + it;
        }
        return new long[] {len, it};
    }
}
