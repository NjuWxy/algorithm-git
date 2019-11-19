package algorithm;

import java.util.Scanner;

public class IndexPow {
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
