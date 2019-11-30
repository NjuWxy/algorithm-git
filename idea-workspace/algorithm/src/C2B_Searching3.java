import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**Searching_3
 *
 */
public class C2B_Searching3 {
    static class Range{
        long low;
        long high;
        long size;
        public Range(long low,long high){
            this.low = low;
            this.high = high;
            this.size = high-low+1;
        }
    }
    private static void solution(Range[] ranges,long[] queries){
        long[] ranks = new long[queries.length];
        for (int i = 0; i < queries.length ; i++) {
            long q = queries[i];
            long res = 0;
            for (int j = 0; j < ranges.length ; j++) {
                Range range = ranges[j];
                if(q<=range.size){
                    res = range.low+q-1;
                    break;
                }else {
                    q = q - range.size;
                }
            }
            ranks[i] = res;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ranks.length; i++) {
            builder.append(ranks[i]).append(" ");
        }
        builder.deleteCharAt(builder.length()-1);
        System.out.println(builder.toString());
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] line = reader.readLine().split(" ");
                int n = Integer.parseInt(line[0]);
                int q = Integer.parseInt(line[1]);
                String[] line1 = reader.readLine().split(" ");
                Range[] ranges = new Range[n];
                for (int i = 0; i <line1.length ; i+=2) {
                    Range range = new Range(Long.parseLong(line1[i]),Long.parseLong(line1[i+1]));
                    ranges[i/2] = range;
                }
                String[] line2 = reader.readLine().split(" ");
                long[] queries = new long[q];
                for (int i = 0; i <line2.length; i++) {
                    queries[i] = Long.parseLong(line2[i]);
                }
                solution(ranges,queries);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
