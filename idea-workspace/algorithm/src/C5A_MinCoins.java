import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**硬币最少数量
 * Given the list of coins of distinct denominations and total amount of money.
 * Output the minimum number of coins required to make up that amount. Output -1 if
 * that money cannot be made up using given coins. You may assume that there are infinite numbers of coins of each type.
 *
 *
 * Input
 */
public class C5A_MinCoins {
    private static void solution(int[] coins,int totalMoney){
        System.out.println(compute(coins,coins.length-1,totalMoney,0));
    }
    private static int compute(int[] coins,int start,int totalMoney,int countAllReady){
        int i = start;
        int total = totalMoney;
        int count = countAllReady;
        while (i>=0&&coins[i]>totalMoney){
            i--;
        }
        if(i<0){
            return -1;
        }else {
            total-=coins[i];
            count++;
            if(total==0){
                return count;
            }else {
                int res = compute(coins,i,total,count);
                while (i>0 && res==-1){
                    i--;
                    res = compute(coins,i,total,count);
                }
                return res;
            }
        }
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
