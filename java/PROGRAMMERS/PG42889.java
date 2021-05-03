package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static int[] solved, unsolved;
	
	public static class Pair implements Comparable<Pair> { 
		
		//실패율
		Double first;
		
		//스테이지 번호
		int second;
		
		Pair(Double f, int s) {
			first = f;
			second = s;
		}
		
		@Override
		public int compareTo(Pair o) {
			// TODO Auto-generated method stub
			if (this.first.compareTo(o.first) == 0) {
				return this.second < o.second ? -1 : 1;
			}
			else {
				return this.first.compareTo(o.first) > 0 ? -1 : 1;
			}
		}
	}
	
    public int[] solution(int N, int[] stages) {
    	
    	solved = new int[N];
    	unsolved = new int[N];
    	
    	for (int idx = 0; idx < stages.length; ++idx) 
    	{
    		int nowStage = stages[idx];
    		
    		if (nowStage == N+1) 
    		{
    			for (int i = 0; i < N; ++i) 
    			{
    				solved[i]++;
    			}
    		}
    		else 
    		{
    			for (int i = 0; i < nowStage - 1; ++i) 
    			{
    				solved[i]++;
    			}
    			
    			unsolved[nowStage-1]++;
    		}
    	}
    	
    	List<Pair> result = new LinkedList<>();
    	
    	//실패율 구하기
    	for (int i = 0; i < N; ++i) 
    	{
    		int solvedMember = solved[i];
    		int unsolvedMember = unsolved[i];
    		
    		int total = solvedMember + unsolvedMember;
    		
    		double failRate;
    		
    		if (total == 0)
    		{
    			failRate = 0;
    		}
    		else 
    		{
    			failRate = (unsolvedMember / (double)total);
    		}

    		result.add(new Pair(failRate, i));
    	}
    	
    	Collections.sort(result);
    	
        int[] answer = new int[N];
        
        int idx = 0;
        
        for (Pair p : result) 
        {
        	answer[idx] = p.second + 1;
        	idx++;
        }
        
        return answer;
    }
}