import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**订单问题
 * Rahul and Ankit are the only two waiters in Royal Restaurant. Today, the restaurant received N orders.
 * The amount of tips may differ when handled by different waiters, if Rahul takes the ith order, he would be tipped Ai
 * rupees and if Ankit takes this order, the tip would be Bi rupees.In order to maximize the total tip value they decided
 * to distribute the order among themselves. One order will be handled by one person only. Also, due to time constraints
 * Rahul cannot take more than X orders and Ankit cannot take more than Y orders. It is guaranteed that X + Y is greater
 * than or equal to N, which means that all the orders can be handled by either Rahul or Ankit. Find out the maximum possible
 * amount of total tip money after processing all the orders.
 *
 * dp[i][j]表示a分了i个订单b分了j个订单的情况下可以获得的最大消费
 * 先初始化第一行和第一列，然后
 * dp[i][j] = max( dp[i-1][j]+a[i+j]  ,   dp[i][j-1]+b[i+j]   )   i+j<=n
 *            max( dp[i-1][j]         ,   dp[i][j-1]          )   i+j>n
 */
public class C4A_OrderProblem {
    private static void solution(int[] a, int[] b,int n,int x,int y){
        int[][] dp = new int[x+1][y+1];
        for (int i = 1; i <=y ; i++) {
            dp[0][i] = dp[0][i-1]+ b[i];
        }
        for (int i = 1; i <=x ; i++) {
            dp[i][0] = dp[i-1][0]+a[i];
        }
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <=x ; i++) {
            for (int j = 1; j <=y ; j++) {
                if(i+j>n){
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);//!!!
                }else {
                    dp[i][j] = Math.max(dp[i-1][j]+a[i+j],dp[i][j-1]+b[i+j]);
                }
                max = Math.max(max,dp[i][j]);
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line1 = reader.readLine().split(" ");
                int n = Integer.parseInt(line1[0]);
                int x = Integer.parseInt(line1[1]);
                int y = Integer.parseInt(line1[2]);
                String[] line2 = reader.readLine().split(" ");
                int[] a = new int[n+1];
                for (int i = 0; i < n ; i++) {
                    a[i+1] = Integer.parseInt(line2[i]);
                }
                String[] line3 = reader.readLine().split(" ");
                int[] b = new int[n+1];
                for (int i = 0; i < n ; i++) {
                    b[i+1] = Integer.parseInt(line3[i]);
                }
                solution(a,b,n,x,y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
