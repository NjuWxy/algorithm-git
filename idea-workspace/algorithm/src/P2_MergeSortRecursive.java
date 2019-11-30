/**归并排序
 * mergeSort里先对左（low,mid）右(mid+1,high)两边分别mergeSort,再merge
 */
public class P2_MergeSortRecursive {
    private void mergeSort(int[] array, int[] extra, int low, int high) {
        if (low == high) {
            return;
        }
        int mid = (low + high) / 2;
        if (mid > low) {
            mergeSort(array, extra, low, mid);
            mergeSort(array, extra, mid + 1, high);
        }
        merge(array, extra, low, high, mid);

    }

    int[] sort(int[] sourceArray){
        int[] res = sourceArray.clone();
        int[] extra = new int[res.length];
        mergeSort(res, extra, 0, sourceArray.length - 1);
        return res;
    }

    private void merge(int[] array, int[] extra, int low, int high, int mid) {
        int i = low, j = mid + 1, k = low;
        while (k <= high) {
            if (i > mid) extra[k++] = array[j++];
            else if (j > high) extra[k++] = array[i++];
            else if (array[i] <= array[j]) extra[k++] = array[i++];
            else extra[k++] = array[j++];
        }
        for (i = low; i <= high; i++) {
            array[i] = extra[i];
        }
    }

}
