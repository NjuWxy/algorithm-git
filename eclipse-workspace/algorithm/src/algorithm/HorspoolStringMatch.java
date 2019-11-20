package algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class HorspoolStringMatch {
	private static void solution(String text,String pattern) {
		//System.out.println(text);
		//System.out.println(pattern);
		Map<Character, Integer> table = new HashMap<Character, Integer>();
		List<Integer> resList = new ArrayList<Integer>(); 
		for (int i = 0; i < pattern.length()-1; i++) {
			char c = pattern.charAt(i);
			int d = pattern.length()-1-i;
			if(table.containsKey(c)) {
				table.replace(c, d);
			}else {
				table.put(c, d);
			}
		}
		int p = 0;
		while (p<=text.length()-pattern.length()) {
			int i=pattern.length()-1,j=p+pattern.length()-1;
			while (i>=0) {
				if(text.charAt(j)==pattern.charAt(i)) {
					i--;
					j--;
				}else {
					int d = pattern.length();
					if(table.containsKey(text.charAt(j))) {
						d = table.get(text.charAt(j));
					}
					p+=d;
					break;
				}
			}
			if(i<0) {
				resList.add(p);
				p++;
			}
			
		}
		//System.out.println(resList.size());
		print(resList);
		
	}
	private static void print(List<Integer> resList) {
		if(resList.size()==0) {
			System.out.println();
		}else {
			for (int i = 0; i < resList.size()-1; i++) {
				System.out.print(resList.get(i)+" ");
			}
			System.out.println(resList.get(resList.size()-1));
		}
	}
	public static void main(String[] args) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t;
		try {
			t = Integer.parseInt(reader.readLine());
			for (int i = 0; i < t; i++) {
				String string = reader.readLine();
				String text = string.split(",")[0];
				String pattern = string.split(",")[1];
				solution(text, pattern);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
