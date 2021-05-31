package boj;

import java.io.*;
import java.util.*;

public class BOJ1238 {

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
	
	static int N,M,X;
	static List<Pair>[] graph, reverseGraph;
	
	static int[] djikstra(int partySpot, List<Pair>[] target) {

		int[] dist = new int[target.length];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[partySpot] = 0;
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(0, partySpot));
		
		while (!pq.isEmpty()) {
			Pair now = pq.poll();
			
			if (now.first > dist[now.second])
				continue;
			
			for (Pair p : target[now.second]) {
				int nxtNode = p.first;
				int nxtDist = p.second + now.first;
				
				if (nxtDist < dist[nxtNode]) {
					dist[nxtNode] = nxtDist;
					pq.add(new Pair(nxtDist, nxtNode));
				}
			}
		}
		
		return dist;
	}
	
	//BOJ1238
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		X = Integer.parseInt(info[2]);
		
		graph = new List[N];
		reverseGraph = new List[N];
		
		for (int i = 0; i < N; ++i) {
			graph[i] = new LinkedList<>();
			reverseGraph[i] = new LinkedList<>();
		}
		
		for (int i = 0; i  < M; ++i) {
			String[] info2 = br.readLine().split(" ");
			int src, dst, w;
			src = Integer.parseInt(info2[0]);
			dst = Integer.parseInt(info2[1]);
			w = Integer.parseInt(info2[2]);
			src--; dst--;
			
			graph[src].add(new Pair(dst, w));
			reverseGraph[dst].add(new Pair(src,w));
		}
		
		int[] dist1 = djikstra(X-1, graph);
		int[] dist2 = djikstra(X-1, reverseGraph);
		
		int answer = -1;
		for (int i = 0; i < N; ++i) {
			int candidate = dist1[i] + dist2[i];
			answer = Math.max(answer, candidate);
		}
		
		System.out.println(answer);
	}

}
