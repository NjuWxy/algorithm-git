import java.util.Scanner;

/**漆狗屋 书本分发
 * 本质是画匠问题，num个花匠并行工作，arr[n]表示n个画板每个画板需要的时间，每一个花匠只能刷连续的板子，求最少的时间
 * 1.求n个画板的总时间max,min=0,mid=(max+min)
 * 2.limit=mid,getNeedNum(limit)计算每一个花匠最多只能刷limit时长时需要的花匠数k,k>num时min=mid,k<=num,max=mid……直到min==max-1
 * 3.max就是结果
 */
/**倒置个数
 * 有一个由N个实数构成的数组，如果一对元素A[i]和A[j]是倒序的，即i<j但是A[i]>A[j]则称它们是一个倒置，设计一个计算该数组中所有倒置数量的算法。要求算法复杂度为O(nlogn)
 *
 * 就是合并排序merge的过程中，i,j分别是左半部分和右半部分的指针，如果遇到arr[i]>arr[j]，count+=(mid-i+1)，因为i和i后面的元素比j大
 */
public class C2A_PainterProblem {
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
		num++;//！！！！！important
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
				if(getNeedNum(arr, mid)>num) {//!!!
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
