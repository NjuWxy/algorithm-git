package algorithm;

import java.util.Scanner;

public class DivisityQuery {
	private static int[] solution(int[] arr, int[] queries) {
		int[] res = new int[queries.length];
		for(int i=0;i<queries.length;i++) {
			int total = 0;
			for(int j=0;j<arr.length;j++) {
				if(arr[j]%queries[i]==0) {
					total++;
				}
			}
			res[i] = total;
		}
		return res;
	}
	private static void print(int[] arr) {
		if(arr.length==0)return;
		for(int j=0;j<arr.length-1;j++) {
			System.out.print(arr[j]+" ");
		}
		System.out.println(arr[arr.length-1]);
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int alen = scanner.nextInt();
			int qlen = scanner.nextInt();
			int arr[] = new int[alen];
			int queries[] = new int[qlen];
			for (int j = 0; j < alen; j++) {
				arr[j] = scanner.nextInt();
			}
			for (int j = 0; j < queries.length; j++) {
				queries[j] = scanner.nextInt();
			}
			print(solution(arr, queries));
		}
	}
}
