import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**格子里的整数
 * Given a square grid of size n, each cell of which contains integer cost which represents a cost to traverse through that cell,
 * we need to find a path from top left cell to bottom right cell by which total cost incurred is minimum.
 *
 * Note : It is assumed that negative cost cycles do not exist in input matrix.
 */
public class C5B_GridInteger {
    private static void solution(int[][] grid,int n){
        int[][] dp = new int[n][n];
        dp[n-1][n-1] = grid[n-1][n-1];
        for (int i = n-2; i >= 0 ; i--) {
            dp[n-1][i] = grid[n-1][i]+dp[n-1][i+1];
            dp[i][n-1] = grid[i][n-1]+dp[i+1][n-1];
        }
        for (int i = n-2; i >=0 ; i--) {
            for (int j = n-2; j >=0 ; j--) {
                dp[i][j] = Math.min(dp[i+1][j],dp[i][j+1])+grid[i][j];
            }
        }
//        print(grid);
        System.out.println(dp[0][0]);

//        print(dp);
    }
    private static void print(int[][] arr){
        for (int i = 0; i < arr.length ; i++) {
            for (int j = 0; j <arr[i].length ; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                String[] line2 = reader.readLine().split(" ");
                int[][] grid = new int[n][n];
                for (int i = 0; i < line2.length ; i++) {
                    grid[i/n][i%n] = Integer.parseInt(line2[i]);
                }
//                System.out.println();
                solution(grid,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
