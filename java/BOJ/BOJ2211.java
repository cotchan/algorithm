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
			return this.first < o.first ? -1 : 1;
		}
	}
	
	static int N,M;
	static List<Pair>[] graphs;
	static HashMap<Integer,Pair> edgeMap = new HashMap<>();
	
	public static void djikstra() {
		
		int[] dist = new int[N];
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[0] = 0;
		pq.add(new Pair(0,0));
	
		while (!pq.isEmpty()) {
		
			Pair now = pq.poll();
			
			if (dist[now.second]< now.first) {
				continue;
			}
			
			for (Pair nxt : graphs[now.second]) {
				int nxtNode = nxt.first;
				int nxtWeight = now.first + nxt.second;
				
				if (dist[nxtNode] > nxtWeight) {
					dist[nxtNode] = nxtWeight;
					pq.add(new Pair(nxtWeight,nxtNode));
					edgeMap.put(nxtNode, new Pair(now.second,nxtNode));
				}
			}
		}
	}
	
	//BOJ2211
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		graphs = new LinkedList[N];
		for (int i = 0; i < N; ++i) {
			graphs[i] = new LinkedList<>();
		}
		
		for (int loop = 0; loop < M; ++loop) {
			String[] edges = br.readLine().split(" ");
			int src = Integer.parseInt(edges[0]);
			int dst = Integer.parseInt(edges[1]);
			int weight = Integer.parseInt(edges[2]);
			
			src--; dst--;
			graphs[src].add(new Pair(dst,weight));
			graphs[dst].add(new Pair(src,weight));
		}
		
		djikstra();
		bw.append(String.valueOf(edgeMap.size()));
		bw.newLine();
		
		for (Map.Entry<Integer, Pair> entry : edgeMap.entrySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(entry.getValue().first + 1);
			sb.append(" ");
			sb.append(entry.getValue().second + 1);
			bw.append(sb.toString());
			bw.newLine();
		}
		
		bw.flush();
		
	}
}
