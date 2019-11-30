import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1
//        3 3
//        -2 -3 3 -5 -10 1 10 30 -5

/**最小化初始点
 * Given a grid with each cell consisting of positive, negative or no points i.e, zero points.
 * We can move across a cell only if we have positive points ( > 0 ).
 * Whenever we pass through a cell, points in that cell are added to our overall points.
 * We need to find minimum initial points to reach cell (m-1, n-1) from (0, 0) by following these certain set of rules :
 * 1.From a cell (i, j) we can move to (i+1, j) or (i, j+1).
 * 2.We cannot move from (i, j) if your overall points at (i, j) is <= 0.
 * 3.We have to reach at (n-1, m-1) with minimum positive points i.e., > 0.
 *
 * 2.3两点的意思是进入位置i,j和从位置i,j出来的时候最小值都为1
 * 用dp[i][j]表示进入位置i,j时的值  dp = new int[rows][columns]
 * exit_ij = min(dp[i+1][j],dp[i][j+1])
 * dp[i][j]=max( exit_ij-arr[i][j] , 1 )
 * 先计算最下面一行和最右边一列，再计算中间的（由下而上，由右而左）
 */
public class C4B_MiniInitialPoints {
    private static final int MIN_MOVE_POINTS = 1;//因为只有点数>0,才能从上一个位置出来，所以到达一个位置时的最小点数是1
    private static void solution(int[][] arr,int r,int c){
        int[][] dp = new int[r][c];//dp[i][j]表示到达位置i,j时的点数
        int endOutPoints = 1;
        dp[r-1][c-1] = (arr[r-1][c-1]<endOutPoints)?endOutPoints-arr[r-1][c-1]:1;
        for (int i = r-2; i >=0 ; i--) {
            int outPoints = dp[i+1][c-1];
            //如果这个位置的点数比输出点数小，说明要啃老；否则，可以理解为输出的都是自己产的点数
            int inPoints = arr[i][c-1]<outPoints?outPoints-arr[i][c-1]:1;
            dp[i][c-1] = inPoints;
        }
        for (int i = c-2; i >=0 ; i--) {
            dp[r-1][i] = dp[r-1][i+1]-arr[r-1][i]>0?dp[r-1][i+1]-arr[r-1][i]:1;
        }
        for (int i = r-2; i >=0 ; i--) {
            for (int j = c-2; j >=0 ; j--) {
                int outPoints = Math.min(dp[i+1][j],dp[i][j+1]);
                int inPoints = arr[i][j]<outPoints?outPoints-arr[i][j]:1;
                dp[i][j] = inPoints;
            }
        }
        System.out.println(dp[0][0]);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t ; i++) {
                String[] line1 = reader.readLine().split(" ");
                int r = Integer.parseInt(line1[0]);
                int c = Integer.parseInt(line1[1]);
                String[] line2 = reader.readLine().split(" ");
                int[][] data = new int[r][c];
                int j=0;
                for(int rp=0;rp<r;rp++){
//                    StringBuilder stringBuilder = new StringBuilder();
                    for (int cp = 0; cp < c; cp++) {
                        data[rp][cp] = Integer.parseInt(line2[j++]);
//                        stringBuilder.append(data[rp][cp]+" ");
                    }
//                    System.out.println(stringBuilder.toString());
                }
                solution(data,r,c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
