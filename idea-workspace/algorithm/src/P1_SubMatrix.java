import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**子矩阵问题
 * 给定一个矩形区域，每一个位置上都是1或0，求该矩阵中每一个位置上都是1的最大子矩形区域中的1的个数。
 * 1. 计算矩阵每一行1的高度
 * 2. 对每一行,用栈，栈顶到栈底位置高度依次递减
 * 3.   当前位置高度不大于栈顶高度，不断出栈，计算子矩阵大小（栈顶出栈，位置为st,新栈顶位置为stack.peek,当前位置为j,
 *         extendLen=j-stack.peek-1(stack.peek为空时为j),size=height[st]*extendLen）
 * 4.   否则，当前位置压栈
 * 5. 一行结束之后，检查栈是否为空，不空继续计算子矩阵大小，把前面的j替换为columns
 */
public class P1_SubMatrix {
    private static int solution(int[][] matrix,int rows,int columns){
        int max = 0;
        //记录矩阵每一行1的高度
        for(int i=1;i<rows;i++){
            for(int j=0;j<columns;j++){
                matrix[i][j] = matrix[i][j]==1?matrix[i-1][j]+1:0;
            }
        }
        for(int i=0;i<rows;i++){//对每一行
            Stack<Integer> stack = new Stack<>();//用栈，栈顶到栈底位置高度依次递减
            for(int j=0;j<columns;j++){
                //当前位置高度不大于栈顶高度，不断出栈，计算子矩阵大小
                // （栈顶出栈，位置为st,新栈顶位置为stack.peek,当前位置为j,size=height[st]*(j-stack.peek-1)）
                while (!stack.isEmpty()&&matrix[i][stack.peek()]>=matrix[i][j]){
                    int st = stack.pop();
                    int extendLen = stack.isEmpty()?j:j-stack.peek()-1;
                    int size = matrix[i][st]*extendLen;
                    if(max<size)max = size;
                }
                //当前位置高度大于栈顶高度，压栈
                stack.push(j);
            }
            //一行之后，检查栈是否为空，不空继续计算子矩阵大小，把前面的j替换为columns
            while (!stack.isEmpty()){
                int st = stack.pop();
                int extendLen = stack.isEmpty()?columns:columns-1-stack.peek();
                int size = matrix[i][st]*extendLen;
                if(max<size)max = size;
            }
        }
        return max;
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for(int i=0;i<t;i++){
                String[] sizes = reader.readLine().split(" ");
                int rows = Integer.parseInt(sizes[0]);
                int columns = Integer.parseInt(sizes[1]);
                int[][] matrix = new int[rows][columns];
                for(int j=0;j<rows;j++){
                    String[] strs = reader.readLine().split(" ");
                    for(int k=0;k<columns;k++){
                        matrix[j][k] = Integer.parseInt(strs[k]);
                    }
                }
                System.out.println(solution(matrix,rows,columns));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
