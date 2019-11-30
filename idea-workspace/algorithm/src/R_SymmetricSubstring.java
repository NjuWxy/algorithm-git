import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**对称子字符串
 * Given a string ‘str’ of digits, find length of the longest substring of ‘str’,such that the length of the substring is 2k digits and sum of left k digits is equal to the sum of right k digits.
 * 0<=i<str.length,双向指针分别由i,i+1向左向右拓展相同长度，计算两边和，相等时加入总结果（不跳出，跳出内循环条件是双向指针到达边界）
 */
public class R_SymmetricSubstring {
    /**
     * The idea is to consider all possible mid points (of even length substrings)
     * and keep expanding on both sides to get and update optimal length as
     * the sum of two sides become equal.
     * @param str
     */
    private static void solution(String str){
        int maxLen = 0;
        for(int i=0;i<str.length();i++){
            int left = i,right = i+1,leftSum = 0,rightSum=0;
            while (left>=0&&right<str.length()){
                leftSum += (str.charAt(left)-'0');
                rightSum += (str.charAt(right)-'0');
                if(leftSum==rightSum){
                    maxLen = Math.max(maxLen,right-left+1);
                }
                left--;
                right++;
            }
        }
        System.out.println(maxLen);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for(int i=0;i<t;i++){
                String str = reader.readLine();
                solution(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
