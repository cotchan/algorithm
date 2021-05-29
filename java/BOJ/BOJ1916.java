package boj;

import java.io.*;
import java.util.*;

public class BOJ1916 {
	
	static int N,M;
	static List<Pair>[] graph;
	
	static class Pair implements Comparable<Pair> {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			return this.first < o.first ? -1 : 1;
		}
	}
	
	static int djikstra(int src, int dst) {
		
		int[] dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		dist[src] = 0;
		pq.add(new Pair(0, src));
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			
			if (now.first > dist[now.second])
				continue;
			
			for (Pair p : graph[now.second]) {
				int nxtNode = p.first;
				int nxtWeight = p.second + now.first;
				
				if (nxtWeight < dist[nxtNode]) {
					dist[nxtNode] = nxtWeight;
					pq.add(new Pair(nxtWeight,nxtNode));
				}
			}
		}
		
		return dist[dst];
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new List[N];
		
		for (int i = 0; i < N; ++i) {
			graph[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; ++i) {
			String[] info = br.readLine().split(" ");
			int src = Integer.parseInt(info[0]);
			int dst = Integer.parseInt(info[1]);
			src--; dst--;
			int weight = Integer.parseInt(info[2]);
			
			graph[src].add(new Pair(dst, weight));
		}
		
		String[] info = br.readLine().split(" ");
		int src = Integer.parseInt(info[0]);
		int dst = Integer.parseInt(info[1]);
		System.out.println(djikstra(src-1,dst-1));
	}
}
