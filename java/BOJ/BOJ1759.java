package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1759 {
	
	static int L,C;
	static Set<Character> vowels = new LinkedHashSet<>();
	static String origin;
	static char[] candidate;
	static boolean[] isSelected;
	static List<String> answers = new LinkedList<>();
	
	public static boolean isAnswer() {
		int consonantCnt, vowCnt = 0;
		
		for (char alphabet : candidate)
		{
			if (vowels.contains(alphabet))
				vowCnt += 1;
		}

		consonantCnt = candidate.length - vowCnt;
		
		return (consonantCnt >= 2) && (vowCnt >= 1);
	}
	
	public static void combination(int idx, int pickCnt) {
	
		if (pickCnt == L)
		{
			if (isAnswer()) {
				String answer = String.valueOf(candidate);
				answers.add(answer);
			}
			
			return;
		}
		else if (idx == C)
		{
			return;
		}
		
		int alphabetIdx = (int)origin.charAt(idx) - (int)'a';	
		
		if (!isSelected[alphabetIdx])
		{
			isSelected[alphabetIdx] = true;
			candidate[pickCnt] = origin.charAt(idx);
			combination(idx+1,pickCnt+1);
			isSelected[alphabetIdx] = false;
		}
		
		combination(idx + 1, pickCnt);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] alphabetNumbers = br.readLine().split(" ");
		String[] alphabets = br.readLine().split(" ");
		Arrays.sort(alphabets);
		
		L = Integer.parseInt(alphabetNumbers[0]);
		C = Integer.parseInt(alphabetNumbers[1]);
	
		StringBuilder sb = new StringBuilder();

		for (String alphabet : alphabets)
		{
			sb.append(alphabet);
		}
		
		origin = sb.toString();
		
		isSelected = new boolean[26];
		candidate = new char[L];
		
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		
		combination(0,0);
		
		for (String answer : answers)
		{
			bw.append(answer);
			bw.newLine();
		}
		
		bw.flush();
	}
}
