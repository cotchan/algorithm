package BOJ;

import java.util.*;
import java.io.*;

public class BOJ14502 {

	static class Pair {
		int first, second;
		Pair(int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static final int[] dy = {-1,1,0,0};
	static final int[] dx = {0,0,-1,1};
	
	static final int EMPTY = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	
	public static int[][] board;
	public static int N, M, wallSize, answer = -1;
	
	public static boolean[] isSelected;
	public static int[] candidate;
	
	public static List<Pair> emptyList = new ArrayList<>();
	public static List<Pair> virusList = new LinkedList<>();
	
	static boolean isSafe(int y, int x) {
		return (0 <= y && y < N && 0 <= x && x < M);
	}
	
	static int bfs(int[][] tmpBoard, List<Pair> _virusList) {
		
		int spreadSize = _virusList.size();
		Queue<Pair> q = new LinkedList<>();
		boolean[][] isVisited = new boolean[N][M];
		
		for (Pair pos : _virusList)
		{
			q.add(pos);
			isVisited[pos.first][pos.second] = true; 
		}
		
		while (!q.isEmpty())
		{
			Pair now = q.poll();
			
			for (int dir = 0; dir < 4; ++dir)
			{
				int ny = now.first + dy[dir];
				int nx = now.second + dx[dir];
				
				if (isSafe(ny,nx) && !isVisited[ny][nx] && (tmpBoard[ny][nx] == EMPTY))
				{
					spreadSize += 1;
					isVisited[ny][nx] = true;
					q.add(new Pair(ny,nx));
				}
			}
		}
		
		return (N*M) - (wallSize + 3) - spreadSize;
	}
	
	static int getSafeArea(int[][] originBoard, int[] candidatePos) {
		
		int[][] tmpBoard = new int[N][M];
		
		for (int i = 0; i < N; ++i)
		{
			tmpBoard[i] = originBoard[i].clone();
		}
		
		for (int i = 0; i < candidatePos.length; ++i)
		{
			int idx = candidatePos[i];
			Pair target = emptyList.get(idx);
			tmpBoard[target.first][target.second] = WALL;
		}
		
		//bfs
		return bfs(tmpBoard, virusList);
	}
	
	static void combination(int idx, int pickCnt) {
		
		if (pickCnt == 3) {
			//isPossible(canddiate)
			answer = Math.max(answer, getSafeArea(board, candidate));
			return;
		}
		else if (idx == emptyList.size()) {
			return;
		}
		
		if (!isSelected[idx]) 
		{
			isSelected[idx] = true;
			candidate[pickCnt] = idx;
			combination(idx + 1, pickCnt + 1);
			isSelected[idx] = false;
		}
		
		combination(idx + 1, pickCnt);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		board = new int[N][M];
		candidate = new int[3];
		
		for (int i = 0; i < N; ++i)
		{
			String[] row = br.readLine().split(" ");
			
			for (int j = 0; j < M; ++j)
			{
				board[i][j] = Integer.parseInt(row[j]);
			}
		}
		
		for (int i = 0; i < N; ++i)
		{
			for (int j = 0; j < M; ++j)
			{
				if (board[i][j] == VIRUS) 
				{
					virusList.add(new Pair(i,j));
				}
				else if (board[i][j] == EMPTY)
				{
					emptyList.add(new Pair(i,j));
				}
				else 
				{
					wallSize += 1;
				}
			}
		}
		
		isSelected = new boolean[emptyList.size()];
		
		combination(0,0);
		System.out.println(answer);
	}
}
