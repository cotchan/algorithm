package PROGRAMMERS;

import java.util.*;

public class PG64061 {

	Stack<Integer>[] stkList;
	Stack<Integer> bucket = new Stack<>();
	int boardSize;
	
	public boolean isEqualToTop(int dollNumber) {
		
		if (bucket.isEmpty())
			return false;
	
		int topDollNumber = bucket.peek();
		
		if (dollNumber == topDollNumber)
			return true;
		else 
			return false;
	}
	
    public int solution(int[][] board, int[] moves) {
        
        boardSize = board.length;
        stkList = new Stack[boardSize];
        
        for (int i = 0; i < boardSize; ++i)
        {
        	stkList[i] = new Stack<>();
        }
        
        for (int i = boardSize-1; i >= 0; --i)
        {
        	for (int j = 0; j < boardSize; ++j)
        	{
        		if (board[i][j] != 0)
        		{
        			stkList[j].add(board[i][j]);
        		}
        	}
        }
        
        int answer = 0;

        for (int pos : moves)
        {
        	int idx = pos - 1;
        	
        	if (stkList[idx].isEmpty())
        		continue;
        	
        	int doll = stkList[idx].pop();
        	
        	//check
        	if (isEqualToTop(doll))
        	{
        		answer += 2;
        		bucket.pop();
        	}
        	else 
        	{
        		bucket.add(doll);
        	}
        }
        
        return answer;
    }
}
