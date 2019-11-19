package algorithm;

import java.util.Scanner;

public class PainterProblem {
	private static int getNeedNum(int[] arr, int limit) {
		int num = 0;
		int sum = 0;
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>limit) {
				return Integer.MAX_VALUE;
			}
			sum+=arr[i];
			if(sum>limit) {
				sum = arr[i];
				num++;
			}
		}
		num++;
		return num;
	}
	private static void solution(int[] arr, int num) {
		int min = 0;
		int max = 0;
		if(arr.length<num) {
			for (int i = 0; i < arr.length; i++) {
				max = Math.max(max, arr[i]);
			}
		}else {
			for(int i=0;i<arr.length;i++) {
				max+=arr[i];
			}
			int mid = (min+max)/2;
			while (min!=max-1) {
				mid = (min+max)/2;
				if(getNeedNum(arr, mid)>num) {
					min = mid;
				}else {
					max = mid;
				}
				
			}
		}
		
		System.out.println(max);
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int num = scanner.nextInt();
			int len = scanner.nextInt();
			int[] arr = new int[len];
			for (int j = 0; j < len; j++) {
				arr[j] = scanner.nextInt();
			}
			solution(arr, num);
		}
	}
}
