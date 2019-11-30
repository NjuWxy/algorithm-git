import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**书本分发
 *You are given N number of books. Every ith book has Pi number of pages. You have to allocate books to M number of students.
 * There can be many ways or permutations to do so. In each permutation one of the M students will be allocated the
 * maximum number of pages. Out of all these permutations, the task is to find that particular permutation in which
 * the maximum number of pages allocated to a student is minimum of those in all the other permutations, and print this
 * minimum value. Each book will be allocated to exactly one student. Each student has to be allocated atleast one book.
 *
 * allotment should be in contiguous order(see explanation for better understanding) todo 上面的解释里说到这一点了吗
 * 和花匠问题稍微不同的是 花匠中板子数目少于花匠数目，直接返回单个板子需要的最大时间，而本问题中，如果书本数目少于学生数目，需要返回-1，因为每一个学生都必须分到一本书
 */
public class C2B_BookAllocate {
    private static int getNeedNum(int[] pages,int limit){
        int needNum = 0;
        int sum = 0;
        for (int i = 0; i < pages.length; i++) {
            if(pages[i]>limit){//!!!意思是说limit分配的过小，我需要很多的花匠依然不能解决问题
                return Integer.MAX_VALUE;
            }
            sum+=pages[i];
            if(sum>limit){
                sum = pages[i];
                needNum++;
            }
        }
        needNum++;
        return needNum;
    }
    private static void solution(int[] pages,int k,int sum){
        int low = 0,high = sum,mid=0;
        if(pages.length<k){//!!!
            System.out.println(-1);
            return;
        }
        while (low!=high-1){
            mid = (low+high)/2;
            if(getNeedNum(pages,mid)>k){
                low = mid;
            }else {
                high = mid;
            }
        }
        System.out.println(high);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                String[] line = reader.readLine().split(" ");
                int[] pages = new int[n];
                int sum = 0;
                for (int i = 0; i <n ; i++) {
                    pages[i] = Integer.parseInt(line[i]);
                    sum+=pages[i];
                }
                int k = Integer.parseInt(reader.readLine());
                solution(pages,k,sum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
