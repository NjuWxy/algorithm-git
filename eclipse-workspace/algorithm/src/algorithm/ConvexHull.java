package algorithm;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ConvexHull {
	static class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	
	private static void solution(Point[] points) {
		Comparator<Point> xComparator = new Comparator<ConvexHull.Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				// TODO Auto-generated method stub
				return o1.x-o2.x;
			}
		};
		Arrays.sort(points, xComparator);
		
	}
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int len = scanner.nextInt();
			Point[] points = new Point[len];
			for (int j = 0; j < points.length; j++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();
				points[j] = new Point(x, y);
			}
			solution(points);
		}
	}

}
