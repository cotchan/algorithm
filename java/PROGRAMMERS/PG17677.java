package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final int OFFSET = 65536;
	Map<String, Integer> str1Info = new HashMap<>();
	Map<String, Integer> str2Info = new HashMap<>();
	
	public boolean isAlphabet(String token) {
		
    	int asciiA = 'a';
    	int asciiZ = 'z';
		boolean result = true;
		
		for (int j = 0; j < token.length(); ++j) {
			char c = token.charAt(j);
			int asciiC = c;
			
			if (asciiA <= asciiC && asciiC <= asciiZ) {
			}
			else {
				result = false;
			}
		}
		
		return result;
	}
	
    public int solution(String str1, String str2) {
        
    	str1 = str1.toLowerCase();
    	str2 = str2.toLowerCase();
    	
    	int asciiA = 'a';
    	int asciiZ = 'z';
    	
    	for (int i = 0; i < str1.length() - 1; ++i) {
    		String token = str1.substring(i, i+2);
    		
    		boolean isAlpha = isAlphabet(token);
    		    		
    		if (!isAlpha)
    			continue;
    		
    		if (str1Info.containsKey(token)) {
    			int before = str1Info.get(token);
    			str1Info.put(token, before+1);
    		} else {
    			str1Info.put(token, 1);
    		}
    	}
    	
    	for (int i = 0; i < str2.length() - 1; ++i) {
    		String token = str2.substring(i, i+2);
    		
    		boolean isAlpha = isAlphabet(token);
    		
    		if (!isAlpha)
    			continue;
    		
    		if (str2Info.containsKey(token)) {
    			int before = str2Info.get(token);
    			str2Info.put(token, before+1);
    		} else {
    			str2Info.put(token, 1);
    		}
    	}
    	
    	//교집합 구하기
    	int intersectSize = 0;
    	
    	for (Map.Entry<String,Integer> entry : str1Info.entrySet())
    	{
    		String key = entry.getKey();
    		int val1 = entry.getValue();
    		
    		if (str2Info.containsKey(key)) 
    		{
    			int val2 = str2Info.get(key);
    			intersectSize += Math.min(val1, val2);
    		}
    	}
    	
    	int str1Size = 0;
    	int str2Size = 0;
    	
    	for (int val : str1Info.values()) {
    		str1Size += val;
    	}
    	
    	for (int val : str2Info.values()) {
    		str2Size += val;
    	}
    	
    	int unionSize = str1Size + str2Size - intersectSize;
    	
    	int answer = 0;
    	
    	if (str1Size == 0 && str2Size == 0) {
    		answer = OFFSET;
    	}
    	else {
    		answer = (intersectSize * OFFSET) / unionSize;
    	}
        
        return answer;
    }
}