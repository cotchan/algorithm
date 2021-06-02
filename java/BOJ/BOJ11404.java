package boj;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int INF = 1000000000;
	static int N, M;
	static int[][] graphs;
	
	//BOJ11404
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		graphs = new int[N][N];
		
		for (int i = 0; i < N; ++i) {
			Arrays.fill(graphs[i], INF);
		}
		
		for (int loop = 0; loop < M; ++loop) {
			String[] info = br.readLine().split(" ");
			
			int a,b,c;
			a = Integer.parseInt(info[0]);
			b = Integer.parseInt(info[1]);
			c = Integer.parseInt(info[2]);
			
			a--; b--;
			graphs[a][b] = Math.min(graphs[a][b], c);
		}
		
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (graphs[i][k] + graphs[k][j] < graphs[i][j]) {
						graphs[i][j] = graphs[i][k] + graphs[k][j]; 
					}
				}
			}
		}
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int result = 0;
				if (i != j) {
					result = (graphs[i][j] == INF ? 0 : graphs[i][j]);
				} else {
				}
				String value = result + "";
				bw.append(value);
				bw.append(' ');
			}
			bw.newLine();
		}
		
		bw.flush();
	}
}
