package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static final int IMPOSSIBLE = 987654321;
	static final int BLOCK = 1;
	static final int EMPTY = 0;
	static int N, M;
	static int[][] board, dp;
	
	public static boolean isSafe(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	public static int func(int i, int j) {
		
		//기저 사례
		if (board[i][j] == EMPTY) {
			return dp[i][j] = 0;
		}
		
		if (dp[i][j] != IMPOSSIBLE) {
			return dp[i][j];
		}
		
		int size1 = 0;
		int size2 = 0;
		int size3 = 0;
		
		if (isSafe(i-1,j-1)) {
			size1 = func(i-1,j-1);
		}
		
		if (isSafe(i,j-1)) {
			size2 = func(i,j-1);
		}
		
		if (isSafe(i-1,j)) {
			size3 = func(i-1,j);
		}
		
		int minv = IMPOSSIBLE;
		minv = Math.min(minv, size1);
		minv = Math.min(minv, size2);
		minv = Math.min(minv, size3);
		
		return dp[i][j] = minv + 1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		board = new int[N][M];
		dp = new int[N][M];
		
		for (int i = 0; i < N; ++i) {
			Arrays.fill(dp[i], IMPOSSIBLE);
		}
		
		for (int i = 0; i < N; ++i) {
			String row = br.readLine();
			for (int j = 0; j < M; ++j) {
				board[i][j] = Integer.parseInt(String.valueOf(row.charAt(j)));
			}
		}
		
		int answer = 0;
		
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if (board[i][j] == BLOCK) {
					int result = func(i,j);
					answer = Math.max(answer, result);					
				}
			}
		}
		
		System.out.println(answer*answer);
	}
}
