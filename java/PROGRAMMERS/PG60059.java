package PROGRAMMERS;

import java.util.*;


class Solution {
	
	public static final int HOM = 0;
	public static final int DOLGI = 1;
	
	public static int keySize, lockSize;
	public List<int[][]> keys = new LinkedList<>();
	
	public int[][] rotate(int[][] origin, int _size) {
		
		int[][] result = new int[_size][_size];
		
		for (int i = 0; i < _size; ++i)
		{
			for (int j = 0; j < _size; ++j)
			{
				result[j][(_size - 1) - i] = origin[i][j];
			}
		}
		
		return result;
	}
	
	public boolean isLockBoundary(int y, int x) {
		return (keySize <= y && (y < keySize + lockSize) && keySize <= x && (x < keySize + lockSize));
	}
	
	public boolean doOpen(int[][] board, int boardSize, int[][] key, int st_y, int st_x) {
		
		//자물쇠의 홈 갯수 세기
		int homCnt = 0;

		for (int i = 0; i < lockSize; ++i)
		{
			for (int j = 0; j < lockSize; ++j)
			{
				if (board[i + keySize][j + keySize] == HOM)
				{
					homCnt += 1;
				}
			}
		}
		
		//돌기와 돌기 만나는 지 확인
		for (int i = 0; i < keySize; ++i)
		{
			for (int j = 0; j < keySize; ++j)
			{
				if ((key[i][j] == DOLGI) && (board[i + st_y][j + st_x] == DOLGI))
					return false;
			}
		}
		
		int coverCnt = 0;
		
		for (int i = 0; i < keySize; ++i)
		{
			for (int j = 0; j < keySize; ++j)
			{
				if ((key[i][j] == DOLGI) && (board[i + st_y][j + st_x] == HOM) 
						&& isLockBoundary(st_y + i, st_x + j))
				{
					coverCnt += 1;
					
				}
			}
		}
		
		//열쇠가 연 홈 갯수가 자물쇠의 홈 갯수와 일치하는지 확인
		return homCnt == coverCnt;
	}
	
    public boolean solution(int[][] key, int[][] lock) {
    	
        boolean answer = false;
        keySize = key.length;
        lockSize = lock.length;
        
        for (int i = 0; i < 4; ++i)
        {
        	int[][] rotateKey = rotate(key, keySize);
        	keys.add(rotateKey);
        	key = rotateKey;
        }
        
        // solve
        final int boardSize = (2 * keySize) + lockSize;
        int[][] board = new int[boardSize][boardSize];

        for (int i = 0; i < lockSize; ++i)
        {
        	for (int j = 0; j < lockSize; ++j)
        	{
        		board[i + keySize][j + keySize] = lock[i][j];
        	}
        }
        
        for (int[][] nowKey : keys)
        {        	
            for (int i = 0; i < keySize + lockSize; ++i)
            {
            	for (int j = 0; j < keySize + lockSize; ++j)
            	{
            		if (doOpen(board, boardSize, nowKey, i, j))
            		{
            			answer = true;
            		}
            	}
            }
        }
        
        return answer;
    }
}