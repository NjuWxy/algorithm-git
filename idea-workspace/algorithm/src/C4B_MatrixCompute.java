import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**矩阵计算
 * Let's define a Series Whose recurrence formula is as follows :
 * C(n)= 3C(i-1) + 4C(i-2) + 5C(i-3) + 6C(i-4)  C(0)= 2  C(1)= 0  C(2)= 1  C(3)= 7
 * Now based on this Series a Matrix Mi,j of size nn is to be formed.
 * The top left cell is(1,1) and the bottom right corner is (n,n). Each cell (i,j) of the Matrix contains
 * either 1 or 0. If C( (i*j)^3 ) is odd, Mi,j is 1, otherwise, it's 0.Count the total number of ones in the Matrix.
 *
 * 计算c的时候每次只保留最后一位
 * 或者找规律，直接((i*j)^3)%7==1
 */
public class C4B_MatrixCompute {
    private static void solution(int n){
        int size = (int)Math.pow(n*n,3);
        int[] cValues = new int[size+1];
        cValues[0] = 2;
        cValues[1] = 0;
        cValues[2] = 1;
        cValues[3] = 7;
        for (int i = 4; i <=size ; i++) {
            int temp = 3*cValues[i-1]+ 4*cValues[i-2]+5*cValues[i-3]+6*cValues[i-4];
            String tempStr = String.valueOf(temp);
            cValues[i] = tempStr.charAt(tempStr.length()-1)-'0';
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <=n ; j++) {
                int x = (int) Math.pow(i*j,3);
                if(cValues[x]%2==1)count++;
            }
        }
        System.out.println(count);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i < t; i++) {
                int n = Integer.parseInt(reader.readLine());
                solution(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
