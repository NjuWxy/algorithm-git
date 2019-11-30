import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**最小交换次数
 * Given an array of N distinct elementsA[ ], find the minimum number of swaps required to sort the array.Your are required
 * to complete the function which returns an integer denoting the minimum number of swaps, required to sort the array.
 *
 * 用排序之后的数组sorted和原数组比较，如果sorted[i]!=arr[i]，则从i的后一位开始在arr找到与sorted[i]相等的元素arr[j]进行交换，交换次数+1
 */
public class C1_MinSwapTimes {
    private static void solution(int[] arr){
        int[] copy = new int[arr.length];
        System.arraycopy(arr,0,copy,0,arr.length);
        Arrays.sort(copy);
        int swapTimes = 0;
        for (int i = 0; i < arr.length ; i++) {
            if(arr[i]==copy[i]){
                continue;
            }else {
                for (int j = i+1; j < arr.length ; j++) {
                    if(copy[i]==arr[j]){
                        swap(arr,i,j);
                        swapTimes++;
                        break;
                    }
                }
            }
        }
        System.out.println(swapTimes);
    }
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                int[] arr = new int[n];
                String[] line = reader.readLine().split(" ");
                for (int i = 0; i < n ; i++) {
                    arr[i] = Integer.parseInt(line[i]);
                }
                solution(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
