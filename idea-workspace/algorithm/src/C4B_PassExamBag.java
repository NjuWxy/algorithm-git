import java.util.Scanner;

/**能否能通过考试
 * 考试时长为h（背包容量），共n个题目（物品），需要拿到p分才能通过考试，给出了n个题目（物品）的得分(v价值)和耗时(w重量)
 * n个，背包容量为h,dp = new int[n+1][h+1]
 * dp[i][j]表示时长为j里前i个题目的最大价值（最有价值的子集总价值）
 * dp[i][j] = dp[i-1][j]  (wi>j)
 *            max(dp[i-1][j],dp[i-1][j-wi]+vi)  (wi<=j)
 * 1<=i<=n,1<=j<=h，两层循环计算dp
 * 找dp[n][0-h]里面最左边出现的>=p的位置
 */
public class C4B_PassExamBag {
    private static void solution(int[][] tasks,int n,int h,int p){
        int[][] dp = new int[n+1][h+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=h ; j++) {
                if(tasks[i-1][0]>j){
                    dp[i][j] = dp[i-1][j];
                }else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-tasks[i-1][0]]+tasks[i-1][1]);
                }
                if(i==n && dp[i][j]>=p){
                    System.out.println("YES "+j);
                    return;
                }
            }

        }
//        print(dp);
        System.out.println("NO");
    }

    private static void print(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t --> 0) {
            int n = scanner.nextInt();
            int h = scanner.nextInt();
            int p = scanner.nextInt();
            int[][] tasks = new int[n][2];
            for (int i = 0; i < n; i++) {
                tasks[i][0] = scanner.nextInt();
                tasks[i][1] = scanner.nextInt();
            }
            solution(tasks,n, h, p);
        }
    }
}
