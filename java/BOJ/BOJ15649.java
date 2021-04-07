package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15649 {

	public static int N, C;
	public static int origin[], result[];
	public static boolean isSelected[];
	public static List<int[]> results = new LinkedList<>();
	
	public static void main(String arg[]) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String input[] = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		origin = new int[N];
		isSelected = new boolean[N];
		result = new int[C];
		
		for (int i = 0; i < N; ++i)
			origin[i] = i+1;
		
		permutation(0);
		
		for (int[] resultList : results)
		{
			StringBuilder sb = new StringBuilder();
			
			for (int num : resultList)
			{
				sb.append(num);
				sb.append(" ");
			}
			
			bw.append(sb.toString());
			bw.newLine();
		}
		
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	private static void permutation(int idx){
		if(idx == C){
			int[] candiate = result.clone();
			results.add(candiate);
			return;
		}
		// 해당 자리에 뽑을 가능한 모든 수에 대해 시도
		for(int i = 0; i < N; i++){
			if(isSelected[i]) continue;
			result[idx] = origin[i];
			isSelected[i] = true;
			permutation(idx + 1); // 다음 자리의 순열 뽑기
			isSelected[i] = false;
		}
	}    
}
