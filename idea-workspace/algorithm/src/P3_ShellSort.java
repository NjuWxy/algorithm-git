import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**希尔排序
 * 不断缩小gap
 * gap<=i<arr.length
 *      j=i, temp=arr[j],while(j>=gap && arr[j-gap]>temp){arr[j]=arr[j-gap] j-=gap}
 *      arr[j]=temp
 */
public class P3_ShellSort {
    private static void solution(int[] arr,int[] gaps){
        for (int k = 0; k < gaps.length ; k++) {
            int gap = gaps[k];
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                while (j>=gap && arr[j-gap]>temp){
                    arr[j] = arr[j-gap];
                    j-=gap;
                }
                arr[j] = temp;
            }
        }

    }
    private static void print(int[] arr){
        for (int i = 0; i < arr.length ; i++) {
            System.out.print(arr[i]);
            if(i<arr.length-1){
                System.out.print(" ");
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] strings = reader.readLine().split(" ");
                int[] arr = new int[strings.length];
                for (int i = 0; i < strings.length ; i++) {
                    arr[i] = Integer.parseInt(strings[i]);
                }
                String[] strings2 = reader.readLine().split(" ");
                int[] gaps = new int[strings2.length];
                for (int i = 0; i < strings2.length; i++) {
                    gaps[i] = Integer.parseInt(strings2[i]);
                }
                solution(arr,gaps);
                print(arr);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
