import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class C5B_Intervals {
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
                int res = 0;
                int arrivals = 0;
                for (int i = 0; i < times.size() ; i++) {
                    if(times.get(i).isInterval()){
                        arrivals++;
                    }else {
                        arrivals--;
                    }
                    res = Math.max(res,arrivals);
                }
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
