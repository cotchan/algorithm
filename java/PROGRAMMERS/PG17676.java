package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static class Pair {
		double first, second;
		
		Pair(double f, double s) {
			first = f;
			second = s;
		}
	}
	
	//milli second 계산을 단순화를 위해 1초를 1000 milli second 올리기 위해 사용
	static final double MILLI_TIME_OFFSET = 1000.0;
	
	//임의 시간부터 1초를 구하는 연산에 사용. 
	//1초 계산 시 시작점을 포함하므로 + 999를 해줘야 총 1000 millisecond(1초) 범위가 됨
	static final double TIME_OFFSET = 999.0;
	
	static Map<Integer, Pair> hashMap = new HashMap<>();
	
	public double time2Double(String hh, String mm, String se) {
		double hour = Double.parseDouble(hh);
		double minute = Double.parseDouble(mm);
		double second = Double.parseDouble(se);
		
		return MILLI_TIME_OFFSET * ((hour * 3600) + (minute * 60) + second);
	}
	
	public boolean isContained(Double ref, Pair target) {
		
		if (ref + TIME_OFFSET < target.first)
			return false;

		if (target.second < ref) 
			return false;
		
		return true;
	}
	
    public int solution(String[] lines) {
    	
    	for (int idx = 0; idx < lines.length; ++idx) {
    		String[] dateInfo = lines[idx].split(" ");
    		
    		//2016-09-15
    		//hh:mm:ss.sss
    		//2.0s
    		String[] timeInfo = dateInfo[1].split(":");
    		
    		//hh
    		//mm
    		//ss.sss
    		
    		double endTime = time2Double(timeInfo[0], timeInfo[1], timeInfo[2]);
    		
    		String range = dateInfo[2].substring(0, dateInfo[2].length() - 1);
    		double rangeValue = Double.parseDouble(range) * MILLI_TIME_OFFSET;
    		
    		double startTime = endTime - rangeValue + 1;
    		
    		//start, end
    		Pair info = new Pair(startTime, endTime);
    		
    		hashMap.put(idx, info);
    	}
    	
    	int answer = 0;
    	
    	for (Map.Entry<Integer, Pair> now : hashMap.entrySet()) {
    		
    		int nowKey = now.getKey();
    		Pair nowTimeInfo = now.getValue();
    		
    		int cntForHead = 1;
    		int cntForTail = 1;
    		
        	for (Map.Entry<Integer, Pair> target : hashMap.entrySet()) {
        		
        		int targetKey = target.getKey();
        		Pair targetInfo = target.getValue();
        		
        		if (nowKey == targetKey) 
        			continue;
        			
        		if (isContained(nowTimeInfo.first, targetInfo)) {
        			cntForHead++;
        		}
        		if (isContained(nowTimeInfo.second, targetInfo)) {
        			cntForTail++;
        		}
        	}
        	
        	answer = Math.max(answer, cntForHead);
        	answer = Math.max(answer, cntForTail);
    	}
        
        return answer;
    }
}