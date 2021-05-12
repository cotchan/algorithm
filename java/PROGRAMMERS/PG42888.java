package PROGRAMMERS;

import java.util.*;

class Solution {

	static class Pair {
		String id, act;
		
		Pair(String _id, String _act) {
			this.id = _id;
			this.act = _act;
		}
	}
	
	static final String ENTER = "Enter";
	static final String LEAVE = "Leave";
	static final String CHANGE = "Change";
	
	Map<String,String> userInfo = new HashMap<>();
	List<Pair> actionList = new LinkedList<>();
	
    public String[] solution(String[] record) {
    	
    	for (String info : record) {
    		String[] infoSplit = info.split(" ");
    		
    		String actionType = infoSplit[0];
    		String userId = infoSplit[1];
    		
    		if (actionType.compareTo(ENTER) == 0 || actionType.compareTo(CHANGE) == 0) {
    			String nickName = infoSplit[2];
    			userInfo.put(userId, nickName);
    		} 
    		
    		if (actionType.compareTo(ENTER) == 0 || actionType.compareTo(LEAVE) == 0) {
    			actionList.add(new Pair(userId, actionType));	
    		}
    	}
    	
    	List<String> answerList = new LinkedList<>();
    	
    	for (Pair action : actionList) {
    		StringBuilder sb = new StringBuilder();
    		sb.append(userInfo.get(action.id));
    		sb.append("님이 ");
    		
    		if (action.act.compareTo(ENTER) == 0) {
    			sb.append("들어왔습니다.");
    		} else {
    			sb.append("나갔습니다.");
    		}
    		
    		answerList.add(sb.toString());
    	}
    	
        String[] answer = answerList.toArray(new String[answerList.size()]);
        return answer;
    }
}
