import java.util.Scanner;


public class C1_InvertTimes {
    private static int count =0;
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
        System.out.println(count);
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
                count+=(mid-i+1);
            }
        }
        System.arraycopy(extra,low,arr,low,high-low+1);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-->0){
            int len = scanner.nextInt();
            int[] arr = new int[len];
            for (int i = 0; i < len; i++) {
                arr[i] = scanner.nextInt();
            }
            count = 0;
            mergeSort(arr);

        }
    }
}
