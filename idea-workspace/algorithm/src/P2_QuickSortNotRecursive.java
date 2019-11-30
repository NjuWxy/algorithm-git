import java.util.LinkedList;
import java.util.Scanner;

/**非递归快排
 * partition函数划分，小的放左边，大的放右边，返回中间元素的角标mid
 * sort函数用一个队列queue保存range，最开始时把range(0,arr.length-1)放进去，
 * 当queue不为空时，取出队首range1，进行partition，mid！=-1时，把新的range(range1.low,mid-1),range(mid+1,range1.high)加进队列
 */
public class P2_QuickSortNotRecursive {
    static class Range{
        int left;
        int right;

        public Range(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    private static int partition(int[] arr, Range range){
        if(range.right<=range.left)return -1;
        int v = arr[range.right],i=range.left,j=range.right-1;
        while (i<j){
            while (i<j&&arr[i]<=v)i++;
            while (i<j&&arr[j]>=v)j--;
            if(i<j){
                swap(arr,i,j);
                i++;
                j--;
            }
        }
        if(arr[i]<v)i++;
        swap(arr,i,range.right);
        return i;
    }
    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    private static void solution(int[] arr){
        LinkedList<Range> queue = new LinkedList<>();
        queue.push(new Range(0,arr.length-1));
        while (!queue.isEmpty()){
            Range range = queue.pollFirst();
            int mid = partition(arr,range);
            if(mid!=-1){
                queue.add(new Range(range.left,mid-1));
                queue.add(new Range(mid+1,range.right));
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
            for(int i=0;i<len;i++){
                arr[i] = scanner.nextInt();
            }
            solution(arr);

        }
    }
}
