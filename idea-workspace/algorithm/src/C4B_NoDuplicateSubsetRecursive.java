import java.util.Scanner;

/**无重复字符子集问题
 *Mike knows how many subset are possible in an array of N integers. The subsets may or may not have the different sum.
 * The challenge is to find the maximum sum produced by any subset under the condition:
 * The elements present in the subset should not have any digit in common.
 * sum=getSum(arr,start+1,sum,digitsInSumComposite)  arr[start]中的数字和digitsInSumComposite重复
 *     max(
 *      getSum(arr,start+1,sum,digitsInSumComposite),
 *      getSum(arr,start+1,sum+arr[start],digitsInSumComposite+String(arr[start]))
 *     )  arr[start]中的数字和digitsInSumComposite不重复
 *
 *
 * todo 动态规划
 */
public class C4B_NoDuplicateSubsetRecursive {
    private static int getSum(int[] arr, int start, int sum, String digits){
        if(start>=arr.length)return sum;
        int res;
        int number = arr[start];
        String numberStr = String.valueOf(number);
        boolean duplicate = false;
        for(char c:numberStr.toCharArray()){
            if(digits.indexOf(c)>=0){
                duplicate = true;
                break;
            }
        }
        int withoutNumberSum = getSum(arr,start+1,sum,digits);
        if(duplicate){
            res = withoutNumberSum;
        }else {
            res = Math.max(withoutNumberSum,getSum(arr,start+1,sum+number,digits+numberStr));
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t-- > 0) {
            int n = in.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = in.nextInt();
            System.out.println(getSum(a,0,0,""));
        }
    }
}
