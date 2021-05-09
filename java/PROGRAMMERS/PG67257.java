package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final String[] orders = {"+*-", "+-*", "*-+", "*+-", "-+*", "-*+"};

	public long calculate(long arg1, String oper, long arg2) {
		
		if (oper.compareTo("+") == 0) {
			return arg1 + arg2;
		}
		else if (oper.compareTo("*") == 0) {
			return arg1 * arg2;
		}
		else {
			return arg1 - arg2;
		}
	}
	
    public long solution(String expression) {
    	
    	String[] operands = expression.split("\\D");
    	String[] tmp = expression.split("\\d");
    	
    	ArrayList<String> operandsList = new ArrayList<>(Arrays.asList(operands));
    	ArrayList<String> operatorsList = new ArrayList<>();
    	
    	for (String oper : tmp) {
    		if (oper.compareTo("") != 0) {
    			operatorsList.add(oper);
    		}
    	}
    	
    	long answer = 0;
    	
    	for (String order : orders) {
    		
        	ArrayList<String> tmpOperandsList = new ArrayList<String>();
        	ArrayList<String> tmpOperatorsList = new ArrayList<String>();
        	tmpOperandsList.addAll(operandsList);
        	tmpOperatorsList.addAll(operatorsList);
        	
    		// ex.+*-
    		for (int idx = 0; idx < order.length(); ++idx) {
    			char c = order.charAt(idx);
    			String token = String.valueOf(c);
    			
    			int matchCnt = 99999;
    			
    			while (matchCnt != 0 && (tmpOperandsList.size() > 1)) {
    				
    				matchCnt = 0;
    				
        			for (int i = 0; i < tmpOperatorsList.size(); ++i) {
        				String target = tmpOperatorsList.get(i);
        				
        				if (target.compareTo(token) == 0) {
        					long arg1 = Long.parseLong(tmpOperandsList.get(i));
        					long arg2 = Long.parseLong(tmpOperandsList.get(i+1));
        					long result = calculate(arg1, target, arg2);
        					
        					
        					tmpOperandsList.remove(i);
        					tmpOperandsList.remove(i);
        					tmpOperandsList.add(i, String.valueOf(result));
        					tmpOperatorsList.remove(i);
        					matchCnt += 1;
        					break;
        				}
        			}
    			}
    		}
    		
    		long absValue = Long.parseLong(tmpOperandsList.get(0));
    		answer = Math.max(answer, Math.abs(absValue));
    	}
    	
        return answer;
    }
}