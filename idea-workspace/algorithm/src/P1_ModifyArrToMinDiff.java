import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 调整数组使差最小  动态规划
 * 有两个序列 a,b，大小都为 n,序列元素的值任意整数，无序； 要求：通过交换 a,b 中的元素，使[序列 a 元素的和]与[序列 b 元素的和]之间的差最小。
 * 这题可以转化为从2n个元素里面选n个元素，使得他们的和最接近2n个元素总和的一半
 * 把两个序列合并成一个序列arr，长度len=2n，全排列，找出arr的所有长度为n的子集，计算他们与2n个元素总和的一半的接近程度
 * 外循环：(1<<len)/2-1 =< i< (1<<len)，
 * 内循环，0<=j<len。((1<<j)&i)==1表示arr[j]在子集中。当满足j遍历到最后并且恰好选了n个元素是，就找出了一个长度为n的子集，计算这个子集的和
 * 内循环没有遍历到最后却已经选了多于n个元素的时候跳出内循环
 * <p>
 *
 * 要先对arr的元素减去arr里的元素的最小值，防止有负数
 * [][][] dp = new int[n + 1][n / 2 + 1][sum / 2 + 1];
 * dp[i][j][k]表示在前i个元素里面选了j个元素时，最接近k的和、
 * 分为选了第i个元素dp[i-1][j-1][k-arr[i]] + arr[i]和不选第i个元素 dp[i-1][j][k]
 * dp[i][j][k] = dp[i-1][j][k]                                             k<arr[i]
 *               max( dp[i-1][j][k],  dp[i-1][j-1][k-arr[i]] + arr[i] )    k>=arr[i]
 */
public class P1_ModifyArrToMinDiff {
    private static void solution(int[] arr, int n, int sum) {
        int[][][] dp = new int[n + 1][n / 2 + 1][sum / 2 + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n / 2; j++) {
                for (int k = 1; k <= sum / 2; k++) {
                    if (k < arr[i]) {
                        dp[i][j][k] = dp[i-1][j][k];
                    } else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i - 1][j - 1][k - arr[i]]+arr[i]);
                    }
                }
            }
        }
        System.out.println(Math.abs(sum - dp[n][n / 2][sum / 2] * 2));
    }

    private static int preprocess(int[] arr) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
        }
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i] - min;
            sum += arr[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                String[] strs1 = reader.readLine().split(" ");
                String[] strs2 = reader.readLine().split(" ");
                int[] arr1 = new int[strs1.length];
                int[] arr2 = new int[strs1.length];
                for (int j = 0; j < strs1.length; j++) {
                    arr1[j] = Integer.parseInt(strs1[j]);
                    arr2[j] = Integer.parseInt(strs2[j]);
                }
                int[] arr = new int[arr1.length + arr2.length + 1];
                System.arraycopy(arr1, 0, arr, 1, arr1.length);
                System.arraycopy(arr2, 0, arr, arr1.length + 1, arr2.length);
                int sum = preprocess(arr);
                solution(arr,arr.length-1,sum);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
