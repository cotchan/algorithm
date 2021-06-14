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
	
	public static boolean isSafe(int pos) {
		return 0 <= pos && pos <= 100000;
	}
	
	public static int djikstra(int src, int dst) {
		
		int answer = -1;
		int[] dist = new int[100001];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		dist[src] = 0;
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		pq.add(new Pair(0, src));
		
		while (!pq.isEmpty()) {
			
			Pair now = pq.poll();
			
			if (dist[now.second] < now.first) {
				continue;
			}
			
			if (now.second == dst) {
				answer = now.first;
				break;
			}
			
			int nxtPos = now.second - 1;
			
			if (isSafe(nxtPos)) {
				if (dist[nxtPos] > now.first + 1) {
					dist[nxtPos] = now.first + 1;
					pq.add(new Pair(now.first + 1, nxtPos));
				}
			}
			
			nxtPos = now.second + 1;
			
			if (isSafe(nxtPos)) {
				if (dist[nxtPos] > now.first + 1) {
					dist[nxtPos] = now.first + 1;
					pq.add(new Pair(now.first + 1, nxtPos));
				}
			}
			
			nxtPos = 2 * now.second;
			
			if (isSafe(nxtPos)) {
				if (dist[nxtPos] > now.first) {
					dist[nxtPos] = now.first;
					pq.add(new Pair(now.first, nxtPos));
				}
			}
		}
		
		return answer;
	}
	
	//BOJ13549
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] pos = br.readLine().split(" ");
		
		int subin, subinBro;
		subin = Integer.parseInt(pos[0]);
		subinBro = Integer.parseInt(pos[1]);
		
		System.out.println(djikstra(subin, subinBro));
	}
}
