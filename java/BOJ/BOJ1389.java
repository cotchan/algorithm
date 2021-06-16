package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Pair implements Comparable<Pair> {
		int first, second;
		Pair(int f, int s) {
			first = f;
			second = s;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (first == o.first) {
				if (second < o.second) return -1;
				else if (second == o.second) return 0;
				else return 1;
			} else {
				if (first < o.first) return -1;
				else if (first == o.first) return 0;
				else return 1;
			}
		}
	}
	
	static final int IMPOSSIBLE = 700000000;
	static int N,M;
	
	static int[][] graph;
	
	//BOJ2667
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		graph = new int[N][N];
		for (int i = 0; i < N; ++i) {
			Arrays.fill(graph[i], IMPOSSIBLE);
		}
		
		for (int loop = 0; loop < M; ++loop) {
			
			String[] friendShip = br.readLine().split(" ");
			int a = Integer.parseInt(friendShip[0]);
			int b = Integer.parseInt(friendShip[1]);
			
			graph[a-1][b-1] = 1;
			graph[b-1][a-1] = 1;
		}
		
		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}
		
		List<Pair> results = new LinkedList<>();

		for (int idx = 0; idx < N; ++idx) {
			int kevinCnt = 0;
			for (int j = 0; j < N; ++j) {
				if (idx == j) {
					continue;
				} else {
					kevinCnt += graph[idx][j];
				}
			}
			results.add(new Pair(kevinCnt, idx));
		}
		
		Collections.sort(results);
		System.out.println(results.get(0).second + 1);
	}
}

