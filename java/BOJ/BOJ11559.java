package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11559 {

	static class Pair {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static final int BOARD_ROW = 6;
	static final int BOARD_COL = 12;
	static final int DY[] = {-1,1,0,0};
	static final int DX[] = {0,0,-1,1};
	
	static List<String> board = new ArrayList<>();
	static int[][] numberBoard = new int[BOARD_COL][BOARD_ROW];
	static boolean[][] isVisited = new boolean[BOARD_COL][BOARD_ROW];

	public static boolean isSafe(int y, int x) {
		return (0 <= y && y < BOARD_COL && 0 <= x && x < BOARD_ROW);
	}
	
	public static boolean bfs(int y, int x) {
		
		int target = numberBoard[y][x];
		List<Pair> results = new LinkedList<>();
		
		results.add(new Pair(y,x));
		Queue<Pair> q = new LinkedList<>();

		q.add(new Pair(y,x));
		
		while (!q.isEmpty())
		{
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir)
			{
				int ny = now.first + DY[dir];
				int nx = now.second + DX[dir];
				
				if (isSafe(ny,nx) 
						&& (numberBoard[ny][nx] == target) 
						&& !isVisited[ny][nx])
				{
					isVisited[ny][nx] = true;
					q.add(new Pair(ny,nx));
					results.add(new Pair(ny,nx));
				}
			}
		}
		
		if (results.size() >= 4) {
			
			for (Pair result_pos : results) 
			{
				int ry = result_pos.first;
				int rx = result_pos.second;
				numberBoard[ry][rx] = 0;
			}
			
			return true;
		} 
		else 
		{
			return false;
		}
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < 12; ++i)
		{
			String row = br.readLine();
			board.add(row);
		}
		
		for (int i = 0; i < 12; ++i)
		{
			for (int j = 0; j < 6; ++j)
			{
				if (board.get(i).charAt(j) != '.')
				{
					numberBoard[i][j] = board.get(i).charAt(j);
				}
			}
		}
		
		int answer = 0;
		boolean isBomed = true;
		
		while (isBomed)
		{
			isBomed = false;

			for (int i = 0; i < BOARD_COL; ++i)
			{
				Arrays.fill(isVisited[i], false);
			}
			
			for (int i = 0; i < BOARD_COL; ++i)
			{
				for (int j = 0; j < BOARD_ROW; ++j)
				{
					if (numberBoard[i][j] != 0 && !isVisited[i][j])
					{
						isVisited[i][j] = true;
						boolean result = bfs(i,j);
						if (result) {
							isBomed = true;
						}
					}
				}
			}
			
			//down
			for (int i = 0; i < BOARD_ROW; ++i)
			{
				Stack<Integer> stk = new Stack<>();
				
				for (int j = 0; j < BOARD_COL; ++j)
				{
					if (numberBoard[j][i] != 0)
					{
						stk.add(numberBoard[j][i]);
					}
				}
				
				for (int j = 0; j < BOARD_COL; ++j)
				{
					numberBoard[j][i] = 0;
				}
				
				for (int j = BOARD_COL - 1; j >= 0; --j)
				{
					if (!stk.isEmpty())
					{
						int val = stk.pop();
						numberBoard[j][i] = val;
					}
					else
					{
						break;
					}
				}
			}
			
			answer += 1;
		}
		
		System.out.println(answer-1);
	}
}
