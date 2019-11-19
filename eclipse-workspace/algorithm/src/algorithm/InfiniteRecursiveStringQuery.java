package algorithm;

import java.util.Scanner;

public class InfiniteRecursiveStringQuery {
	private static char solution(long pos) {
		char res;
		if(pos==0) {
			res = '0';
		}else {
			String pat = "12345$54321";
			while (pos>11) {
				long[] recersiveData = getA(pos);
				long len = recersiveData[0];
				long recersiveCount = recersiveData[1];
				pos = pos-(len+recersiveCount)/2;
			}
			if(pos<=0)res='$';
			else {
				res = pat.charAt((int)pos-1);
			}
		}
		return res;
	}
	
	private static long[] getA(long pos) {
		long len = 5;
		long recersiveCount = 0;
		while (len<pos) {
			recersiveCount++;
			len  = len *2 + recersiveCount;
		}
		return new long[] {len,recersiveCount};
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for(int i=0;i<t;i++) {
			long pos = scanner.nextLong();
			System.out.println(solution(pos));
		}
	}

}
