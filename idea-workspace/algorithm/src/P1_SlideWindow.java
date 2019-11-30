import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

/**数组和窗口
 * 给定一个整型数组arr和一个大小为w的窗口，窗口从数组最左边滑动到最右边，每次向右滑动一个位置，求出每一次滑动时窗口内最大元素的和。
 * 用"最大值队列"记录最大值候选值的角标（队首为窗口里的最大值的角标），
 * 先更新"最大值队列"，再滑动窗口
 *      1.检查最大值是否划出窗口，
 *      2.不断淘汰队尾比当前新滑进窗口的值小的,最后队列里加入当前新滑进窗口的值的角标
 *      3.sum+=本次窗口的最大值
 */
public class P1_SlideWindow {
    private static void solution(int[] arr,int w){
        int sum = 0;
        LinkedList<Integer> queue = new LinkedList<>();//记录最大值后选值的角标
        int i = 0;
        while (i<arr.length){
            if(i>=w && queue.getFirst()<=i-w){
                queue.removeFirst();
            }
            while (!queue.isEmpty()&&arr[i]>arr[queue.peekLast()]){
                queue.pollLast();
            }
            queue.addLast(i);
            if(i>=w-1){
                sum+=arr[queue.peekFirst()];
            }
            i++;
        }
        System.out.println(sum);
    }

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
                int windowSize = Integer.parseInt(reader.readLine());
                solution(arr,windowSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
