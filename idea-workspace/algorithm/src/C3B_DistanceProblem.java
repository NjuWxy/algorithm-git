import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**距离问题
 * In a given cartesian plane, there are N points. We need to find the Number of Pairs of points(A,B) such that
 *
 * Point A and Point B do not coincide.
 * Manhattan Distance and the Euclidean Distance between the points should be equal.
 * Note : Pair of 2 points(A,B) is considered same as Pair of 2 points(B,A).
 *
 * Manhattan Distance = |x2-x1|+|y2-y1|
 *
 * Euclidean Distance = ((x2-x1)^2 + (y2-y1)^2)^0.5 where points are (x1,y1) and (x2,y2).
 *
 * Constraints:1<=T <= 50, 1<=N <= 2*10 ^ 5, 0<=(|Xi|, |Yi|) <= 10^9
 *
 *  |x2-x1|+|y2-y1|= ((x2-x1)^2 + (y2-y1)^2)^0.5  化简之后x1==x2||y1==y2
 *
 *  三个map分别记录x相同的值和数目，y相同的值和数目，xy都相同的值和数目。
 *  对三个map分别计算由多少对相同的x，多少对相同的y，多少对相同的xy
 *  例如，计算多少对相同的x： for(int n:xMap.values){xSamePairs+=(n(n-1)/2)}
 *  最后用xSamePairs+ySamePairs-xySamePairs
 *  todo 有什么问题？
 */
public class C3B_DistanceProblem {

    private static void solution(Map<Integer,Integer> xMap,Map<Integer,Integer> yMap,Map<String,Integer> xyMap){
        int xAns=0,yAns=0,xyAns=0;
        for (int n:xMap.values()) {
            xAns = xAns + n*(n-1)/2;
        }
        for (int n:yMap.values()) {
            yAns = yAns + n*(n-1)/2;
        }
        for (int n:xyMap.values()) {
            xyAns = xyAns + n*(n-1)/2;
        }
        System.out.println(xAns+yAns-xyAns);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for (int i = 0; i < cases; i++) {
            int pNums = scanner.nextInt();
            Map<Integer,Integer> xMap = new HashMap<>();
            Map<Integer,Integer> yMap = new HashMap<>();
            Map<String,Integer> xyMap = new HashMap<>();
            for (int j = 0; j < pNums; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                String pKey = x+" "+y;
                xMap.put(x,xMap.getOrDefault(x,0)+1);
                yMap.put(y,yMap.getOrDefault(y,0)+1);
                xyMap.put(pKey,xyMap.getOrDefault(pKey,0)+1);
            }
            solution(xMap,yMap,xyMap);
        }
    }
}
