package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Pair {
		int first, second;
		
		Pair (int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static final int EMPTY = 0;
	static final int HOUSE = 1;
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	
	static int N;
	static int[][] graph;
	static boolean[][] visited;
	
	public static boolean isSafe(int y, int x) {
		return (0 <= y && y < N && 0 <= x && x < N);
	}
	
	public static int bfs(int y, int x) {

		int result = 1;
		Queue<Pair> q = new LinkedList<>();
		
		q.add(new Pair(y,x));
		
		while (!q.isEmpty()) {
			
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir) {
				int ny = now.first + dy[dir];
				int nx = now.second + dx[dir];
				
				if (isSafe(ny,nx) && !visited[ny][nx] && graph[ny][nx] == HOUSE) {
					visited[ny][nx] = true;
					q.add(new Pair(ny,nx));
					result++;
				}
			}
		}
		
		return result;
	}
	
	//BOJ2667
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		graph = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; ++i) {
			String row = br.readLine();
			
			for (int j = 0; j < N; ++j) {
				graph[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
			}
		}
		
		List<Integer> results = new ArrayList<>();
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				if (graph[i][j] == HOUSE && !visited[i][j]) {
					visited[i][j] = true;
					int result = bfs(i,j);
					results.add(result);
				}
			}
		}
		
		Collections.sort(results);
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(results.size());
		sb.append('\n');
		
		for (int componentSize : results) {
			sb.append(componentSize);
			sb.append('\n');
		}
		
		System.out.println(sb.toString());
	}
}

