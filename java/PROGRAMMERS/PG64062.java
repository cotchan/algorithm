package PROGRAMMERS;

import java.util.*;

class Solution {
	
	
	boolean isPossible(int[] stones, int k, int height) {
		
		for (int idx = 0; idx < stones.length; ++idx) {
			
			if (stones[idx] < height)
			{
				int nxt = idx;
				
				while (nxt < stones.length && stones[nxt] < height)
				{
					nxt++;
				}
				
				int offset = (nxt - idx);
				if (offset >= k)
				{
					return false;
				}
				
				idx = nxt - 1;
			}
		}
		
		return true;
	}
	
    public int solution(int[] stones, int k) {
    	
    	//get low, high
    	int low, high, answer;
    	low = Integer.MAX_VALUE;
    	high = Integer.MIN_VALUE;
    	answer = Integer.MIN_VALUE;
    	
    	for (int stone : stones) {
    		low = Math.min(low, stone);
    		high = Math.max(high, stone);
    	}
    	
    	high += 1;
    	
    	while (low <= high) {
    		
    		int mid = (low + high ) / 2;
    		
    		if (isPossible(stones, k, mid)) 
    		{
    			//건널 수 있음
    			//높이 더 올려봄
    			low = mid + 1;
    			answer = Math.max(answer, mid);
    		}
    		else 
    		{
    			high = mid - 1;
    		}
    		
    	}
    	
        return answer;
    }
}
