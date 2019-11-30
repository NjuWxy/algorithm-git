import java.util.Scanner;

/**序号乘方
 * There are Infinite People Standing in a row, indexed from 1.A person having index 'i' has strength of (i*i).
 * You have Strength 'P'. You need to tell what is the maximum number of People You can Kill With your Strength P.
 * You can only Kill a person with strength 'X' if P >= 'X' and after killing him, Your Strength decreases by 'X'.
 * 就是计算1^2+2^2+3^2+……+i^2<=p,求i的最大值
 */
public class R_IndexPow {
	private static long solution(long p) {
		long i = 1;
		long pow = i*i;
		while (pow<p) {
			i++;
			long temp = pow+i*i;
			if(temp>p) {
				return i-1;
			}else if(temp==p) {
				return i;
			}else {
				pow = temp;
			}
			
		}
		return 0;
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			long p = scanner.nextLong();
			System.out.println(solution(p));
		}
	}

}
