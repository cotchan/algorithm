package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Pair {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static final int POSITION_MAX = 100001;
	static int[] parent = new int[POSITION_MAX];
	
	public static boolean isSafe(int x) {
		return 0 <= x && x < POSITION_MAX;
	}
	
	public static int[] getNxtPos(int pos) {
		int[] result = {pos-1,pos+1,2*pos};
		return result;
	}
	
	public static int bfs(int st, int en) {
		
		boolean[] visited = new boolean[POSITION_MAX];
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(st,0));
		
		while (!q.isEmpty()) {
			
			Pair now = q.poll();
			
			if (now.first == en) {
				return now.second;
			}
			
			int[] nxtPos = getNxtPos(now.first);
			
			for (int pos : nxtPos) {
				if (isSafe(pos) && !visited[pos]) {
					visited[pos] = true;
					parent[pos] = now.first;
					q.add(new Pair(pos,now.second + 1));
				}
			}
		}
		
		return -1;
	}
	
	//BOJ13913
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n,k;
		String[] pos = br.readLine().split(" ");
		n = Integer.parseInt(pos[0]);
		k = Integer.parseInt(pos[1]);
		
		System.out.println(bfs(n,k));
		StringBuilder sb = new StringBuilder();
		
		int now = k;
		List<Integer> result = new ArrayList<>();
		while (now != n) {
			result.add(now);
			now = parent[now];
		}
		result.add(n);
		Collections.reverse(result);
		
		for (int position : result) {
			sb.append(position);
			sb.append(" ");
		}
		
		System.out.println(sb.toString());
	}
}
