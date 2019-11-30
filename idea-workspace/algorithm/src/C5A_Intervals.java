import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**时间分隔
 * Given arrival and departure times of all trains that reach a railway station. Your task is to find the minimum number of platforms required for the railway station so that no train waits.
 *
 * Note: Consider that all the trains arrive on the same day and leave on the same day. Also, arrival and departure times must not be same for a train.
 */
public class C5A_Intervals {
    static class Interval implements Comparable<Interval>{
        int start,end;
        public Interval(int start){
            this.start = start;
        }
        public Interval(int start,int end){
            this.start = start;
            this.end = end;
        }
        public boolean isInterval(){
            return start<=end;
        }

        @Override
        public int compareTo(Interval o) {
            return this.start-o.start;
        }

    }

    private static int computePlat(LinkedList<Interval> queue){

        return 0;
    }

//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        Scanner scan = new Scanner(System.in);
//        int t = scan.nextInt();
////		int time = Integer.valueOf(scan.nextLine());
//        while (t-->0){
//            int n = scan.nextInt();
//            int arrivals[] = new int[n];
//            int departures[] = new int[n];
//            for(int j=0;j<n;j++) {
//                arrivals[j] = scan.nextInt();
//            }
//            for(int j=0;j<n;j++) {
//                departures[j] = scan.nextInt();
//            }
//            List<Interval> times = new ArrayList<>();
//            for (int i = 0; i <n ; i++) {
//                times.add(new Interval(arrivals[i],departures[i]));
//                times.add(new Interval(departures[i],arrivals[i]));
//            }
//            Collections.sort(times);
//            LinkedList<Interval> queue = new LinkedList<>();
//            queue.add(times.get(0));
//            int res = 1;
//            int arrivalCount = 1;
//            for (int i = 1; i < times.size() ; i++) {
//                Interval interval = times.get(i);
//                if(interval.isInterval()){
//                    arrivalCount++;
//                    res = Math.max(res,arrivalCount);
//                }else {
//                    arrivalCount--;
//                }
//            }
//            System.out.println(res);
//        }
//
//    }
    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                int n = Integer.parseInt(reader.readLine().trim());
                String[] line1 = reader.readLine().split("\\s+");
                String[] line2 = reader.readLine().split("\\s+");
                List<Interval> times = new ArrayList<>();
                for (int i = 0; i <n ; i++) {
                    int arrival = Integer.parseInt(line1[i]);
                    int departure = Integer.parseInt(line2[i]);
                    times.add(new Interval(arrival,departure));
                    times.add(new Interval(departure,arrival));
                }
                Collections.sort(times);
                LinkedList<Interval> queue = new LinkedList<>();
                queue.add(times.get(0));
                int res = 1;
                int arrivals = 1;
                for (int i = 1; i < times.size() ; i++) {
                    Interval interval = times.get(i);
                    if(interval.isInterval()){
                        arrivals++;
                        res = Math.max(res,arrivals);
                    }else {
                        arrivals--;
                    }
                }
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
