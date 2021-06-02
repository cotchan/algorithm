package boj;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N,M;
	static int[] parents;
	
	public static int find(int node) {
		if (parents[node] == node) {
			return node;
		} else {
			return parents[node] = find(parents[node]);
		}
	}
	
	public static void union(int c1, int c2) {
		parents[c2] = c1;
	}
	
	//BOJ1717
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		parents = new int[N+1];
		
		for (int idx = 0 ; idx <= N; ++idx) {
			parents[idx] = idx;
		}
		
		for (int loop = 0; loop < M; ++loop) {
			String[] oper = br.readLine().split(" ");
			
			int t,a,b;
			t = Integer.parseInt(oper[0]);
			a = Integer.parseInt(oper[1]);
			b = Integer.parseInt(oper[2]);
			
			if (t == 0) {
				int c1 = find(a);
				int c2 = find(b);
				union(c1,c2);
			} else {
				int c1 = find(a);
				int c2 = find(b);
				String result = (c1 == c2 ? "YES" : "NO");
				bw.append(result);
				bw.newLine();
			}
		}
		
		bw.flush();
	}
}
