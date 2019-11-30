import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 分治法解最近对问题
 * 对每一个用例输出两个距离最近的点（坐标使用空格隔开），用逗号隔开，先按照第一个坐标大小排列，再按照第二个坐标大小排列。
 * 如果有多个解，则按照每个解的第一个点的坐标排序，连续输出多个解，用逗号隔开。
 *
 * 算中间的点对时，两个点分布在中线的两边才放进去
 */
public class R_NearestPair {
    static class Point implements Comparable<Point> {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public Point(String string) {
            String[] dimension = string.split("\\s+");
            this.x = Double.parseDouble(dimension[0]);
            this.y = Double.parseDouble(dimension[1]);
        }

        @Override
        public String toString() {
            String xString = (int) x == x ? (int) x + "" : x + "";
            String yString = (int) y == y ? (int) y + "" : y + "";
            return xString + " " + yString;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return Double.compare(this.y, o.y);
            } else {
                return Double.compare(this.x, o.x);
            }
        }

        public boolean isSamePoint(Point point) {
            return this.x == point.x && this.y == point.y;
        }
    }

    static class Pair implements Comparable<Pair> {
        Point a;
        Point b;
        double distance;

        public Pair(Point a, Point b, Double distance) {
            this.a = a;
            this.b = b;
            if (distance == null) {
                this.distance = computeDistance(a, b);
            } else {
                this.distance = distance;
            }
        }

        public static double computeDistance(Point a, Point b) {
            return Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2);
        }

        @Override
        public String toString() {
            Point[] points = new Point[]{a, b};
//            Arrays.sort(points);
            return points[0] + "," + points[1];
        }

        @Override
        public int compareTo(Pair o) {
            if (this.a.compareTo(o.a) == 0) {
                return this.b.compareTo(o.b);
            } else {
                return this.a.compareTo(o.a);
            }

        }
    }

    private static void solution(List<Point> points) {
        Collections.sort(points);
        LinkedList<Pair> minDistancePairs = new LinkedList<>();
        double d = findMinDistancePairs(points, Double.MAX_VALUE, minDistancePairs);
        List<Pair> dPairs = new ArrayList<>();
        for (Pair pair : minDistancePairs) {
            if (pair.distance == d) {
                dPairs.add(pair);
            }
        }
        Collections.sort(dPairs);
        StringBuilder builder = new StringBuilder();
        for (Pair pair : dPairs) {
            builder.append(pair.toString()).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        System.out.println(builder.toString());
    }

    private static double findMinDistancePairs(List<Point> points, double minDistance, LinkedList<Pair> minDistancePairs) {
        if (points.size() <= 1) {
            return minDistance;
        }
        if (points.size() == 2) {
            Pair pair = new Pair(points.get(0), points.get(1), null);
            if (pair.distance <= minDistance) {
                minDistancePairs.add(pair);
                return pair.distance;
            }
            return minDistance;
        }
        double d = minDistance;
        double leftMinDistance = findMinDistancePairs(points.subList(0, points.size() / 2 + 1), d, minDistancePairs);
        double rightMinDistance = findMinDistancePairs(points.subList(points.size() / 2, points.size()), d, minDistancePairs);
        d = Math.min(rightMinDistance, leftMinDistance);

        double midX = points.get(points.size() / 2).x;
        List<Point> midRangePoints = new ArrayList<>();
        for (Point point : points) {
            if (Math.abs(point.x - midX) <= d) {
                midRangePoints.add(point);
            }
        }
        for (int i = 0; i < midRangePoints.size() - 1; i++) {
            Point a = midRangePoints.get(i), candidate = null;
            for (int j = i + 1; j < midRangePoints.size(); j++) {
                Point b = midRangePoints.get(j);
                if (Math.abs(a.y - b.y) <= d) {
                    double distance = Pair.computeDistance(a, b);
                    if (distance <= d && (a.x - midX) * (b.x - midX) < 0) {//!!!算中间的点对时，两个点分布在中线的两边才放进去
                        d = distance;
                        candidate = b;
                    }
                } else {
                    break;
                }
            }
            if (candidate != null) {
                Pair pair = new Pair(a, candidate, d);
                minDistancePairs.add(pair);
            }
        }
        return d;
    }


    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-- > 0) {
                String[] line = reader.readLine().split(",");
                List<Point> points = new ArrayList<>();
                for (int i = 0; i < line.length; i++) {
                    points.add(new Point(line[i]));
                }
                solution(points);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
