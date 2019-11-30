import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**牛的繁殖问题
 *Cows in the FooLand city are interesting animals. One of their specialties is related to producing offsprings.
 *  A cow in FooLand produces its first calve (female calf) at the age of two years and proceeds to
 *  produce other calves (one female calf a year).
 *
 * Now the farmer Harold wants to know how many animals would he have at the end of N years, if we assume that none of
 * the calves die, given that initially, he has only one female calf?
 *
 * explanation:At the end of 1 year, he will have only 1 cow, at the end of 2 years he will
 * have 2 animals (one parent cow C1 and other baby calf B1 which is the offspring of cow C1).
 * At the end of 3 years, he will have 3 animals (one parent cow C1 and 2 female calves B1 and B2,
 * C1 is the parent of B1 and B2).At the end of 4 years, he will have 5 animals (one parent cow C1,
 * 3 offsprings of C1 i.e. B1, B2, B3 and one offspring of B1).
 *
 * fn = f(n-1)+f(n-2) todo
 * 变化公式：这个是严格按照斐波那契数列来：f(2n+1)=f(n)^2+f(n+1)^2      f(2n) = 2*f(n)*f(n+1) - f(n)*f(n)
 * 斐波那契数列是 1，1，2，3，5，8……
 * 这题是1，2，3，5，8……
 * 所以用变化公式时的第i项是斐波那契数列的第i+1项
 */
public class R_CowProduce {
    private static Map<Long,Long> fsMap = new HashMap<>();
    private static long solution(long n){
        if(n==0)return 0;
        if(n==1)return 1;
        if(n==2)return 1;
        if(fsMap.containsKey(n)){
            return fsMap.get(n);
        }else {
            long fn;
            long k = n/2;
            if(n%2==1){
                fn =  (solution(k)*solution(k)+solution(k+1)*solution(k+1))%1000000007;
            }else {
                fn  =(2*solution(k+1)*solution(k)-solution(k)*solution(k))%1000000007;
            }
            fsMap.put(n,fn);
            return fn;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            System.out.println(solution(scanner.nextLong()+1));
        }
    }
}
