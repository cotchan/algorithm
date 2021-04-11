package BOJ;

import java.io.*;
import java.util.*;

public class BOJ15654 {
	
	static int N,M;
	static int[] origin, picks;
	static boolean[] isUsed;
	static List<int[]> results = new LinkedList<>();
	
	static void permutation(int pickCnt) {
		
		if (pickCnt == M) {
			int[] candidate = picks.clone();
			results.add(candidate);
			return;
		}
		
		for (int i = 0; i < N; ++i) {
			if (isUsed[i])
				continue;
			
			isUsed[i] = true;
			picks[pickCnt] = origin[i];
			permutation(pickCnt + 1);
			isUsed[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");

		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);

		String[] numbers = br.readLine().split(" ");
		
		origin = new int[N];
		isUsed = new boolean[N];
		picks = new int[M];
		
		for (int i = 0; i < N; ++i)
		{
			origin[i] = Integer.parseInt(numbers[i]);
		}
		
		Arrays.sort(origin);
		
		permutation(0);

		for (int[] candidate : results)
		{
			StringBuilder sb = new StringBuilder();
			
			for (int pickNumber : candidate)
			{
				sb.append(pickNumber);
				sb.append(" ");
			}
			
			bw.append(sb.toString());
			bw.newLine();
		}
		
		bw.flush();
		
	}

}
