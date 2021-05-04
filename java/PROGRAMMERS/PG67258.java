package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static Map<String, Integer> myGems = new HashMap<>();
	static Set<String> gemList = new HashSet<>();
	
    public int[] solution(String[] gems) {
    	
    	int left, right, minv, minv_l, minv_r;
    	left =  right = minv_l = minv_r = 0;
    	minv = Integer.MAX_VALUE;
    	int MAX_LENGTH = gems.length;
    	
    	//getFullList
    	for (String gem : gems)
    	{
    		gemList.add(gem);
    	}
    	
    	while ((right < MAX_LENGTH) && left <= right)
    	{
    		String nowGem = gems[right];
    		
    		//right 전진
    		if (!myGems.containsKey(nowGem)) 
    		{
    			myGems.put(nowGem, 1);
    		}
    		else
    		{
    			int before = myGems.get(nowGem);
    			myGems.put(nowGem, before+1);
    		}
    		
    		//끝내기 가능 ?
    		    		
    		//left 전진
    		while (left < right)
    		{
    			String nxtGem = gems[left];
    			int nxtGemSize = myGems.get(nxtGem);
    			
    			if (nxtGemSize >= 2)
    			{
    				myGems.put(nxtGem, nxtGemSize-1);
    				left++;
    			}
    			else
    			{
    				break;
    			}
    		}
    		
    		if (gemList.size() == myGems.keySet().size())
    		{
    			int nowLength = right - left;
    			
    			if (nowLength < minv)
    			{
    				minv = nowLength;
    				minv_l = left;
    				minv_r = right;
    			}
    		}
    		
    		right += 1;
    	}
    	
        int[] answer = {minv_l+1, minv_r+1};
        return answer;
    }
}