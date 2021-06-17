package boj;

import java.io.*;
import java.util.*;

class Solution {
	
	static class Pair {
		int first, second;
		
		Pair (int f, int s) {
			first = f;
			second = s;
		}
	}
	
	static class Tuple {
		int first, second, third;
		
		Tuple (int f, int s, int t) {
			first = f;
			second = s;
			third = t;
		}
	}
	
	static final int UP = 0;
	static final int LEFT = 1;
	static final int DOWN = 2;
	static final int RIGHT = 3;
	static final int DIR_SIZE = 4;
	
	static final int EMPTY = 0;
	static final int BLOCK = 1;
	
	static final int DY[] = {-1,0,1,0};
	static final int DX[] = {0,-1,0,1};
	
	static int N;
	static int[][][] stateBoard;
	
	public static Pair getTailPosition(int y, int x, int dir) {
		int ny = y + DY[dir];
		int nx = x + DX[dir];
		
		return new Pair(ny,nx);
	}
	
	public static boolean isSafe(int y, int x) {
		return (0 <= y && y < N && 0 <= x && x < N);
	}
	
	public static int bfs(int[][] board) {
	
		Queue<Tuple> q = new LinkedList<>();
		q.add(new Tuple(0,0,RIGHT));
		stateBoard[RIGHT][0][0] = 0;
		
		int time = 0;
		boolean isFind = false;
		
		while (!q.isEmpty()) {
			
			if (isFind) {
				break;
			}
					
			int loop = q.size();
			
			for (int l = 0; l < loop; ++l) {
				//종료 조건
				Tuple head = q.poll();
				Pair tail = getTailPosition(head.first, head.second, head.third);

				//종료 조건1
				if (head.first == N-1 && head.second == N-1) {
					isFind = true;
					break;
				}
				//종료 조건2
				else if (tail.first == N-1 && tail.second == N-1) {
					isFind = true;
					break;
				} 
				else {
					
					int headDir = head.third;
					//head를 축으로 tail이 회전하는 경우
					//case1.
					int nxtDir = (headDir + 1 + DIR_SIZE) % DIR_SIZE;
					Pair nxtTail = getTailPosition(head.first, head.second, nxtDir);
					int ny = head.first + DY[headDir] + DY[nxtDir];
					int nx = head.second + DX[headDir] + DX[nxtDir];
					
					//회전할 수 있으면
					if (isSafe(ny,nx) && board[ny][nx] == EMPTY 
						&& isSafe(nxtTail.first, nxtTail.second)
						&& board[nxtTail.first][nxtTail.second] == EMPTY) {
						if (time < stateBoard[nxtDir][head.first][head.second]) {
							stateBoard[nxtDir][head.first][head.second] = time;
							q.add(new Tuple(head.first, head.second, nxtDir));
						}
					}
					
					//case2.
					nxtDir = (headDir -1 + DIR_SIZE) % DIR_SIZE;
					nxtTail = getTailPosition(head.first, head.second, nxtDir);
					ny = head.first + DY[headDir] + DY[nxtDir];
					nx = head.second + DX[headDir] + DX[nxtDir];
					
					//회전할 수 있으면
					if (isSafe(ny,nx) && board[ny][nx] == EMPTY 
						&& isSafe(nxtTail.first, nxtTail.second)
						&& board[nxtTail.first][nxtTail.second] == EMPTY)  {
						if (time < stateBoard[nxtDir][head.first][head.second]) {
							stateBoard[nxtDir][head.first][head.second] = time;
							q.add(new Tuple(head.first, head.second, nxtDir));
						}
					}
					
					
					//tail을 축으로 head가 회전하는 경우
					int tailDir = (headDir + 2) % DIR_SIZE;
					
					//case1.
					nxtDir = (tailDir + 1 + DIR_SIZE) % DIR_SIZE;
					nxtTail = getTailPosition(tail.first, tail.second, nxtDir);
					ny = tail.first + DY[tailDir] + DY[nxtDir];
					nx = tail.second + DX[tailDir] + DX[nxtDir];
					
					//회전할 수 있으면
					if (isSafe(ny,nx) && board[ny][nx] == EMPTY 
						&& isSafe(nxtTail.first, nxtTail.second)
						&& board[nxtTail.first][nxtTail.second] == EMPTY) {
						if (time < stateBoard[nxtDir][tail.first][tail.second]) {
							stateBoard[nxtDir][tail.first][tail.second] = time;
							q.add(new Tuple(tail.first, tail.second, nxtDir));
						}
					}
					
					//case2.
					nxtDir = (tailDir - 1 + DIR_SIZE) % DIR_SIZE;
					nxtTail = getTailPosition(tail.first, tail.second, nxtDir);
					ny = tail.first + DY[tailDir] + DY[nxtDir];
					nx = tail.second + DX[tailDir] + DX[nxtDir];
					
					//회전할 수 있으면
					if (isSafe(ny,nx) && board[ny][nx] == EMPTY 
						&& isSafe(nxtTail.first, nxtTail.second)
						&& board[nxtTail.first][nxtTail.second] == EMPTY) {
						if (time < stateBoard[nxtDir][tail.first][tail.second]) {
							stateBoard[nxtDir][tail.first][tail.second] = time;
							q.add(new Tuple(tail.first, tail.second, nxtDir));
						}
					}
					
					for (int dir = 0; dir < DIR_SIZE; ++dir) {
						int ny2 = head.first + DY[dir];
						int nx2 = head.second + DX[dir];
						Pair nxt2Tail = getTailPosition(ny2, nx2, head.third);
						
						if (isSafe(ny2,nx2) && board[ny2][nx2] == EMPTY 
								&& isSafe(nxt2Tail.first, nxt2Tail.second)
								&& board[nxt2Tail.first][nxt2Tail.second] == EMPTY) {
							if (time < stateBoard[head.third][ny2][nx2]) {
								stateBoard[head.third][ny2][nx2] = time;
								q.add(new Tuple(ny2, nx2, head.third));
							}
						}
					}
				}
			}
						
			time++;
		}
		
		return time;
	}
	
    public int solution(int[][] board) {
    	
    	N = board.length;
    	stateBoard = new int[DIR_SIZE][N][N];
    	
    	for (int i = 0; i < DIR_SIZE; ++i) {
    		for (int j = 0; j < N; ++j) {
    			Arrays.fill(stateBoard[i][j], Integer.MAX_VALUE);
    		}
    	}
    	
        int answer = bfs(board);
        return answer - 1;
    }
}
