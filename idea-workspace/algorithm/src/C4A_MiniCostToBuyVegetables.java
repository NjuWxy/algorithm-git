import java.util.Arrays;
import java.util.Scanner;

/**如何花最少的钱购买蔬菜
 * Rahul wanted to purchase vegetables mainly brinjal, carrot and tomato.
 * There are N different vegetable sellers in a line. Each vegetable seller sells all three vegetable items,
 * but at different prices. Rahul, obsessed by his nature to spend optimally, decided not to purchase same vegetable
 * from adjacent shops. Also, Rahul will purchase exactly one type of vegetable item (only 1 kg) from one shop.
 * Rahul wishes to spend minimum money buying vegetables using this strategy. Help Rahul determine the minimum money he will spend.
 *
 * dp[i][j]表示在第i个商店买了第j种蔬菜时花的最少的钱
 * dp[i][j] = min( dp[i-1][(j+1)%3] , dp[i-1][(j+2)%3] )+a[i][j]
 */
public class C4A_MiniCostToBuyVegetables {
    private static void solution(int[][] a,int n){
        int[][] dp = new int[n][3];
        for (int i = 0; i < 3 ; i++) {
            dp[0][i] = a[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3 ; j++) {
                dp[i][j] = Math.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]) + a[i][j];
            }
        }
        int min = Math.min(dp[n-1][0],dp[n-1][1]);
        min = Math.min(dp[n-1][2],min);
        System.out.println(min);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n --> 0) {
            int m = scanner.nextInt();
            int[][] price = new int[m][3];
            scanner.nextLine();
            for (int i = 0; i < m; i++) {
                price[i] = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            }
            solution(price,price.length);
        }
    }

}
