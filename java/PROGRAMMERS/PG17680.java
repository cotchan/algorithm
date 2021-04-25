package PROGRAMMERS;

import java.util.*;

class Solution {

	Map<String, Integer> cache = new HashMap<>();
	
	public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if (cacheSize == 0)
        {
        	return cities.length * 5;
        }
        
        int time = 1;
        for (String city : cities)
        {
        	city = city.toLowerCase();
        	
        	if (cache.containsKey(city))
        	{
        		cache.put(city, time);
        		answer++;
        	}
        	else {
        		
        		if (cache.size() == cacheSize) {
        			
        			String minvCity = "";
        			int minv = Integer.MAX_VALUE;
        			
        			for (Map.Entry<String, Integer> entry : cache.entrySet()) {
        				String targetCity = entry.getKey();
        				int targetTime = entry.getValue();
        				
        				if (targetTime < minv)
        				{
        					minv = targetTime;
        					minvCity = targetCity;
        				}
        			}
        			
        			cache.remove(minvCity);
        			cache.put(city, time);
        		}
        		else {
        			cache.put(city, time);
        		}
        		
        		answer += 5;
        	}
        	
        	time++;
        }
        
        return answer;
    }
}