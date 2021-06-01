package boj;

import java.io.*;
import java.util.*;

public class BOJ2252 {

	static int N,M;
	static int[] parents;
	static List<Integer>[] graph;
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	
	public static int findNxt() {
		for (int idx = 0; idx < N; ++idx) {
			if (parents[idx] == 0) {
				return idx;
			}
		}
		return -1;
	}
	
	//BOJ2252
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
	
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		parents = new int[N];
		graph = new List[N];
		
		for (int i = 0; i < N; ++i) {
			graph[i] = new LinkedList<>();
		}
		
		for (int loop = 0; loop < M; ++loop) {
			String[] students = br.readLine().split(" ");
			int a = Integer.parseInt(students[0]);
			int b = Integer.parseInt(students[1]);
			parents[b-1]++;
			graph[a-1].add(b-1);
		}
		
		int[] result = new int[N];
		
		int idx = 0;
		int now = -1;
		
		while ((now = findNxt()) != -1) {
			
			for (Integer nxt : graph[now]) {
				parents[nxt]--;
			}
			
			parents[now] = -1;
			result[idx] = now;
			idx++;
		}
		
		//print result
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int student : result) {
			bw.append((student + 1) + "");
			bw.append(' ');
		}
		
		bw.flush();
	}

}
