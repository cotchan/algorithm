package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final int HOUR_OFFSET = 60;
	
	public int time2Number(String time) {
		
		String[] timeInfo = time.split(":");
		int hour = Integer.parseInt(timeInfo[0]);
		int minute = Integer.parseInt(timeInfo[1]);
		return hour * HOUR_OFFSET + minute;
	}
	
	public String Number2Time(int number) {
		
		int hour = number / HOUR_OFFSET;
		int minute = number % HOUR_OFFSET;
		
		StringBuilder sb = new StringBuilder();
		if (0 <= hour && hour <= 9) {
			sb.append("0");
			sb.append(hour);
		} else {
			sb.append(hour);
		}
		
		sb.append(":");
		
		if (0 <= minute && minute <= 9) {
			sb.append("0");
			sb.append(minute);
		} else {
			sb.append(minute);
		}
		
		return sb.toString();
	}
	
    public String solution(int n, int t, int m, String[] timetable) {
        
    	int busTime = time2Number("09:00");
    	int answerTimeNumber = 0;
    	
    	//ÀüÃ³¸®
    	
    	int[] numberTimeTable = new int[timetable.length];
    	
    	for (int i = 0; i < timetable.length; ++i) {
    		int timeNumber = time2Number(timetable[i]);
    		numberTimeTable[i] = timeNumber;
    	}
    	
    	Arrays.sort(numberTimeTable);
    	
    	//timetable index
    	int tIdx = 0;
    	
    	for (int loop = 1; loop <= n; ++loop) {
    		
    		if (tIdx == numberTimeTable.length) 
    			break;
    		
    		int clientCnt = 0;
    		
    		while ((clientCnt < m) && (tIdx < numberTimeTable.length)) {
    		
    			int clientTimeNum = numberTimeTable[tIdx];

    			if (clientTimeNum <= busTime) {
    				
    				clientCnt++;
    				tIdx++;
        		} else {
        			break;
        		}
    		}
    		
    		if (loop == n) {
    			if (clientCnt == m) {
    				int clientTimeNum = numberTimeTable[tIdx-1];
    				answerTimeNumber = clientTimeNum-1;
    			} else {
    				answerTimeNumber = busTime;
    			}
    		}
    		   		
    		busTime += t; 
    		
    	}
    	
    	String answer = Number2Time(answerTimeNumber);
        
        return answer;
    }
}
