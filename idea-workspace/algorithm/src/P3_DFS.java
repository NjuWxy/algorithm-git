import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**深度优先遍历
 * 按照给定的起始顶点深度优先遍历给定的无向图，尝试所有可能的遍历方式，打印遍历过程中出现的最大深度。
 * int dfs(int[][] graph, int node, List<Integer> path)  node为父节点，path为当前遍历到的节点  返回最大深度度
 *      首先把父节点加进当前路径中
 *      对于父节点的每一个不包含在当前路径的子节点，都调用一下dfs计算走这个子节点得到的路径长度
 *      对得到的子节点的路径长度求一个最大长度返回
 */
public class P3_DFS {
    private static void solution(int[][] graph,int begin){
        List<Integer> path = new ArrayList<>();
        System.out.println(dfs(graph,begin,path));
    }
    private static int dfs(int[][] graph, int node, List<Integer> path){
        path.add(node);
        int[] sameLevel = graph[node];
        int maxLen = path.size();
        for (int i = 0; i < sameLevel.length; i++) {
            if(sameLevel[i]==1 && !path.contains(i)){
               List<Integer> newPath = new ArrayList<>(path);
               int len = dfs(graph,i,newPath);
               maxLen = Math.max(maxLen,len);
            }
        }
        return maxLen;
    }
    private static void print(List<Integer> path){
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i)+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line = reader.readLine().split(" ");
                int nodeNum = Integer.parseInt(line[0]);
                String beginNode = line[1];
                String[] line2 = reader.readLine().split(" ");
                Map<String,Integer> map = new HashMap<>();
                for (int i = 0; i < line2.length ; i++) {
                    map.put(line2[i],0);
                }
                int[][] graph = new int[line2.length][line2.length];
                for (int i = 0; i < nodeNum; i++) {
                    String[] line3 = reader.readLine().split(" ");
                    map.put(line3[0],i);
                    for (int j = 1; j < line3.length ; j++) {
                        graph[i][j-1] = Integer.parseInt(line3[j]);
                    }
                }
                solution(graph,map.get(beginNode));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
