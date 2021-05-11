package PROGRAMMERS;

class Solution {
	
	static int[][][] roadState;

	static final int DIR_SIZE = 4;
	
	static final int EMPTY = 0;
	static final int WALL = 1;

	static final int UP = 0;
	static final int LEFT = 1;
	static final int DOWN = 2;
	static final int RIGHT = 3;
	
	static final int STRAIGHT_ROAD_PRICE = 100;
	static final int CORNER_ROAD_PRICE = 600;
	
	static int rowSize, colSize;
	
	int dy[] = {-1,0,1,0};
	int dx[] = {0,-1,0,1};
	
	public boolean isSafe(int y, int x) {
		return (0 <= y && y < rowSize && 0 <= x && x < colSize);
	}
	
	public void func(int[][] board, int dir, int y, int x) {
		
		for (int nxtDir = 0; nxtDir < DIR_SIZE; ++nxtDir) {
			
			if ((dir + 2) % DIR_SIZE == nxtDir) {
				continue;
			}
			else if (dir == nxtDir) {
				int ny = y + dy[nxtDir];
				int nx = x + dx[nxtDir];
				
				if (isSafe(ny,nx) && board[ny][nx] == EMPTY) {
					int myValue = roadState[dir][y][x] + STRAIGHT_ROAD_PRICE;
					
					if (roadState[nxtDir][ny][nx] > myValue) {
						roadState[nxtDir][ny][nx] = myValue;
						func(board, nxtDir, ny, nx);
					}
				}
			}
			else {
				int ny = y + dy[nxtDir];
				int nx = x + dx[nxtDir];
				if (isSafe(ny,nx) && board[ny][nx] == EMPTY) {
					int myValue = roadState[dir][y][x] + CORNER_ROAD_PRICE;
					
					if (roadState[nxtDir][ny][nx] > myValue) {
						roadState[nxtDir][ny][nx] = myValue;
						func(board, nxtDir, ny, nx);
					}
				}
			}
		}
	}
	
    public int solution(int[][] board) {
    	
    	rowSize = board.length;
    	colSize = board[0].length;
    	roadState = new int[DIR_SIZE][rowSize][colSize];
    	
    	for (int i = 0; i < DIR_SIZE; ++i)
    		for (int j = 0; j < rowSize; ++j)
    			for (int k = 0; k < colSize; ++k)
    				roadState[i][j][k] = Integer.MAX_VALUE;
    	
    	for (int dir = 0; dir < DIR_SIZE; ++dir) {
    		roadState[dir][0][0] = 0;
    	}
    	
    	func(board, RIGHT, 0, 0);
    	func(board, DOWN, 0, 0);
    	
        int answer = Integer.MAX_VALUE;
        
    	for (int dir = 0; dir < DIR_SIZE; ++dir) {
    		answer = Math.min(answer,roadState[dir][rowSize-1][colSize-1]);
    	}
        
        return answer;
    }
}