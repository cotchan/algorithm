package PROGRAMMERS;

import java.util.*;

class Solution {

	static final int EMPTY = 0;
	static final int DIR_SIZE = 4;
	
	static int[][] friendBoard;
	static int rowSize, colSize;
	
	int dy[] = {0,1,1,0};
	int dx[] = {0,1,0,1};
	
	public boolean isSafe(int y, int x) {
		return (0 <= y && y < rowSize && 0 <= x && x < colSize);
	}
	
	public void changedState(boolean[][] arr, int y, int x, boolean state) {
		
		for (int dir = 0; dir < DIR_SIZE; ++dir) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (isSafe(ny,nx)) {
				arr[ny][nx] = state;
			}
		}
	}
	
	public boolean willBeRemoved(int y, int x) {
		int cnt = 0;
		
		for (int dir = 0; dir < DIR_SIZE; ++dir) {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			
			if (isSafe(ny,nx) && (friendBoard[ny][nx] == friendBoard[y][x])) {
				cnt += 1;
			}
		}
		
		return cnt == 4 ? true : false;
	}
	
    public int solution(int m, int n, String[] board) {
    	
    	friendBoard = new int[m][n];
    	rowSize = m;
    	colSize = n;
    	
    	for (int i = 0; i < m; ++i) {
    		for (int j = 0; j < n; ++j) {
    			int ascii = board[i].charAt(j);
    			friendBoard[i][j] = ascii;
    		}
    	}
    	
    	
    	while (true) {
    		
    		boolean isFind = false;
    		boolean[][] isRemoved = new boolean[m][n];
    		
    		for (int i = 0; i < m; ++i) {
    			for (int j = 0; j < n; ++j) {
    				
    				if (friendBoard[i][j] != EMPTY) {
        				boolean result = willBeRemoved(i, j);
        				
        				if (result) {
        					isFind = true;
        					changedState(isRemoved, i, j, true);
        				}
    				}
    				
    			}
    		}
    		
    		//옮기기
    		for (int j = 0; j < n; ++j) {
    			Stack<Integer> stk = new Stack<>();
    			
    			//지울 애 찾기
    			for (int i = 0; i < m; ++i) {
    				if (!isRemoved[i][j]) {
    					stk.add(friendBoard[i][j]);
    				}
    				
    				friendBoard[i][j] = EMPTY;
    			}
    			
    			//옮기기
    			int idx = m-1;
    			while (!stk.isEmpty()) {
    				int val = stk.pop();
    				friendBoard[idx][j] = val;
    				idx--;
    			}
    		}
    		
    		if (!isFind)
    			break;
    	}
    	
    	
    	int answer = 0;
    	
    	for (int i = 0; i < m; ++i) {
    		for (int j = 0; j < n; ++j) {
    			if (friendBoard[i][j] == EMPTY)
    				answer += 1;
    		}
    	}
        
        return answer;
    }
}