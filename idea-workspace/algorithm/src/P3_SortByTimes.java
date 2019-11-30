import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**按照数值个数排序
 * 对给定数组中的元素按照元素出现的次数排序，出现次数多的排在前面，如果出现次数相同，则按照数值大小排序。
 * 例如，给定数组为{2, 3, 2, 4, 5, 12, 2, 3, 3, 3, 12}，
 * 则排序后结果为{3, 3, 3, 3, 2, 2, 2, 12, 12, 4, 5}。
 * 自定义比较器
 */
public class P3_SortByTimes {
    private static Map<Integer,Integer> preprocess(String string){
        String[] strs = string.split(" ");
        Map<Integer,Integer> times = new HashMap<>();
        for (int j = 0; j <strs.length ; j++) {
            int num = Integer.parseInt(strs[j]);
            if(times.containsKey(num)){
                times.replace(num,times.get(num)+1);
            }else {
                times.put(num,1);
            }
        }
        return times;
    }
    private static void solution(Map<Integer,Integer> times){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int o1Times = times.get(o1);
                int o2Times = times.get(o2);
                if(o1Times==o2Times){
                    return o1-o2;
                }else {
                    return o2Times - o1Times;
                }
            }
        };
        List<Integer> keys = new ArrayList<>(times.keySet());
        Collections.sort(keys,comparator);
        for (int i = 0; i <keys.size() ; i++) {
            int key = keys.get(i);
            int repeat = times.get(key);
            for (int j = 0; j < repeat; j++) {
                if(!(i==keys.size()-1&&j==repeat-1)){
                    System.out.print(key+" ");
                }else {
                    System.out.print(key);
                }
            }
        }
        System.out.println();
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            for (int i = 0; i <t ; i++) {
                int n = Integer.parseInt(reader.readLine());
                String string = reader.readLine();
                Map<Integer,Integer> times = preprocess(string);
                solution(times);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
