import java.util.Scanner;

/**计数排序
 * counters = new int[arr.length]
 * 初始化counters使得counters[i]表示比arr[i]小的元素的数目
 * 把结果存进res数组中，arr[i]这个元素在res的位置是counter[i],如果发现res[counter[i]]已经被占，就需要counter[i]++,直至发现空位
 */
public class P2_CountSort {
    private static void solution(int[] arr){
        int[] counters = new int[arr.length];
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr.length;j++){
                if(arr[j]<arr[i]){
                    counters[i]++;
                }
            }
        }
        int[] res = new int[arr.length];
        for(int i=0;i<counters.length;i++){
            int pos = counters[i];
            while (res[pos]!=0){
                pos++;
            }
            res[pos] = arr[i];
        }
        for(int i=0;i<arr.length-1;i++){
            System.out.print(res[i]+" ");
        }
        System.out.println(res[res.length-1]);
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
