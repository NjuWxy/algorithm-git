import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**和最大的连续降序字符
 * Archana is very fond of strings. She likes to solve many questions related to strings.
 * She comes across a problem which she is unable to solve. Help her to solve.
 * The problem is as follows: Given is a string of length L. Her task is to find the longest string from
 * the given string with characters arranged in descending order of their ASCII code and in arithmetic progression.
 * She wants the common difference should be as low as possible(at least 1) and the characters of the string to be of higher ASCII value.
 * 公差由小到大1<=diff<=25,字符由大到小25>=ltr>=0,计算由这个字符为起始字符、以diff为公差的降序字符串，找出ASCII value和最大的记录下来，这个降序字符串就是结果
 */
public class C3B_MaxSumContinuousDescending {
    private static void solution(String str){
        int[] letters = new int[26];
        Arrays.fill(letters,0);
        for (int i = 0; i < str.length(); i++) {
            letters[str.charAt(i)-'A'] = 1;
        }
        int maxSum = 0;
        int start = 0;
        int minDiff = 0;
        int end = 0;
        for (int diff = 1; diff <= 25; diff++) {
            for (int ltr = 25; ltr >=0 ; ltr--) {
                //todo
                if(letters[ltr]==1){
                    int sum = letters[ltr];
                    int i = ltr-diff;
                    while (i>=0 && letters[i]==1){
                        sum+=letters[i];
                        i-=diff;
                    }
                    if(sum>maxSum){
                        start = ltr;
                        minDiff = diff;
                        end = i;
                        maxSum = sum;
                    }
                }
            }
        }
        for (int i = start; i >end ; i-=minDiff) {
            System.out.print((char)(i+'A'));
        }
        System.out.println();
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
