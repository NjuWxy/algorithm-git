import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**数字重组整除问题
 *Babul’s favourite number is 17. He likes the numbers which are divisible by 17. This time what he does is that he takes a number N and tries to find the largest number which is divisible by 17, by rearranging the digits. As the number increases he gets puzzled with his own task. So you as a programmer have to help him to accomplish his task.Note: If the number is not divisible by rearranging the digits, then print “Not Possible”. N may have leading zeros.
 * 全排列，找每一种重组情况，看能否整除
 * 全排列：（元素数组arr，本次起点start，……）
 *      用i从start开始遍历arr，交换i和start，递归，再交换回来
 *      递归结束条件：start>=arr.length
 */
public class C3B_RearrangeForDivisible {
    private static final int NUMBER = 17;
    private static void solution(String str){
        Set<Integer> nums = new HashSet<>();
        arrange(str.toCharArray(),0,nums);
        if(nums.size()==0){
            System.out.println("Not Possible");
        }else {
            int max = 0;
            for (int num:nums) {
                max = Math.max(max,num);
            }
            System.out.println(max);
        }
    }
    private static void arrange(char[] digits, int start, Set<Integer> set){
        if(start>=digits.length){
            int num = toInt(digits);
            if(num%NUMBER==0 && num!=0){
                set.add(num);
            }
            return;
        }
        for (int i = start; i <digits.length ; i++) {
            swap(digits,start,i);
            arrange(digits,start+1,set);
            swap(digits,start,i);
        }
    }
    private static int toInt(char[] digits){
        int num = 0;
        if(digits[0]=='0')return 0;
        for (int i = 0; i < digits.length ; i++) {
            num +=  (digits[i]-'0')*Math.pow(10,digits.length-i-1);
        }
        return num;
    }

    private static void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int cases = 0;
        try {
            cases = Integer.parseInt(reader.readLine());
            for(int i=0;i<cases;i++){
                String s = reader.readLine();
                solution(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
