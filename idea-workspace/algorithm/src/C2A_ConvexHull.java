import java.util.*;

/**点的凸包
 * Convex Hull of a set of points, in 2D plane, is a convex polygon with minimum area such that each point lies either
 * on the boundary of polygon or inside it. Now given a set of points the task is to find the convex hull of points.
 *
 * 首先对所有点排序，找出最左边的点和最右边的点，allPoints最初为所有的点去掉p1,p2, 作为计算凸包起始参数
 * findFarthestPoint(Point p1, Point p2, List<Point> allPoints, List<Point> hullPoints, POS_TYPE posType)
 *      allPoints表示在p1,p2一端的点，hullPoints表示凸包结果点，posType表示在p1,p2上方还是下方
 *      linePoints记录与p1,p2在同一直线的点,sidePoints记录在posType一端的点
 *      遍历allPoints里的点p3,计算p1,p2,p3组成的三角形面积，
 *          >0表示p3在p1,p2上方，<0表示在下方，=0表示在p1,p2这条直线上。对应加进linePoints和sidePoints
 *      如果没有sidePoints了，直接把linePoints加进hullPoints里
 *      反之，sidePoints去除farthestPoint作为下一次的allPoints，
 *      分别调用findFarthestPoint(p1,farthestPoint....)
 *      和findFarthestPoint(farthestPoint,p2....)
 */
public class C2A_ConvexHull {
    static class Point{
        int x;
        int y;
        Point(int x,int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }
    }
    private static void solution(Point[] points){
        if(points.length<=2){
            print(Arrays.asList(points));
            return;
        }
        Comparator<Point> xComparator = new Comparator<Point>() {
            //todo x相等的时候按照y排序？
            public int compare(Point o1, Point o2) {
                if(o1.x==o2.x){
                    return o1.y - o2.y;
                }else {
                    return o1.x-o2.x;
                }
            }
        };
        Arrays.sort(points,xComparator);
        Point p1 = points[0];
        Point p2 = points[points.length-1];
        List<Point> hullPoints = new ArrayList<Point>();
        hullPoints.add(p1);
        hullPoints.add(p2);
        List<Point> allPoints = Arrays.asList(points).subList(1,points.length-1);
        findFarthestPoint(p1,p2,allPoints,hullPoints,true);
        findFarthestPoint(p1,p2,allPoints,hullPoints,false);
        Collections.sort(hullPoints,xComparator);
//        System.out.println();
        print(hullPoints);
    }

    private static void findFarthestPoint(Point p1, Point p2, List<Point> allPoints, List<Point> hullPoints, boolean above){
//        System.out.println("allPoints.size():  "+allPoints.size());
//        System.out.println(p1+", "+p2);
        List<Point> sidePoints = new ArrayList<Point>();
        List<Point> linePoints = new ArrayList<Point>();
        int maxArea = 0;
        int farthestPIndex = -1;
        for(int i=0;i<allPoints.size();i++){
            Point p3 = allPoints.get(i);
            int area = p1.x*p2.y + p1.y*p3.x + p2.x*p3.y - (p3.x*p2.y + p3.y*p1.x + p1.y*p2.x);
            if(area>0 && above){
                sidePoints.add(p3);
                if(area>maxArea){
                    maxArea = area;
                    farthestPIndex = sidePoints.size()-1;
                }
            }else if(area<0 && !above){
                sidePoints.add(p3);
                if(Math.abs(area)>maxArea){
                    maxArea = Math.abs(area);
                    farthestPIndex = sidePoints.size()-1;
                }
            }else if(area==0){
                linePoints.add(p3);
            }
        }
        if(sidePoints.size()==0){
            hullPoints.addAll(linePoints);
        }else {
            if(farthestPIndex!=-1){
                Point p3 = sidePoints.get(farthestPIndex);
//            System.out.println("------>  "+p3);
                hullPoints.add(p3);
                sidePoints.remove(farthestPIndex);
                if(sidePoints.size()>0){
                    findFarthestPoint(p1,p3,sidePoints,hullPoints,above);
                    findFarthestPoint(p3,p2,sidePoints,hullPoints,above);
                }
            }
        }
    }


    private static void print(List<Point> points){
        if(points.size()==0){
            System.out.println(-1);
            return;
        }
        for(int i=0;i<points.size()-1;i++){
            System.out.print(points.get(i).toString()+", ");
        }
        System.out.println(points.get(points.size()-1).toString());
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            int len = scanner.nextInt();
            Point[] points = new Point[len];
            for (int j = 0; j < len; j++) {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                points[j] = new Point(x,y);
            }
            solution(points);
        }
    }
    //2 2 2 3 3 1 3 2 3 4 4 3 4 5 5 2 5 4 6 3

}
