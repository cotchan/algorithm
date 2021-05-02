package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static int ans;
	static int USER_ID_SIZE;
	static int BANNED_ID_SIZE;
	
	static boolean[] isSelected;
	static LinkedList<String> picks = new LinkedList<>();
	static Set<Integer> answerSets = new HashSet<>();
	static Map<String, Integer> keyMaps = new HashMap<>();
	
	public int makeStateBit() {
		
		int result = 0;
		
		for (String userId : picks) 
		{
			int idx = keyMaps.get(userId);
			result += (1 << idx);
		}
		
		return result;
	}
	
	public void doMatchAllBannedId(String[] banned_id) {
		
		int matchCnt = 0;
		boolean[] isUsed = new boolean[BANNED_ID_SIZE];
		
		for (String userId : picks) 
		{
			boolean isMatch = false;
			
			for (int i = 0; i < BANNED_ID_SIZE; ++i) 
			{
				if (isMatch)
				{
					break;
				}
				else if (isUsed[i])
				{
					continue;
				}
				
				if (userId.length() != banned_id[i].length())
					continue;
				
				//userId.length() == banned_id[i].length()
				int idx;
				
				for (idx = 0; idx < banned_id[i].length(); ++ idx) 
				{
					if (banned_id[i].charAt(idx) == '*')
					{
						continue;
					}
					else if (userId.charAt(idx) == banned_id[i].charAt(idx)) 
					{
						continue;
					}
					else
					{
						break;
					}
				}
				
				if (idx == banned_id[i].length())
				{
					isMatch = true;
					matchCnt += 1;
					isUsed[i] = true;
				}
			}
		}
		
		if (matchCnt == BANNED_ID_SIZE) 
		{
			int nowState = makeStateBit();
			answerSets.add(nowState);
		}
		
		return;
	}
	
	public void permutation(String[] user_id, String[] banned_id, int pickCnt) {
		
		if (pickCnt == BANNED_ID_SIZE) 
		{
			//do process
			doMatchAllBannedId(banned_id);
			
			return;
		}
		
		for (int idx = 0; idx < USER_ID_SIZE; ++idx)
		{
			if (!isSelected[idx])
			{
				isSelected[idx] = true;
				picks.addLast(user_id[idx]);
				permutation(user_id, banned_id, pickCnt+1);
				picks.removeLast();
				isSelected[idx] = false;
			}
		}
		
	}
	
    public int solution(String[] user_id, String[] banned_id) {
    	
    	USER_ID_SIZE = user_id.length;
    	BANNED_ID_SIZE = banned_id.length;
    	isSelected = new boolean[USER_ID_SIZE];
    	
    	for (int idx = 0; idx < USER_ID_SIZE; ++idx) 
    	{
    		keyMaps.put(user_id[idx], idx);
    	}
    	
    	permutation(user_id, banned_id, 0);
    	
        int answer = answerSets.size();
        
        return answer;
    }
}
