import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/** 区间第K最小（简单）
 * 找到给定数组的给定区间内的第K小的数值。
 * 简单排序就可以了
 */
public class P1_RangeTopK {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for(int i=0;i<t;i++){
                String[] strs = reader.readLine().split(" ");
                int[] arr = new int[strs.length];
                for (int j = 0; j < arr.length; j++) {
                    arr[j] = Integer.parseInt(strs[j]);
                }
                String[] range = reader.readLine().split(" ");
                int left = Integer.parseInt(range[0]);
                int right = Integer.parseInt(range[1]);
                int k = Integer.parseInt(reader.readLine());
                int[] rangeArr = new int[right-left+1];
                System.arraycopy(arr,left-1,rangeArr,0,right-left+1);
                Arrays.sort(rangeArr);
//                for(int j=0;j<rangeArr.length;j++){
//                    System.out.print(rangeArr[j]+" ");
//                }
//                System.out.println();
                System.out.println(rangeArr[k-1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
