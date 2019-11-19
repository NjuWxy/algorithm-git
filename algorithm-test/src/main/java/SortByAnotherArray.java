import java.util.*;

public class SortByAnotherArray {
    private static void solution(Integer[] arr, final List<Integer> anotherArr){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int o1Index = anotherArr.indexOf(o1);
                int o2Index = anotherArr.indexOf(o2);
                if(!contains(o1Index) && !contains(o2Index)){
                    return o1-o2;
                }else if(contains(o1Index) && contains(o2Index)){
                    return o1Index - o2Index;
                }else if(contains(o1Index) && !contains(o2Index)){
                    return -1;
                }else {
                    return 1;
                }
            }
        };
        Arrays.sort(arr,comparator);
        print(arr);
        
    }

    private static boolean contains(int idx){
        return idx!=-1;
    }
    private static void print(Integer[] arr){
        if(arr.length==0)return;
        for (int i = 0; i < arr.length-1; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(arr[arr.length-1]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int len = scanner.nextInt();
            int anotherLen = scanner.nextInt();
            Integer[] arr = new Integer[len];
            List<Integer> anotherArr = new ArrayList<Integer>();
            for (int j = 0; j < len; j++) {
                arr[j] = scanner.nextInt();
            }
            for (int j = 0; j < anotherLen; j++) {
                anotherArr.add(scanner.nextInt());
            }
            solution(arr,anotherArr);
        }
    }
}
