import java.util.Scanner;

/**序号乘方
 * There are Infinite People Standing in a row, indexed from 1.A person having index 'i' has strength of (i*i).
 * You have Strength 'P'. You need to tell what is the maximum number of People You can Kill With your Strength P.
 * You can only Kill a person with strength 'X' if P >= 'X' and after killing him, Your Strength decreases by 'X'.
 * 就是计算1^2+2^2+3^2+……+i^2<=p,求i的最大值
 */
public class C2B_IndexPow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for(int i=0;i<cases;i++){
            long p = scanner.nextLong();
            long k=1;
            p = p - k*k;
            int count = 0;
            while (p>=0){
                count++;
                k++;
                p = p - k*k;
            }
            System.out.println(count);
        }
    }
}
