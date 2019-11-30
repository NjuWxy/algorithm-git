import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**按照要求保留数组元素使得和最大
 * Given an array of N numbers, we need to maximize the sum of selected numbers. At each step, you need to select a number Ai,
 * delete one occurrence of Ai-1 (if exists) and Ai each from the array. Repeat these steps until the array gets empty.
 * The problem is to maximize the sum of selected numbers.
 *
 * 从最大的元素开始选，选A[i]，如果A[i]-1存在，就要删去A[i]-1,直至所有的元素都被选掉或删掉
 */
public class C4A_RemainElementsToMaxSum {
    private static void solution(List<Integer> arr){
        arr.sort((a1,a2)->a2-a1);
        int sum = 0;
        while (arr.size()>0){
           int num = arr.get(0);
           sum+=num;
           arr.remove(0);
           int removeIdx = arr.indexOf(num-1);
           if(removeIdx>=0){
               arr.remove(removeIdx);
           }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                List<Integer> arr = new ArrayList<>(n);
                String[] line = reader.readLine().split(" ");
                for (int i = 0; i < n ; i++) {
                    arr.add(Integer.parseInt(line[i]));
                }
                solution(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
