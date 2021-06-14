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
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	static final int WHITE = 0;
	static final int BLUE = 1;
	
	static int n,m;
	static int[][] board;
	static boolean[][] visited;
	
	public static boolean isSafe(int y, int x) {
		return (0 <= y && y < n && 0 <= x && x < m);
	}
	
	public static Pair bfs(int y, int x) {
		
		Queue<Pair> q = new LinkedList<>();
		int result = 1;
		int type = board[y][x];
		
		q.add(new Pair(y,x));
		
		while (!q.isEmpty()) {
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir) {
				int ny = now.first + dy[dir];
				int nx = now.second + dx[dir];
				
				if (isSafe(ny,nx) && !visited[ny][nx] && board[ny][nx] == type) {
					visited[ny][nx] = true;
					result += 1;
					q.add(new Pair(ny,nx));
				}
			}
		}
		
		return new Pair(type,result*result);
	}
	
	//BOJ13913
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		m = Integer.parseInt(info[0]);
		n = Integer.parseInt(info[1]);
		
		board = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i = 0; i < n; ++i) {
			String row = br.readLine();
			for (int j = 0; j < m; ++j) {
				if (row.charAt(j) == 'W') {
					board[i][j] = WHITE;
				} else {
					board[i][j] = BLUE;
				}
			}
		}
		
		int[] results = new int[2];
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (!visited[i][j]) {
					visited[i][j] = true;
					//bfs(i,j);
					Pair result = bfs(i,j);
					results[result.first] += result.second;
				}
			}
		}
		
		System.out.print(results[0]);
		System.out.print(" ");
		System.out.print(results[1]);
	}
}
