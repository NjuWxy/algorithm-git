import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**数组查询
 * Given an array, the task is to complete the function which finds the maximum sum subarray,
 * where you may remove at most one element to get the maximum sum.
 *
 * dpForward = new int[arr.length]     dpBackward = new int[arr.length]
 * dpForward[i]表示从0->i这些元素里面以i结尾的连续子数组的和最大的子数组的和  dpForward[i] = max(dpForward[i-1]+arr[i],arr[i])
 * dpBackward[i]表示从i->arr.length-1这些元素里面以i开头的连续子数组的和最大的子数组的和 dpBackward[i] = max(dpBackward[i+1]+arr[i],arr[i])
 * 先找最大的dpForward[i]，再找最大的dpForward[i-1]+dpBackward[i+1]，再找两者最大的
 */
public class C4A_ArrayQuery {
    private static void solution(int[] arr){
        int[] dpForward = new int[arr.length];
        int[] dpBackward = new int[arr.length];
        dpForward[0] = arr[0];
        dpBackward[arr.length-1] = arr[arr.length-1];
        int max = dpForward[0];
        for (int i = 1; i < arr.length ; i++) {
            dpForward[i] = Math.max(dpForward[i-1]+arr[i],arr[i]);
            max = Math.max(dpForward[i],max);
        }
        for (int i = arr.length-2; i >=0  ; i--) {
            dpBackward[i] = Math.max(dpBackward[i+1]+arr[i],arr[i]);
        }
        for (int i = 1; i < arr.length-1 ; i++) {
            max = Math.max(dpForward[i-1]+dpBackward[i+1],max);
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                int[] arr = new int[n];
                String[] line = reader.readLine().split(" ");
                for (int i = 0; i < line.length ; i++) {
                    arr[i] = Integer.parseInt(line[i]);
                }
                solution(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
