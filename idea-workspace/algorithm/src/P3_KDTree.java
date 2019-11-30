import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * KD树构造和查找
 * 对给定的点集合构造KD树，要求如下：将方差最大的维度作为当前的分割维度，将数据集在分割维度上排序后的中位数作为分割点。程序要检索给定点对应的最近的K个点的坐标。
 */
public class P3_KDTree {
    private static final int X = 1;
    private static final int Y = 2;

    static class Point implements Comparable<Point> {
        double x;
        double y;
        double distance;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            String xOut = x==(int)x?(int)x+"":x+"";
            String yOut = y==(int)y?(int)y+"":y+"";
            return xOut + " " + yOut;
        }

        @Override
        public int compareTo(Point o) {
            return Double.compare(this.distance,o.distance);
        }
    }

//    private static int getMaxVariantDimension(Point[] arr){
//        double xa = 0;
//        double ya = 0;
//        for (int i = 0; i < arr.length ; i++) {
//            xa += arr[i].x;
//            ya += arr[i].y;
//        }
//        xa = xa/arr.length;
//        ya = ya/arr.length;
//        double xv = 0;
//        double yv = 0;
//        for (int i = 0; i < arr.length ; i++) {
//            xv += (arr[i].x-xa)*(arr[i].x-xa);
//            yv += (arr[i].y-ya)*(arr[i].y-ya);
//        }
//        return
//    }


    private static void solution(Point[] arr, Point p, int k) {
        Point[] distance = new Point[arr.length];
        for (int i = 0; i < arr.length; i++) {
            Point point = new Point(arr[i].x, arr[i].y);
            point.distance = Math.pow(arr[i].x - p.x, 2) + Math.pow(arr[i].y - p.y, 2);
            distance[i] = point;
        }
        Arrays.sort(distance);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < k; i++) {
            builder.append(distance[i].toString()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());

    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-- > 0) {
                String[] line = reader.readLine().split(",");
                Point[] arr = new Point[line.length];
                for (int i = 0; i < line.length; i++) {
                    String[] point = line[i].split(" ");
                    arr[i] = new Point(Double.parseDouble(point[0]), Double.parseDouble(point[1]));
                }
                String[] line2 = reader.readLine().split(" ");
                Point p = new Point(Double.parseDouble(line2[0]), Double.parseDouble(line2[1]));
                int k = Integer.parseInt(reader.readLine());
                solution(arr, p, k);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
