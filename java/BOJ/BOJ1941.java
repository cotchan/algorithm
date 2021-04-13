package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1941 {
	
	static class Pair {
		int first, second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}

	static final int DASOM = 0;
	static final int DOYEON = 1;
	static final int GRAPH_SIZE = 5;
	static final int STUDENT_SIZE = 25;
	static final int PRINCESS_SIZE = 7;
	
	static final int dy[] = {-1,1,0,0};
	static final int dx[] = {0,0,-1,1};
	
	static int answer = 0;
	static List<String> board = new ArrayList<>();
	static int[][] princessMap = new int[GRAPH_SIZE][GRAPH_SIZE];
	
	static int[] origin = new int[STUDENT_SIZE];
	static int[] picks = new int[PRINCESS_SIZE];
	static boolean[] isUsed = new boolean[STUDENT_SIZE];
	
	static boolean isSafe(int y, int x) {
		return (0 <= y && y < GRAPH_SIZE && 0 <= x && x < GRAPH_SIZE);
	}
	
	static int solve(int[] positions) {
		
		int dasomCnt = 0;
		int doyeonCnt = 0;
		
		int[][] tmpMap = new int[GRAPH_SIZE][GRAPH_SIZE];
		boolean[][] isVisited = new boolean[GRAPH_SIZE][GRAPH_SIZE];
		Queue<Pair> q = new LinkedList<>();
		
		//좌표 변환
		for (int i = 0; i < positions.length; ++i)
		{
			int y = positions[i] / GRAPH_SIZE;
			int x = positions[i] % GRAPH_SIZE;
			
			//첫 번째 좌표만 큐에 넣기 위함
			if (i == 0)
			{
				q.add(new Pair(y,x));
				isVisited[y][x] = true;
			}
			
			if (princessMap[y][x] == DASOM)
				dasomCnt += 1;
			else
				doyeonCnt += 1;
			
			tmpMap[y][x] = 1;
		}
		
		//다솜파 갯수 세기
		if (doyeonCnt >= dasomCnt)
			return 0;
		
		//연결되어 있는지 chk (bfs)
		int searchSize = 1;
		
		while (!q.isEmpty())
		{
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir)
			{
				int ny = now.first + dy[dir];
				int nx = now.second + dx[dir];
				
				if (isSafe(ny,nx) && !isVisited[ny][nx] && tmpMap[ny][nx] == 1)
				{
					isVisited[ny][nx] = true;
					q.add(new Pair(ny,nx));
					searchSize += 1;
				}
			}
		}
		
		if (searchSize == PRINCESS_SIZE)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	static void combination(int idx, int pickCnt) {
		
		if (pickCnt == PRINCESS_SIZE) {
			answer += solve(picks);
			return;
		}
		else if (idx == STUDENT_SIZE) {
			return;
		}
		
		if (!isUsed[idx]) {
			isUsed[idx] = true;
			picks[pickCnt] = origin[idx];
			combination(idx + 1, pickCnt + 1);
			isUsed[idx] = false;
		}
		
		combination(idx + 1, pickCnt);
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i = 0; i < GRAPH_SIZE; ++i)
		{
			String row = br.readLine();
			board.add(row);
		}
		
		for (int i = 0; i < GRAPH_SIZE; ++i)
		{
			for (int j = 0; j < GRAPH_SIZE; ++j)
			{
				if (board.get(i).charAt(j) == 'S')
					princessMap[i][j] = DASOM;
				else 
					princessMap[i][j] = DOYEON;
			}
		}
		
		for (int i = 0; i < STUDENT_SIZE; ++i)
			origin[i] = i;
		
		combination(0,0);
		
		System.out.println(answer);
	}

}
