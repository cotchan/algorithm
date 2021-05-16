package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final char LEFT = '(';
	static final char RIGHT = ')';
	
	public boolean isRightGaro(String garo) {
		Stack<Character> stk = new Stack<>();
		
		for (int i = 0; i < garo.length(); ++i) {
			char target = garo.charAt(i);
			
			if (target == LEFT) {
				stk.push(LEFT);
			} else {
				if (stk.isEmpty()) {
					return false;
				} else {
					stk.pop();
				}
			}
		}
		
		return stk.isEmpty() ? true : false;
	}
	
	public boolean isBalancedGaro(String garo) {
		int leftCnt = 0;
		int rightCnt = 0;
		
		for (int i = 0; i < garo.length(); ++i) {
			if (garo.charAt(i) == LEFT) leftCnt++;
			else rightCnt++;
		}
		
		return leftCnt == rightCnt ? true: false;
	}
	
    public String solution(String p) {
    	
    	//1.
    	if (p.compareTo("") == 0)
    		return "";
    	
    	//2.
    	String u = "";
    	String v = "";
    	
    	for (int idx = 2; idx <= p.length(); idx += 2) {
    		String target = p.substring(0, idx);
    		boolean result = isBalancedGaro(target);
    		
    		if (result) {
    			u = target;
    			
    			if (idx == p.length()) {
    				v = "";
    			} else {
    				v = p.substring(idx);
    			}
    			
    			break;
    		}
    	}
    	
    	//3.
    	if (isRightGaro(u)) {
    		return u + solution(v);
    	}     		
    	
    	//4.
    	String answer = "(" + solution(v) + ")";
    	String nxt = "";
    	StringBuilder sb = new StringBuilder();
    	sb.append("");
    	
    	if (u.length() == 2) {
    		
    	} else {
    		nxt = u.substring(1, u.length() - 1);
    		for (int i = 0; i < nxt.length(); ++i) {
    			if (nxt.charAt(i) == LEFT) sb.append(RIGHT);
    			else sb.append(LEFT);
    		}
    	}
    	
    	answer += sb.toString();
        return answer;
    }
}