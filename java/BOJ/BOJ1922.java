package boj;

import java.io.*;
import java.util.*;

public class BOJ1922 {

	static class Tuple implements Comparable<Tuple> {
		int first, second, third;
		
		Tuple(int f, int s, int t) {
			first = f;
			second = s;
			third = t;
		}
		
		@Override
		public int compareTo(Tuple o) {
			return this.first < o.first ? -1 : 1;
		}
	}
	
	static int N, M;
	static PriorityQueue<Tuple> edges = new PriorityQueue<>();
	static int[] parent;
	
	public static void union(int a, int b) {
		parent[b] = a;
	}
	
	public static int find(int node) {
		if (node == parent[node])
			return node;
		else {
			return parent[node] = find(parent[node]);
		}
	}
	
	public static int kruskal() {
		
		int result = 0;
		int edgeCnt = 0;
		
		while (edgeCnt != N-1) {

			Tuple now = edges.poll();
			
			int component1 = find(now.second);
			int component2 = find(now.third);
			
			if (component1 == component2) {
				continue;
			} else {
				union(component1, component2);
				result += now.first;
				edgeCnt += 1;
			}
		}
		
		return result;
	}
	
	//BOJ1922
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		parent = new int[N];
		
		for (int idx = 0; idx < N; ++idx) {
			parent[idx] = idx;
		}
		
		for (int loop = 0; loop < M; ++loop) {
			String[] info = br.readLine().split(" ");
			
			int a = Integer.parseInt(info[0]) - 1;
			int b = Integer.parseInt(info[1]) - 1;;
			int cost = Integer.parseInt(info[2]);
			
			edges.add(new Tuple(cost, a, b));
		}
		
		System.out.println(kruskal());
	}

}
