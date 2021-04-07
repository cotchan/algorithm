package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15650 {

	public static int N,C;
	public static int[] origin;
	public static boolean[] isSelected;
	public static int[] picked;
	public static List<int[]> results = new LinkedList<>();
	
	public static void combination(int idx, int pickCnt) {
				
		if (pickCnt == C)
		{
			int[] candidate = picked.clone();
			results.add(candidate);
			return;
		}
		
		if (idx == N)
		{
			return;
		}
		
		if (!isSelected[idx])
		{
			isSelected[idx] = true;
			picked[pickCnt] = origin[idx];
			combination(idx + 1, pickCnt + 1);
			isSelected[idx] = false;
		}
		
		combination(idx + 1, pickCnt);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		C = Integer.parseInt(info[1]);
		
		origin = new int[N];
		isSelected = new boolean[N];
		picked = new int[C];
		
		for (int i = 0; i < N; ++i)
			origin[i] = i + 1;
		
		combination(0,0);
		
		for (int[] result : results)
		{
			StringBuilder sb = new StringBuilder();
			
			for (int number : result)
			{
				sb.append(number);
				sb.append(" ");
			}
			
			bw.append(sb.toString());
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
