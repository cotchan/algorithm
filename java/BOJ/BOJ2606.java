package boj;

import java.io.*;
import java.util.*;

class Main {

	static List<Integer>[] graph;
	
	static int computerCnt, connectionCnt;

	
	
	//BOJ2606
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		computerCnt = Integer.parseInt(br.readLine());
		connectionCnt = Integer.parseInt(br.readLine());
		
		graph = new List[computerCnt];
		for (int i = 0; i < computerCnt; ++i) {
			graph[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < connectionCnt; ++i) {
			String[] edge = br.readLine().split(" ");
			
			int src = Integer.parseInt(edge[0]);
			int dst = Integer.parseInt(edge[1]);
			src--; dst--;
			
			graph[src].add(dst);
			graph[dst].add(src);
		}
		
		int result = 0;
		boolean[] visited = new boolean[computerCnt];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(0);
		visited[0] = true;
		
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int nxt : graph[now]) {
				if (!visited[nxt]) {
					visited[nxt] = true;
					q.add(nxt);
					result += 1;
				}
			}
		}
		
		System.out.println(result);
	}
}

