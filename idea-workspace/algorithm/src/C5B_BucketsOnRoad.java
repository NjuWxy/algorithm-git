import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**路上的球
 * There are two parallel roads, each containing N and M buckets, respectively.
 * Each bucket may contain some balls. The buckets on both roads are kept in such a way
 * that they are sorted according to the number of balls in them. Geek starts from the end of
 * the road which has the bucket with a lower number of balls(i.e. if buckets are sorted in increasing order,
 * then geek will start from the left side of the road). The geek can change the road only at the point of
 * intersection(which means, buckets with the same number of balls on two roads). Now you need to
 * help Geek to collect the maximum number of balls.
 *
 * 用指针i,j分别指向road1和road2，sum1,sum2分别记录road1和road2在到达road[i]==road[j]时的和（sum1,sum2分别加上road[i],road[j]）
 * 用sum记录结果，每次到达road[i]==road[j]时，sum=max(sum1,sum2),然后把sum1和sum2清零，继续计算下一次到达road[i]==road[j]时的和，再计算sum……
 * 直至指针i到达末尾或指针j到达末尾
 * 跳出循环之后，如果有指针没有到达末尾，继续计算这个road的和，加到sum上
 */
public class C5B_BucketsOnRoad {

    private static void solution(int[] road1,int[] road2){
        int i=0,j=0,sum1=0,sum2=0,sum=0;
        while (i<road1.length && j<road2.length){
            if(road1[i]<road2[j]){
                sum1 += road1[i];
                i++;
            }else if(road1[i]>road2[j]){
                sum2 += road2[j];
                j++;
            }else {
                sum1+=road1[i];
                sum2+=road2[j];
                i++;
                j++;
                sum += Math.max(sum1,sum2);
                sum1 = 0;
                sum2 = 0;
            }
        }
        if(i<road1.length){
            for (; i < road1.length ; i++) {
                sum1+=road1[i];
            }
            sum+=Math.max(sum1,sum2);
        }
        if(j<road2.length){
            for (; j < road2.length ; j++) {
                sum2+=road2[j];
            }
            sum+=Math.max(sum1,sum2);
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line1 = reader.readLine().split(" ");
                int len1 = Integer.parseInt(line1[0]);
                int len2 = Integer.parseInt(line1[1]);
                String[] line2 = reader.readLine().split(" ");
                String[] line3 = reader.readLine().split(" ");
                int[] road1 = new int[len1];
                int[] road2 = new int[len2];
                for (int i = 0; i <len1 ; i++) {
                    road1[i] = Integer.parseInt(line2[i]);
                }
                for (int i = 0; i < len2 ; i++) {
                    road2[i] = Integer.parseInt(line3[i]);
                }
                solution(road1,road2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
