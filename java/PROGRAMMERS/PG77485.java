package PROGRAMMERS;

import java.util.*;

class Solution {
	
	//https://coding-factory.tistory.com/603
	PriorityQueue<Integer> pq = new PriorityQueue<>();
	int row_size, col_size;
	
	void printBoard(int[][] board) {
		
		int rowSize = board.length;
		int colSize = board[0].length;
		
		for (int i = 0; i < rowSize; ++i)
		{
			for (int j = 0; j < colSize; ++j)
			{
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	int rotate(int[][] origin, int y1, int x1, int y2, int x2) {
		
		pq.clear();
	
		int val1 = origin[y1][x2];
		
		pq.add(val1);
		
		for (int i = x2; i > x1; --i) {
			pq.add(origin[y1][i-1]);
			origin[y1][i] = origin[y1][i-1];
		}
		
		
		
		int val2 = origin[y2][x2];
		pq.add(val2);
		
		for (int i = y2; i > y1; --i) {
			pq.add(origin[i-1][x2]);
			origin[i][x2] = origin[i-1][x2];
		}
		
		origin[y1+1][x2] = val1;
		
		
		
		int val3 = origin[y2][x1];
		pq.add(val3);
		
		for (int i = x1+1; i < x2; ++i) {
			pq.add(origin[y2][i]);
			origin[y2][i-1] = origin[y2][i];
		}
		
		origin[y2][x2-1] = val2;
		
		
		
		for (int i = y1 + 1; i < y2; ++i) {
			pq.add(origin[i][x1]);
			origin[i-1][x1] = origin[i][x1];
		}
		
		origin[y2-1][x1] = val3;
		
		return pq.peek();
	}
	
    public int[] solution(int rows, int columns, int[][] queries) {

    	
    	int[][] board = new int[rows][columns];
    	
    	for (int loop = 0; loop < rows * columns; ++loop) {
    		int i = loop / columns;
    		int j = loop % columns;
    		board[i][j] = loop + 1;
    	}

    	int loopSize = queries.length;
    	int[] answer = new int[loopSize];
    	
    	for (int i = 0; i < loopSize; ++i) {
    		
    		int y1 = queries[i][0];
    		int x1 = queries[i][1];
    		int y2 = queries[i][2];
    		int x2 = queries[i][3];
    		
    		y1--;
    		x1--;
    		y2--;
    		x2--;
    		
    		int minv = rotate(board,y1,x1,y2,x2);
    		answer[i] = minv;
    	}
        return answer;
    }
}