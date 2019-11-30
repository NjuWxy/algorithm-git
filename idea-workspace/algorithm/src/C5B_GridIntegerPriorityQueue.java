import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

/**格子里的整数
 * Given a square grid of size n, each cell of which contains integer cost which represents a cost to traverse through that cell,
 * we need to find a path from top left cell to bottom right cell by which total cost incurred is minimum.
 *
 * Note : It is assumed that negative cost cycles do not exist in input matrix.
 *
 * accumuCosts = new int[n][n]记录每个点穿过这个点需要的最小累计cost, accumuCosts[0][0]初始化为grid[0][0]
 * visited = new boolean[n][n] 记录每一个点accumuCost是否被更新过
 * 用一个优先级队列记录下一次优先选择的点（即被加进优先级队列的所有的点里面累计cost最小的点，看它还能往哪里走）
 * 每次从优先级队列中取出一个点，更新它周围的没有访问过的点的cost，并设为已访问，分别加入优先级队列，队列为空或者当前取出的点为(n-1,n-1)时跳出循环
 */
public class C5B_GridIntegerPriorityQueue {
    static class Pos implements Comparable<Pos>{
        int x,y,accuCost;
        public Pos(int x,int y,int accuCost){
            this.x = x;
            this.y = y;
            this.accuCost = accuCost;
        }

        @Override
        public int compareTo(Pos o) {
            return this.accuCost-o.accuCost;
        }
    }
    private static void solution(int[][] grid,int n){
        boolean[][] visited = new boolean[n][n];
        int[][] accumuCosts = new int[n][n];

        accumuCosts[0][0] = grid[0][0];
        visited[0][0] = true;
        PriorityQueue<Pos> queue = new PriorityQueue<>();
        queue.add(new Pos(0,0,accumuCosts[0][0]));

        int[] px = new int[]{-1,0,1,0};
        int[] py = new int[]{0,-1,0,1};

        Pos p;
        while (!queue.isEmpty()){
            p = queue.poll();

            if(p.x==n-1 && p.y==n-1)break;

            for (int j = 0; j <py.length ; j++) {
                int newPx = p.x + px[j];
                int newPy = p.y + py[j];

                if(isValidPos(newPx,newPy,n) && !visited[newPx][newPy]){
                    accumuCosts[newPx][newPy] = accumuCosts[p.x][p.y] + grid[newPx][newPy];
                    visited[newPx][newPy] = true;
                    queue.add(new Pos(newPx,newPy,accumuCosts[newPx][newPy]));
                }
            }
        }

        System.out.println(accumuCosts[n-1][n-1]);

    }
    private static boolean isValidPos(int x,int y,int n){
        return x>=0 && x<n && y>=0 && y<n;
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
