import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**汉诺塔
 * 汉诺塔问题中限制不能将一层塔直接从最左侧移动到最右侧，也不能直接从最右侧移动到最左侧，而是必须经过中间。求当有N层塔的时候移动步数。
 * f(n) = 3*f(n-1)+1
 * g(n) = 2 * f(n)
 */
public class P1_HanNuoTa {
    private static BigInteger solution(long n){
        BigInteger res = new BigInteger("0");
        for (long i = 1; i <=n ; i++) {
            //todo 手写乘法
            res = res.multiply(new BigInteger("3")).add(new BigInteger("1"));
        }
        return res.multiply(new BigInteger("2"));
    }
    private static long solution2(long n){
        if(n==0)return 0;
        int res = 0;
        long p = 1;
        while (p<=n){
            res = res*3+2;
            p++;
        }

        return res;
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                long n = Long.parseLong(reader.readLine());
                System.out.println(solution(n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
