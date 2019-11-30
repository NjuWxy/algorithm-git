import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**固定和的元素对
 *输入一个数组和一个数字，在数组中查找两个数，使得它们的和正好是输入的那个数字，统计这样两个数的对数。
 * 先排序，再用双指针遍历（遍历过程中如果算到等于输入数字，左指针和右指针都要移动）
 */
public class P1_FixSumElementPair {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                String[] strs = reader.readLine().split(" ");
                int[] arr = new int[strs.length];
                for (int j = 0; j < arr.length; j++) {
                    arr[j] = Integer.parseInt(strs[j]);
                }
                int sum = Integer.parseInt(reader.readLine());
                Arrays.sort(arr);
                int left = 0, right = arr.length - 1, count = 0;
                while (left < right) {
                    int total = arr[left] + arr[right];
                    if (total < sum) left++;
                    else if (total > sum) right--;
                    else {
                        count++;
                        left++;
                        right--;
                    }
                }
                System.out.println(count);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
