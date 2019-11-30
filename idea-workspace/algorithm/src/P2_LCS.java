import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**最长公共子序列
 * 给定两个字符串s1,s2，返回两个字符串的最长公共子序列（不是最长公共子字符串），可能是多个。
 * m=s1.length,n=s2.length,dp[m+1][n+1],direction[m+1][n+1]
 * dp[i+1][j+1]表示s1[0-i],s2[0-j]的最长公共子序列的长度。
 *      dp[i+1][j+1]=
 *                   dp[i][j]+1                     (s1[i]==s2[j])
 *                   Max(dp[i][j+1],dp[i+1][j])     (s1[i]!=s2[j])
 * direction[i+1][j+1]记录的是dp[i+1][j+1]计算时的方向，用的是dp[i][j]，还是dp[i][j+1]，还是dp[i+1][j]，还是dp[i][j+1]和dp[i+1][j]二选一
 * 0<=i<m, 0<=j<n 计算dp和direction
 * i=a,j=b,由这个位置开始收集字符（a,b最初为右下角），根据direction的方向往上走，当dp[i+1][j+1]计算时用的是dp[i][j]就把i位置的字符收进来，如果是dp[i][j+1]和dp[i+1][j]二选一，则开分支递归
 * 收集结束条件 i<=0||j<=0   收集结束之后把收集到的子串加进结果中  最后取最大子串（可能多个，排序输出）
 *
 * !!!注意⚠️方向不能错
 */
public class P2_LCS {
    static enum Direction {
        UP,LEFT,LEFT_UP,BOTH
    }
    private static void collect(Direction[][] directions, int startI, int startJ, Set<String> res,StringBuilder path,char[] s1){
        int i=startI,j=startJ;
        while (i>0&&j>0){
            if(directions[i][j]==Direction.LEFT_UP){
                path.append(s1[i-1]);
                i--;
                j--;
            }else if(directions[i][j]==Direction.LEFT){
                j--;
            }else if(directions[i][j]==Direction.UP){
                i--;
            }else {
                collect(directions,i-1,j,res,new StringBuilder(path.toString()),s1);
                j--;
            }
        }
        res.add(path.reverse().toString());
    }
    private static void solution(char[] s1,char[] s2){
        int[][] dp = new int[s1.length+1][s2.length+1];
        Direction[][] directions = new Direction[s1.length+1][s2.length+1];
        for (int i = 0; i < s1.length ; i++) {
            for (int j = 0; j < s2.length ; j++) {
                if(s1[i]==s2[j]){
                    dp[i+1][j+1] = dp[i][j]+1;
                    directions[i+1][j+1] = Direction.LEFT_UP;
                }else {
                    if(dp[i][j+1]<dp[i+1][j]){
                        dp[i+1][j+1] = dp[i+1][j];
                        directions[i+1][j+1] = Direction.LEFT;
                    }else if(dp[i][j+1]>dp[i+1][j]){
                        dp[i+1][j+1] = dp[i][j+1];
                        directions[i+1][j+1] = Direction.UP;
                    }else {
                        dp[i+1][j+1] = dp[i+1][j];
                        directions[i+1][j+1] = Direction.BOTH;
                    }
                }

            }
        }
        Set<String> res = new HashSet<>();
        collect(directions,s1.length,s2.length,res,new StringBuilder(),s1);
        List<String> list = new ArrayList<>(res);
        Collections.sort(list);
        for (String str:list) {
            System.out.println(str);
        }

    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int cases = Integer.parseInt(reader.readLine());
            for (int i=0;i<cases;i++) {
                String str1 = reader.readLine();
                String str2 = reader.readLine();
                solution(str1.toCharArray(),str2.toCharArray());
            }
        } catch (IOException e) {
            e.printStackTrace();//B1D23CA45B6A
        }
    }
}
