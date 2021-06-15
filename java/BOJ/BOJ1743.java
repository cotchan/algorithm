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
	
	static final int[] DY = {-1,1,0,0};
	static final int[] DX = {0,0,-1,1};
	static final int EMPTY = 0;
	static final int TRASH = 1;
	
	static int n,m,k;
	static int[][] board;
	static boolean[][] visited;
	
	public static boolean isSafe(int y, int x) {
		return (0 <= y && y < n && 0 <= x && x < m);
	}
	
	public static int bfs(int y, int x) {
	
		int result = 1;
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(y,x));
		
		while (!q.isEmpty()) {
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir) {
				int ny = now.first + DY[dir];
				int nx = now.second + DX[dir];
				
				if (isSafe(ny,nx) && board[ny][nx] == TRASH && !visited[ny][nx]) {
					visited[ny][nx] = true;
					q.add(new Pair(ny,nx));
					result += 1;
				}
			}
		}
		
		return result;
	}
			
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] info = br.readLine().split(" ");
		
		n = Integer.parseInt(info[0]);
		m = Integer.parseInt(info[1]);
		k = Integer.parseInt(info[2]);
		
		board = new int[n][m];
		visited = new boolean[n][m];
		
		for (int loop = 0; loop < k; ++loop) {
			String[] pos = br.readLine().split(" ");
			
			int y = Integer.parseInt(pos[0]);
			int x = Integer.parseInt(pos[1]);
			board[y-1][x-1] = TRASH;
		}
		
		int answer = -1;
		
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (!visited[i][j] && board[i][j] == TRASH) {
					visited[i][j] = true;
					int result = bfs(i,j);
					answer = Math.max(answer,result);
				}
			}
		}
		
		System.out.println(answer);
	}
}
