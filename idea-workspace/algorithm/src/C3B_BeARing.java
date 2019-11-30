import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**能否成环
 * Given an array of strings A[ ], determine if the strings can be chained together to form a circle.
 * A string X can be chained together with another string Y if the last character of X is same as first character of Y.
 * If every string of the array can be chained, it will form a circle. For example, for the array arr[] = {"for", "geek", "rig", "kaf"}
 * the answer will be Yes as the given strings can be chained as "for", "rig", "geek" and "kaf".
 *
 * 对给出的字符串列表用邻接表的形式构造两个图graph：首字符-->末字符，和reverseGraph：末字符-->首字符
 * 如果inEqualsOut(graph) && inEqualsOut(reverseGraph) && isCircle(graph) && isCircle(reverseGraph) 返回1，否则返回0
 * inEqualsOut:
 *          判断图的每一个节点的入度是否都等于出度
 * isCircle:
 *          从一个节点开始遍历图，一遍遍历下来，如果还有没有遍历到的节点，就返回false，否则返回true
 */
public class C3B_BeARing {
    private static boolean isCircle(Map<Character,List<Character>> graph){
        char startNode = graph.keySet().iterator().next();
        Map<Character,Boolean> visited = new HashMap<>();
        for (Character node:graph.keySet()) {
            visited.put(node,false);
        }
        dfs(graph,startNode,visited);
        for (Character node:visited.keySet()) {
            if(!visited.get(node))return false;
        }
        return true;
    }
    private static void dfs(Map<Character,List<Character>> graph,char node,Map<Character,Boolean> visited){
        visited.replace(node,true);
        for (Character child:graph.get(node)) {
            if(!visited.get(child)){
                dfs(graph,child,visited);
            }
        }
    }

    private static boolean inEqualsOut(Map<Character,List<Character>> graph){
        Map<Character,Integer> inDegrees = new HashMap<>();
        for (List<Character> targets:graph.values()) {
            for (Character target:targets) {
                inDegrees.put(target,inDegrees.getOrDefault(target,0)+1);
            }
        }
        for (Character source:graph.keySet()) {
            int outDegrees = graph.get(source).size();
            if(inDegrees.getOrDefault(source,0)!=outDegrees){
                return false;
            }
        }
        return true;
    }
    private static void solution(String[] strings){
        //构造两个图graph：首字符-->末字符，和reverseGraph：末字符-->首字符
        Map<Character,List<Character>> graph = new HashMap<>();
        Map<Character,List<Character>> reverseGraph = new HashMap<>();
        for (int i = 0; i < strings.length ; i++) {
            char first = strings[i].charAt(0);
            char last = strings[i].charAt(strings[i].length()-1);
            List<Character> firstTargets = graph.getOrDefault(first,new ArrayList<>());
            firstTargets.add(last);
            graph.put(first,firstTargets);
            List<Character> lastTargets = reverseGraph.getOrDefault(last,new ArrayList<>());
            lastTargets.add(first);
            reverseGraph.put(last,lastTargets);
        }
        //如果inEqualsOut(graph)&&isCircle(graph)&&isCircle(reverseGraph)返回1，否则返回0
        if(inEqualsOut(graph) && isCircle(graph) && isCircle(reverseGraph)){
            System.out.println(1);
        }else {
            System.out.println(0);
        }

    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(reader.readLine());
            for (int i=0;i<cases;i++){
                reader.readLine();
                String s = reader.readLine();
                String[] strings = s.split(" ");
                solution(strings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
