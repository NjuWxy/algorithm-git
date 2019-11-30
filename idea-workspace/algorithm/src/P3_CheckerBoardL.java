import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**棋盘覆盖问题
 * fillCenter(int startX,int endX,int startY,int endY,Point sp,List<L> allL)
 *      注意递归跳出条件startX==endX||startX==endY
 *      sp表示空点，allL最终保存所有覆盖棋盘的L
 *      把圈定的范围分为四块，左上角，右上角，左下角，右下角，分别对每一个角进行处理
 *      比如对左上角：
 *          判断sp是否分布在左上角的区域（这个判断不仅要判断右端，也有判断左端），在则直接fillCenter
 *          不在的话，就把中心左上角的点加进覆盖中心的L里，然后再fillCenter，此时sp传为中心左上角的点
 *      最后把L加进allL
 * 得到allL,遍历allL,找寻目标L
 */
public class P3_CheckerBoardL {
    static class Point implements Comparable<Point>{
        int x;
        int y;
        public Point(int x,int y){
            this.x = x;
            this.y = y;
        }
        public boolean isSamePoint(Point p){
            return p.x==this.x && p.y == this.y;
        }

        @Override
        public String toString() {
            return x+" "+y;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x==o.x){
                return this.y-o.y;
            }else {
                return this.x-o.x;
            }
        }
    }
    static class L{
        List<Point> points = new ArrayList<>();
        public L(List<Point> points){
            this.points = points;
        }
        private boolean contains(Point destP){
            boolean res = false;
            for (Point p:points) {
                if(p.isSamePoint(destP)){
                    res = true;
                    break;
                }
            }
            return res;
        }
        private String getFriend(Point destP){ ;
            Collections.sort(points);
            StringBuilder stringBuilder = new StringBuilder();
            for (Point p:points) {
                if(!p.isSamePoint(destP)){
                    stringBuilder.append(p+",");
                }
            }
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
            return stringBuilder.toString();
        }
    }

    private static void fillCenter(int startX,int endX,int startY,int endY,Point sp,List<L> allL){
        if(startX==endX||startX==endY)return;
        int midX = (startX + endX) / 2;
        int midY = (startY + endY) / 2;
        List<Point> points = new ArrayList<>();
        //左上角
        if(startX<=sp.x&&sp.x<=midX && startY<=sp.y&&sp.y<=midY){
            fillCenter(startX,midX,startY,midY,sp,allL);
        }else {
            Point point = new Point(midX,midY);
            points.add(point);
            fillCenter(startX,midX,startY,midY,point,allL);
        }
        //右上角
        if(startX<=sp.x&&sp.x<=midX && sp.y>=midY+1 && sp.y<=endY){//如果缺失点在右上角
            fillCenter(startX,midX,midY+1,endY,sp,allL);
        }else {
            Point point = new Point(midX,midY+1);
            points.add(point);
            fillCenter(startX,midX,midY+1,endY,point,allL);
        }
        //左下角
        if(sp.x>=midX+1 && sp.x<=endX && startY<=sp.y&&sp.y<=midY){//如果缺失点在左下角
            fillCenter(midX+1,endX,startY,midY,sp,allL);
        }else {
            Point point = new Point(midX+1,midY);
            points.add(point);
            fillCenter(midX+1,endX,startY,midY,point,allL);
        }
        //右下角
        if(sp.x>=midX+1 && sp.x<=endX && sp.y>=midY+1 && sp.y<=endY){
            fillCenter(midX+1,endX,midY+1,endY,sp,allL);
        }else {
            Point point = new Point(midX+1,midY+1);
            points.add(point);
            fillCenter(midX+1,endX,midY+1,endY,point,allL);
        }
        allL.add(new L(points));
    }

    private static String solution(int n,Point sp,Point destP){
        int size = (int)Math.pow(2,n);
        List<L> allL = new ArrayList<>();
        fillCenter(0,size-1,0,size-1,sp,allL);
        for (L l:allL) {
            if(l.contains(destP)){
                return l.getFriend(destP);
            }
        }
        return "";
    }
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int t = Integer.parseInt(reader.readLine());
            while (t-->0){
                String[] strings1 = reader.readLine().split(" ");
                int n = Integer.parseInt(strings1[0]);
                Point sp = new Point(Integer.parseInt(strings1[1]),Integer.parseInt(strings1[2]));
                String[] strings2 = reader.readLine().split(" ");
                Point destP = new Point(Integer.parseInt(strings2[0]),Integer.parseInt(strings2[1]));
                System.out.println(solution(n,sp,destP));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
