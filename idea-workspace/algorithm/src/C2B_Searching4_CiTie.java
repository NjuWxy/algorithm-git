import java.util.Scanner;

/**Searching_4
 * Given n Magnets which are placed linearly, with each magnet to be considered as of point object.
 * Each magnet suffers force from its left sided magnets such that they repel it to the right and vice versa.
 * All forces are repulsive. The force being equal to the distance (1/d , d being the distance). Now given the positions of
 * the magnets, the task to find all the points along the linear line where net force is ZERO.
 *
 * Note: Distance have to be calculated with precision of 0.0000000000001.
 *
 * Constraints:1<=T<=100,1<=N<=100,1<=M[]<=1000
 * 每两个相邻的磁铁之间找一个位置，使得这个位置的力平衡。用二分法查找，二分法每次找到一个位置，计算这个位置力的总和
 * 三层循环，第一层遍历相邻的磁铁，第二层在相邻的磁铁之间用二分法查找位置，第三层计算查找的位置所有磁铁对这个位置的力的总和sum
 * 如果Math.abs(sum)<0.00001就找到力的平衡点 String.format("%.2f",mid) ⚠️要break
 * sum>0 low=mid; sum<0 high=mid
 */
public class C2B_Searching4_CiTie {
    private static void solution(int[] positions){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < positions.length-1; i++) {
            double low = positions[i];
            double high = positions[i+1];
            while (low<high){
                double mid = (low+high)/2;
                double sum = 0;
                for (int j = 0; j < positions.length; j++) {
                    sum+=(1/(mid-positions[j]));
                }
                if(Math.abs(sum)<0.00001){
                    String output = String.format("%.2f", mid);
                    builder.append(output).append(" ");
                    break;
                }else if(sum>0){
                    low = mid;
                }else {
                    high = mid;
                }
            }
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cases = scanner.nextInt();
        for(int i=0;i<cases;i++){
            int magnetCount = scanner.nextInt();
            int[] postions = new int[magnetCount];
            for(int j=0;j<magnetCount;j++){
                postions[j] = scanner.nextInt();
            }
            solution(postions);
        }
    }
}
