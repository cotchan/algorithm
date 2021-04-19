package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static class Pair {
		int first, second;
		Pair(int f, int s) {
			this.first = f;
			this.second = s;
		}
	}
	
	static final int POS_OFFSET = 3;
	static final int ASTERISK = 9;
	static final int SHARP = 11;
	
	
	Pair leftThumb, rightThumb;
	
	Pair num2Position(int number) {
		return new Pair(number / POS_OFFSET, number % POS_OFFSET);
	}
	
	int getDistance(Pair thumb, Pair target) {
		return Math.abs(thumb.first - target.first) + Math.abs(thumb.second - target.second);
	}
	
    public String solution(int[] numbers, String hand) {
    	
        leftThumb = num2Position(ASTERISK);
        rightThumb = num2Position(SHARP);
        
        StringBuilder sb = new StringBuilder();
        
        for (int number : numbers) 
        {
        	if (number == 0)
        		number = 11;
        		
        	if (number == 1 || number == 4 || number == 7)
        	{
        		leftThumb = num2Position(number - 1);
        		sb.append('L');
        	}
        	else if (number == 3 || number == 6 || number == 9)
        	{
        		rightThumb = num2Position(number - 1);
        		sb.append('R');
        	}
        	else 
        	{
        		Pair target = num2Position(number - 1);
        		int ltDistance = getDistance(leftThumb, target);
        		int rtDistance = getDistance(rightThumb, target);
        		
        		if (ltDistance < rtDistance) 
        		{
        			sb.append('L');
        			leftThumb = target;
        		} 
        		else if (rtDistance < ltDistance) 
        		{
        			sb.append('R');
        			rightThumb = target;
        		} 
        		//rtDistance == ltDistance
        		else 
        		{
        			if (hand.compareTo("left") == 0) 
        			{
            			sb.append('L');
            			leftThumb = target;
        			}
        			else 
        			{
            			sb.append('R');
            			rightThumb = target;
        			}
        		}
        	}
        }
        
        return sb.toString();
    }
}