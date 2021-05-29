package boj;

import java.io.*;
import java.util.*;

public class BOJ1504 {
	
	static class Pair implements Comparable<Pair> {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair o) {
			return this.first < o.first ? -1 : 1;
		}
	}
	
	public static final int IMPOSSIBLE = 700000000;
	public static int N,E, v1, v2;
	
	public static List<Pair>[] graph;
	
	public static int djikstra(int src, int dst) {
		
		int[] dist = new int[N];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		Arrays.fill(dist, IMPOSSIBLE);
		dist[src] = 0;

		pq.add(new Pair(0, src));
		
		while (!pq.isEmpty()) {
			
			Pair now = pq.poll();
			
			if (now.first > dist[now.second])
				continue;
			
			for (Pair nxt : graph[now.second]) {
				int nxtNode = nxt.first;
				int nxtWeight = now.first + nxt.second;
				
				if (nxtWeight < dist[nxtNode]) {
					dist[nxtNode] = nxtWeight;
					pq.add(new Pair(nxtWeight, nxtNode));
				}
			}
		}
		
		return dist[dst];
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nodeEdge = br.readLine().split(" ");
		
		N = Integer.parseInt(nodeEdge[0]);
		E = Integer.parseInt(nodeEdge[1]);
		
		graph = new LinkedList[N];
		
		for (int i = 0; i < N; ++i) {
			graph[i] = new LinkedList<>();
		}
		
		for (int loop = 0; loop < E; ++loop) {
			String[] edges = br.readLine().split(" ");
			int src = Integer.parseInt(edges[0]);
			int dst = Integer.parseInt(edges[1]);
			int weight = Integer.parseInt(edges[2]);
			
			graph[src-1].add(new Pair(dst-1, weight));
			graph[dst-1].add(new Pair(src-1, weight));
		}
		
		String[] nodes = br.readLine().split(" ");
		
		v1 = Integer.parseInt(nodes[0]);
		v2 = Integer.parseInt(nodes[1]);
		
		v1--;
		v2--;
		
		int result1 = djikstra(0, v1) + djikstra(v1, v2) + djikstra(v2, N-1);
		int result2 = djikstra(0, v2) + djikstra(v2, v1) + djikstra(v1, N-1);
		int result = Math.min(result1, result2);
		
		if (result >= IMPOSSIBLE) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}

}
