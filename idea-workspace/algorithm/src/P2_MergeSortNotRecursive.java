import java.util.Scanner;

/**非递归和并排序
 * mergeSort方法里面，
 *      外循环1<=mergeLen<arr.length,每次*2，
 *      内循环0<=low<arr.length-mergeLen，每次加mergeLen*2,
 *          计算 low,mid,high,调用merge(arr,extra,low,mid,high)
 */
public class P2_MergeSortNotRecursive {
    private static void mergeSort(int[] arr){
        int[] extra = new int[arr.length];
        for (int mergeLen = 1; mergeLen < arr.length; mergeLen=mergeLen*2) {
            for (int low = 0; low < arr.length-mergeLen ; low+=mergeLen*2) {
                int mid=low+mergeLen-1,high=Math.min(low+mergeLen*2-1,arr.length-1);
                if(low<high){
                    merge(arr,extra,low,mid,high);
                }
            }
        }
        for(int i=0;i<arr.length-1;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println(arr[arr.length-1]);
    }
    private static void merge(int[] arr,int[] extra,int low,int mid,int high){
        int i=low,j=mid+1,k=low;
        while (k<=high){
            if(i>mid){
                extra[k++]=arr[j++];
            }else if(j>high){
                extra[k++] = arr[i++];
            }else if(arr[i]<=arr[j]){
                extra[k++] = arr[i++];
            }else {
                extra[k++] = arr[j++];
            }
        }
        System.arraycopy(extra,low,arr,low,high-low+1);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()){
            int len = scanner.nextInt();
            int[] arr = new int[len];
            for(int i=0;i<len;i++){
                arr[i] = scanner.nextInt();
            }
            mergeSort(arr);

        }
    }
}
