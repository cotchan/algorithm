package boj;

import java.io.*;
import java.util.*;

public class BOJ12851 {
	
	static final int LOWER_BOUND = 0;
	static final int UPPER_BOUND = 100000;
	
	static class Pair {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static int[] getNxtPosition(int offset) {
		int[] result = {-1,1,offset}; 
		return result;
	}
	
	static boolean isSafe(int pos) {
		return (LOWER_BOUND <= pos) && (pos <= UPPER_BOUND);
	}
	
	static Pair bfs(int n, int k) {
		
		int result = 0;
		int visitedTime = Integer.MAX_VALUE;
		
		int[] visited = new int[UPPER_BOUND + 1];
		Arrays.fill(visited, Integer.MAX_VALUE);
		visited[n] = 0;
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(0, n));	//0초에 n번째 위치
		
		while (!q.isEmpty()) {
			
			Pair now = q.poll();
			
			if (now.first > visitedTime)
				break;

			if (now.second == k && now.first <= visitedTime) {
				visitedTime = now.first;
				result++;
			}
			else {
				for (int pos : getNxtPosition(now.second)) {
					int nxtPos = now.second + pos;
					if (isSafe(nxtPos) && now.first + 1 <= visited[nxtPos]) {
						visited[nxtPos] = now.first + 1;
						q.add(new Pair(now.first + 1, nxtPos));
					} 
				}
			}
		}
		
		return new Pair(visitedTime, result);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		
		int n = Integer.parseInt(info[0]);
		int k = Integer.parseInt(info[1]);
		
		Pair result = bfs(n,k);
		
		System.out.println(result.first);
		System.out.println(result.second);
	}

}
