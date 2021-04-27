package PROGRAMMERS;

import java.util.*;

class Solution {
    public int solution(String s) {
        final int sSize = s.length();
        String answer = s;
        
        for (int slice_size = 1; slice_size <= s.length() / 2; ++slice_size)
        {
        	Map<String, Integer> stateMap = new HashMap<>();
        	String before = "";
        	StringBuilder sb = new StringBuilder();
        	
        	for (int idx = 0; idx < sSize; idx += slice_size)
        	{
        		int restLength = Math.min(slice_size, sSize - idx);
        		String target = s.substring(idx, idx + restLength);
        		
        		if (stateMap.containsKey(target))
        		{
        			int nowValue = stateMap.get(target);
        			stateMap.put(target, nowValue + 1);
        		}
        		else 
        		{
        			//rest
        			if (idx != 0)
        			{
            		    int beforeCnt = stateMap.get(before);
            			
            	        if (beforeCnt != 1)
            		    {
            			    sb.append(beforeCnt);
            		    }
            			
            		    sb.append(before);
            		    stateMap.remove(before);	
        			}
        			
        			stateMap.put(target, 1);
        			before = target;
        		}       		
        	}
        	
    		for (Map.Entry<String,Integer> entry : stateMap.entrySet())
    		{
    			String candidate = entry.getKey();
    			int cnt = entry.getValue();
    			if (cnt != 1) 
    			{
    				sb.append(cnt);
    			}
    			sb.append(candidate);
    		}
    		
    		String candidate = sb.toString();
    		
    		if (candidate.length() < answer.length())
    		{
    			answer = candidate;
    		}
        }
        
        return answer.length();
    }
}
