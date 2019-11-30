/**快排
 * partition函数划分，小的放左边，大的放右边，返回中间元素的角标mid
 * quickSort里先partition，再递归对左（low,mid-1）右(mid+1,high)两边分别快排quickSort
 */
public class P2_QuickSortRecursive {
    private static int[] sort(int[] sourceArray) throws Exception {
        int[] res = sourceArray.clone();
        quickSort(res,0,res.length-1);
        return res;
    }

    private static void quickSort(int[] array,int low,int high){
        if(low>=high)return;
        int pos = partition(array,low,high);
        quickSort(array,low,pos-1);
        quickSort(array,pos+1,high);
    }

    private static void exchange(int[] array,int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static void print(int[] array,int low,int high){
        for(int i=low;i<=high;i++){
            System.out.print(array[i]+" ");
        }
        System.out.println();
    }

    private static int partition(int[] array,int low,int high){
        int i=low,j=high-1,v=array[high];
        while (i<j){
            //i扫过的地方<=v,i停留的地方1、i<=j&&a[i]>v;2、i>j->a[i]>v
            while (i<=j&&array[i]<=v)i++;
            //j扫过的地方>=v,j停留的地方1、i<j&&a[j]<v;2、i>j->a[j]<v
            while (i<=j&&array[j]>=v)j--;
            if(i<j){
                exchange(array,i,j);
                i++;
                j--;//i=j,a[i]大小未知；i<j,继续循环；i=j+1,a[i]>v
            }
        }
        if(array[i]<v)i++;
        if(array[i]!=v){
            exchange(array,i,high);
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {9,6,1,-1,10};
        System.out.println(partition(arr,0,arr.length-1));
    }
}
