import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**分配问题
 * n项工作分配给n个人，给出每一个人坐每一个任务的成本，找出最小成本分配
 * 暴力法 找出全排列 比如四个人四个任务，就是找出 1 2 3 4 这四个数字的全排列 ， 比如 1234 2134 2143 ……，2134表示第一个人配任务2，第二个人配任务1，第三个人配任务3，第四个人配任务4
 */
public class P3_AllocateForce {
    private static void arrange(int[] arr, int start, Map<String,Integer> allocates,int[][] costs){
        if(start==arr.length-1){
            StringBuilder builder = new StringBuilder();
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                builder.append((arr[i]+1)+" ");
                sum+=(costs[i][arr[i]]);
            }
            builder.deleteCharAt(builder.length()-1);
            allocates.put(builder.toString(),sum);
        }
        for (int i = start; i < arr.length ; i++) {
            swap(arr,start,i);
            arrange(arr,start+1,allocates,costs);
            swap(arr,start,i);
        }
    }
    private static void solution(int[][] costs,int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        Map<String,Integer> allocates = new HashMap<>();
        arrange(arr,0,allocates,costs);
        int minCost = Integer.MAX_VALUE;
        for (Integer cost:allocates.values()) {
            minCost = Math.min(minCost,cost);
        }
        List<String> minAllocates = new ArrayList<>();
        for (String allocate:allocates.keySet()) {
            if(allocates.get(allocate)==minCost){
                minAllocates.add(allocate);
            }
        }
        minAllocates.sort((s1,s2)->-s1.compareTo(s2));
        StringBuilder builder = new StringBuilder();
        for (String allocate:minAllocates) {
            builder.append(allocate+",");
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
    }
    private static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                int[][] costs = new int[n][n];
                String[] inputs = reader.readLine().split(",");
                for (int j = 0; j < inputs.length; j++) {
                    String[] temp = inputs[j].split(" ");
                    int person = Integer.parseInt(temp[0]) - 1;
                    int task = Integer.parseInt(temp[1]) - 1;
                    int cost = Integer.parseInt(temp[2]);
                    costs[person][task] = cost;
                }
                solution(costs,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
