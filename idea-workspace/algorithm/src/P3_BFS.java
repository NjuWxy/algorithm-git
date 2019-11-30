import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**广度优先遍历
 * 按照给定的起始顶点广度优先遍历图，每一次通过字母顺序选择顶点查找下一层邻接点，打印遍历顺序。
 * 利用队列记录需要遍历的节点，用path记录已经形成的路径，先把给定节点加进队列
 * 每次从队首取出一个节点，把它加进路径中，往下遍历其子节点，依次把不在路径中且不在队列中的节点加进队列
 * 直至队列为空
 */
public class P3_BFS {
    //todo 按照字母顺序选择顶点找下一层邻接顶点？
    private static void bfs(int[][] graph, int begin, LinkedList<Integer> queue, List<Integer> path,Map<Integer,String> idxToName){
        queue.add(begin);
        while (!queue.isEmpty()){
            int node = queue.pollFirst();
            path.add(node);
            for (int i = 0; i < graph[node].length; i++) {
                if(graph[node][i]==1 && !path.contains(i) && !queue.contains(i)){
                    queue.addLast(i);
                }
            }
        }
        for (int i = 0; i < path.size() ; i++) {
            System.out.print(idxToName.get(path.get(i)));
            if(i<path.size()-1){
                System.out.print(" ");
            }else {
                System.out.println();
            }
        }
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
                Map<String,Integer> nameToIdx = new HashMap<>();
                Map<Integer,String> idxToName = new HashMap<>();
                int[][] graph = new int[line2.length][line2.length];
                for (int i = 0; i < nodeNum; i++) {
                    String[] line3 = reader.readLine().split(" ");
                    nameToIdx.put(line3[0],i);
                    idxToName.put(i,line3[0]);
                    for (int j = 1; j < line3.length ; j++) {
                        graph[i][j-1] = Integer.parseInt(line3[j]);
                    }
                }
                bfs(graph,nameToIdx.get(beginNode),new LinkedList<>(),new ArrayList<>(),idxToName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
