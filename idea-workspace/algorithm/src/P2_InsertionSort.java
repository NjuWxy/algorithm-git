import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**插入排序
 * gap=1, gap=<i<arr.length
 *       内循环：j=i, temp=arr[i],while(j>=gap && arr[j-gap]>temp){arr[j]=arr[j-gap];j-=gap}
 *              arr[j]=temp
 */
public class P2_InsertionSort {
    private static void solution(int[] arr){
        int gap = 1;
        for (int i = gap; i <arr.length ; i++) {
            int temp = arr[i];
            int j=i;
            while (j >= gap && arr[j-gap]>temp){
                arr[j] = arr[j-gap];
                j-=gap;
            }
            arr[j] = temp;
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
                int[] arr = new int[strings.length-1];
                for (int i = 1; i < strings.length ; i++) {
                    arr[i-1] = Integer.parseInt(strings[i]);
                }
                solution(arr);
                print(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
