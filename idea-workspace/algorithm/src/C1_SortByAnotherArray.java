import java.util.*;

/**按照另一个数组排序
 * Given two array A1[] and A2[], sort A1 in such a way that the relative order among the elements will be same as those in A2.
 * For the elements not present in A2. Append them at last in sorted order. It is also given that the number of elements
 * in A2[] are smaller than or equal to number of elements in A1[] and A2[] has all distinct elements.
 * Input:A1[] = {2, 1, 2, 5, 7, 1, 9, 3, 6, 8, 8} A2[] = {2, 1, 8, 3}Output: A1[] = {2, 2, 1, 1, 8, 8, 3, 5, 6, 7, 9}
 * Since 2 is present first in A2[], all occurrences of 2s should appear first in A[], then all occurrences 1s as 1 comes after 2 in A[].
 * Next all occurrences of 8 and then all occurrences of 3. Finally we print all those elements of A1[] that are not present in A2[]
 * Constraints:1 ≤ T ≤ 501 ≤ M ≤ 501 ≤ N ≤ 10 & N ≤ M1 ≤ A1[i], A2[i] ≤ 1000
 *
 * todo
 */
public class C1_SortByAnotherArray {
    private static void solution(Integer[] arr, final List<Integer> anotherArr){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                int o1Index = anotherArr.indexOf(o1);
                int o2Index = anotherArr.indexOf(o2);
                if(!contains(o1Index) && !contains(o2Index)){
                    return o1-o2;
                }else if(contains(o1Index) && contains(o2Index)){
                    return o1Index - o2Index;
                }else if(contains(o1Index) && !contains(o2Index)){
                    return -1;
                }else {
                    return 1;
                }
            }
        };
        Arrays.sort(arr,comparator);
        print(arr);
        
    }

    private static boolean contains(int idx){
        return idx!=-1;
    }
    private static void print(Integer[] arr){
        if(arr.length==0)return;
        for (int i = 0; i < arr.length-1; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println(arr[arr.length-1]);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int len = scanner.nextInt();
            int anotherLen = scanner.nextInt();
            Integer[] arr = new Integer[len];
            List<Integer> anotherArr = new ArrayList<Integer>();
            for (int j = 0; j < len; j++) {
                arr[j] = scanner.nextInt();
            }
            for (int j = 0; j < anotherLen; j++) {
                anotherArr.add(scanner.nextInt());
            }
            solution(arr,anotherArr);
        }
    }
}
