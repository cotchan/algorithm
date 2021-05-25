package boj;

import java.io.*;
import java.util.*;

public class BOJ1753 {

	static int INF = Integer.MAX_VALUE;
	static int V,E;
	static List<Pair>[] graph;
	
	static class Pair implements Comparable<Pair> {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.first < o.first) return -1;
			else return 1;
		}
	}
	
	static int[] djikstra(int src) {
	
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		int[] dist = new int[V];
		Arrays.fill(dist, INF);
		dist[src] = 0;
		pq.add(new Pair(0,src));
		
		while (!pq.isEmpty()) {
			
			Pair now = pq.poll();
			
			if (now.first > dist[now.second])
				continue;
			
			for (Pair p : graph[now.second]) {
				int nxtNode = p.first;
				int nxtWeight = p.second + now.first;	
				
				if (dist[nxtNode] > nxtWeight) {
					dist[nxtNode] = nxtWeight;
					pq.add(new Pair(nxtWeight, nxtNode));
				}
			}
		}
		
		return dist;
		
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");

		V = Integer.parseInt(info[0]);
		E = Integer.parseInt(info[1]);
		
		int start = Integer.parseInt(br.readLine());
		start--;
		
		graph = new List[V];
		
		for (int i = 0; i < V; ++i) {
			graph[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < E; ++i) {
			String[] edge = br.readLine().split(" ");
			
			int src = Integer.parseInt(edge[0]);
			int dst = Integer.parseInt(edge[1]);
			int weight = Integer.parseInt(edge[2]);
			
			src--; dst--;
			graph[src].add(new Pair(dst, weight));
		}
		
		int[] result = djikstra(start);
		
		for (int dist : result) {
			
			StringBuilder sb = new StringBuilder();
			
			if (dist == INF) {
				sb.append("INF");
			} else {
				sb.append(dist);
			}
			
			bw.append(sb.toString());
			bw.newLine();
			
		}
		
		bw.flush();
	}

}
