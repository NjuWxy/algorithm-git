import java.util.*;

/**拓扑排序解的个数
 * 给定有向无环图中所有边，计算图的拓扑排序解的个数。
 *
 * 用Map<String,Integer> inDegrees来记录所有节点的入度
 * 拓扑排序：遍历inDegrees找到入度为0的点，加入路径中，更新inDegrees（删除当前选择的入度为0的点，更新其他点的入度），
 *          然后递归对更新后的inDegrees拓扑排序，递归结束条件是：inDegrees里没有节点了，此时得到最终路径
 * 因为要找到所有解，所以对所有遍历inDegrees找到的入度为0的点同等对待，更新inDegrees时不是在原inDegrees修改，而是复制一份在新的上面修改
 * 然后递归对新的inDegrees拓扑排序，递归结束条件是：inDegrees里没有节点了，此时得到最终路径，把它加进所有解中
 */
public class R_TopoSortNum {
    private static void solution(Map<String,List<String>> edgeMap){
        //计算所有节点的入度
        Map<String,Integer> inDegrees = new HashMap<>();
        for (List<String> targets: edgeMap.values()) {
            for (String target:targets) {
                inDegrees.put(target,inDegrees.getOrDefault(target,0)+1);
            }
        }
        for (String source:edgeMap.keySet()) {
            inDegrees.put(source,inDegrees.getOrDefault(source,0));
        }
        //拓扑排序
        List<String> paths = new ArrayList<>();
        topoSort(inDegrees,edgeMap,paths,"");
        System.out.println(paths.size());

    }
    private static void topoSort(Map<String,Integer> inDegrees,Map<String,List<String>> edgeMap,List<String> paths,String path){
        for (String node:inDegrees.keySet()) {
            if(inDegrees.get(node)==0){
                Map<String,Integer> newInDegrees = updateInDegrees(inDegrees,edgeMap,node);
                topoSort(newInDegrees,edgeMap,paths,path+" "+node);
            }
        }
        if(inDegrees.keySet().size()==0){
            paths.add(path);
        }
    }
    private static Map<String,Integer> updateInDegrees(Map<String,Integer> inDegrees,Map<String,List<String>> edgeMap,String node){
        Map<String,Integer> newInDegrees = new HashMap<>();
        List<String> targets = edgeMap.getOrDefault(node,new ArrayList<>());
        //先把当前节点去除
        for (String key:inDegrees.keySet()) {
            if(!key.equals(node)){
                newInDegrees.put(key,inDegrees.get(key));
            }
        }
        //然后把以当前节点为起点的边的终点的入度-1
        for (String target:targets) {
            newInDegrees.put(target,newInDegrees.get(target)-1);
        }
        return newInDegrees;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        while (n --> 0) {
            String[] edges = scanner.nextLine().trim().split(",");
            Map<String, List<String>> edgeMap = new HashMap<>();
            for (String edge: edges) {
                String[] nodes = edge.split(" ");
                List<String> to = edgeMap.getOrDefault(nodes[0], new ArrayList<>());
                to.add(nodes[1]);
                edgeMap.put(nodes[0], to);
            }
            solution(edgeMap);
        }
    }
}
