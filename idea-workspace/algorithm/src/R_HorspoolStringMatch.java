import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**字符串匹配问题
 * Given a text txt[0..n-1] and a pattern pat[0..m-1], write a function search(char pat[], char txt[])
 * that prints all occurrences of pat[] in txt[]. You may assume that n > m.
 *
 * 假设文本中，模式与文本对齐时，文本与模式最右端对齐的字符为c,Horspool算法根据c的不同情况来确定移动距离
 * 用i表示文本与模式最右端对齐的位置，k表示匹配的字符数，table[c]表示当遇到不匹配时的移动距离
 * 当遇到不匹配时，用文本与模式最右端对齐的字符c取table中查找移动距离，i加上这个距离
 */
public class R_HorspoolStringMatch {
	private static Map<Character,Integer> createTable(String pattern){
		Map<Character,Integer> table = new HashMap<>();
		for (int i = 0; i < pattern.length()-1 ; i++) {
			char c = pattern.charAt(i);
			int d = pattern.length()-1-i;
			if(table.containsKey(c)){
				table.replace(c,d);
			}else {
				table.put(c,d);
			}
		}
		return table;
	}

	private static void solution(String text,String pattern) {
		Map<Character, Integer> table = createTable(pattern);
		List<Integer> resList = new ArrayList<Integer>();
		int m=pattern.length(),n=text.length();
		int i = m-1;
		while (i<=n-1){
			int k=0;
			while (k<=m-1 && text.charAt(i-k)==pattern.charAt(m-1-k)){
				k++;
			}
			if(k==m){
				resList.add(i-m+1);
				i++;
			}else {
				char c = text.charAt(i);
				int d = table.getOrDefault(c,m);
				i+=d;
			}
		}
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
