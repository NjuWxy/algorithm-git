import java.util.Scanner;

/**冒泡排序
 * i由arr.length-1到1,指向本轮冒泡最后存放的位置
 * j由0到i，两两比较，交换大的到后面
 */
public class P2_BubbleSort {
    private static void solution(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        for(int i=0;i<arr.length-1;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println(arr[arr.length-1]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int len = scanner.nextInt();
            int[] arr = new int[len];
            for(int j=0;j<len;j++){
                arr[j] = scanner.nextInt();
            }
            solution(arr);
        }
    }
}
