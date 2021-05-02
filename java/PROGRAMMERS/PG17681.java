package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final char WALL = '#';
	static final char EMPTY = ' ';
	
	String decodeLine(int n, int number) {
		
		StringBuilder sb = new StringBuilder();
		
		int target = number;
		
		while (target > 0) {
			int result = target % 2;
			
			char token;
			
			if (result == 1) 
			{
				token = WALL;
			} 
			else 
			{
				token = EMPTY;
			}
			
			sb.append(token);
			target /= 2;
		}
		
		int zeroLoopSize = n - sb.length();
		
		for (int i = 0; i < zeroLoopSize; ++i) 
		{
			sb.append(String.valueOf(EMPTY));
		}
		
		return sb.reverse().toString();
	}
	
	String[] doDecode(int[] numbers) {
		
		String[] result = new String[numbers.length];
		
		for (int i = 0; i < numbers.length; ++i) 
		{
			String row = decodeLine(numbers.length, numbers[i]);
			result[i] = row;
		}
		
		return result;
	}
	
    public String[] solution(int n, int[] arr1, int[] arr2) {
    	
    	String[] answer = new String[n];
    	String[] graph1 = doDecode(arr1);
    	String[] graph2 = doDecode(arr2);
    	
    	//merge
    	for (int i = 0; i < n; ++i) 
    	{
    		StringBuilder sb = new StringBuilder();
    		
    		for (int j = 0; j < n; ++j) 
    		{
    			if (graph1[i].charAt(j) == EMPTY && graph2[i].charAt(j) == EMPTY) 
    			{
    				sb.append(EMPTY);
    			} else 
    			{
    				sb.append(WALL);
    			}
    		}
    		
    		answer[i] = sb.toString();
    	}
    	
        return answer;
    }
}