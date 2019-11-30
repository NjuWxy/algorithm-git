import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**硬币最少数量
 * Given the list of coins of distinct denominations and total amount of money.
 * Output the minimum number of coins required to make up that amount. Output -1 if
 * that money cannot be made up using given coins. You may assume that there are infinite numbers of coins of each type.
 *
 *
 * dp[i]表示形成i需要的最少硬币数,初始化为Integer.MAX_VALUE
 * dp[i] = min ( dp[i-coins[j]]+1)， j from coins.length-1 to 0 ,j从中挑dp[i-coins[j]] != Integer.MAX_VALUE的
 */
public class C5B_MinCoinsDp {
    private static void solution(int[] coins, int totalMoney){
        Arrays.sort(coins);
        int[] dp = new int[totalMoney+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <=totalMoney ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = coins.length-1; j >=0 ; j--) {
                if(i>=coins[j] && dp[i-coins[j]] != Integer.MAX_VALUE){//!!!
                    min = Math.min(dp[i-coins[j]]+1,min);
                }

            }
            if (min != Integer.MAX_VALUE) {
                dp[i] = min;
            }
        }
        System.out.println(dp[totalMoney]==Integer.MAX_VALUE?-1:dp[totalMoney]);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line1 = reader.readLine().split(" ");
                int n = Integer.parseInt(line1[0]);
                int totalMoney = Integer.parseInt(line1[1]);
                String[] line2 = reader.readLine().split(" ");
                int[] coins = new int[line2.length];
                for (int i = 0; i < n ; i++) {
                    coins[i] = Integer.parseInt(line2[i]);
                }
                solution(coins,totalMoney);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
