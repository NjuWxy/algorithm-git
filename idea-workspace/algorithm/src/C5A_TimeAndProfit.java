import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**时间与收益
 * Given a set of n jobs where each job i has a deadline and profit associated to it.
 * Each job takes 1 unit of time to complete and only one job can be scheduled at a time.
 * We earn the profit if and only if the job is completed by its deadline. The task is to find the maximum profit and the number of jobs done.
 *
 * todo
 */
public class C5A_TimeAndProfit {
    static class Job implements Comparable<Job>{
        int id;
        int ddl;
        int profit;
        public Job(int id,int ddl,int profit){
            this.id = id;
            this.ddl = ddl;
            this.profit = profit;
        }

        @Override
        public int compareTo(Job o) {
            return o.profit-this.profit;
        }
    }
    private static void solution(Job[] jobs,int n){
        Arrays.sort(jobs);
        boolean[] slot=new boolean[n];
        Job[] sch=new Job[n];
        Arrays.fill(slot,false);
        slot[Math.min(n,jobs[0].ddl)-1]=true;
        sch[Math.min(n,jobs[0].ddl)-1]=jobs[0];
        for(int i=1;i<n;i++){
            for(int j=Math.min(n,jobs[i].ddl)-1;j>=0;j--){
                if(!slot[j]){
                    // System.out.println(arr[i].dl+" "+j);
                    slot[j]=true;
                    sch[j]=jobs[i];
                    break;
                }
            }
        }
        int maxpr=0,num=0;
        for(int i=0;i<n;i++){
            if(slot[i]){
                maxpr+=sch[i].profit;
                num++;
            }
        }
        System.out.println(num+" "+maxpr);
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine());
                String[] line2 = reader.readLine().split(" ");
                Job[] jobs = new Job[n];
                for (int i = 0; i < n ; i++) {
                    int id = Integer.parseInt(line2[i*3]);
                    int ddl = Integer.parseInt(line2[i*3+1]);
                    int profit = Integer.parseInt(line2[i*3+2]);
                    jobs[i] = new Job(id,ddl,profit);
                }
                solution(jobs,n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
