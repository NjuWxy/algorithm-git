import java.util.Arrays;
import java.util.Scanner;

/**素数和问题
 *Given an even number ( greater than 2 ), return two prime numbers whose sum will be equal to given number. There are several combinations possible. Print only first such pair.
 *NOTE: A solution will always exist, read Goldbach’s conjecture.Also, solve the problem in linear time complexity, i.e., O(n).
 * 1. prime = new int[n+1]求素数数组
 * 2. 0<=i<=n, 判断i和n-i是不是都是素数，是的话就是结果
 */
public class C3B_PrimeSum {
    private static boolean[] getPrime(int n){
        boolean[] isPrime = new boolean[n+1];
        Arrays.fill(isPrime,true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i=2;i*i <= n;i++){
            if(isPrime[i]){
                for(int j=i*i;j<=n;j+=i){
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }
    private static void solution(int n){
        boolean[] isPrime = getPrime(n);
        for(int i=0;i<n;i++){
            if(isPrime[i]&&isPrime[n-i]){
                System.out.println(i + " " + (n - i));
                return;
            }
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for(int i=0;i<cases;i++){
            int n = scanner.nextInt();
            solution(n);
        }
    }
}
