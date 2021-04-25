package PROGRAMMERS;

import java.util.*;

class Solution {
	
	final String DIVIDER = "#";
	int columnSize, rowSize, ans;
	
	int[] origin;
	boolean[] isUsed;
	LinkedList<Integer> picks = new LinkedList<>();
	Set<Integer> candidateKeys = new HashSet<>();

	int state2Bit() {
		int result = 0;
		
		for (int idx : picks) {
			result += (1 << idx);
		}
		
		return result;
	}
	
	boolean doProcess(String[][] relation) {
		
		Set<String> rows = new HashSet<>();
		
		//후보 키 만들기
		for (int i = 0; i < relation.length; ++i) {
			
			StringBuilder sb = new StringBuilder();

			for (Integer j : picks) {
				String fieldName = relation[i][j];
				sb.append(fieldName);
				sb.append(DIVIDER);
			}
			
			String nowRow = sb.toString();
			
			if (rows.contains(nowRow)) {
				return false;
			}
			else {
				rows.add(nowRow);
			}
		}
		
		int nowKey = state2Bit();
		
		for (Integer candidateKey : candidateKeys) {
			if ((candidateKey & nowKey) == candidateKey) {
				return false;
			}
		}
		
		candidateKeys.add(nowKey);
		
		return true;
		
	}

	void combination(String[][] relation, int idx, int pickCnt, int pickSize) {
		
		if (pickCnt == pickSize) {
			//doProcess
			if (doProcess(relation))
			{
				ans++;
			}
			
			return;
		}
		else if (idx == columnSize) {
			return;
		}
		
		if (!isUsed[idx]) {
			isUsed[idx] = true;
			picks.addLast(origin[idx]);
			combination(relation, idx + 1, pickCnt + 1, pickSize);
			picks.removeLast();
			isUsed[idx] = false;
		}
		
		combination(relation, idx + 1, pickCnt, pickSize);
		
	}
	
    public int solution(String[][] relation) {
        int answer = 0;
    
        columnSize = relation[0].length;
        rowSize = relation.length;
        isUsed = new boolean[columnSize];
        origin = new int[columnSize];
        
        for (int i = 0; i < columnSize; ++i)
        	origin[i] = i;
        
        for (int pickCnt = 1; pickCnt <= columnSize; ++pickCnt) {
        	combination(relation, 0, 0, pickCnt);
        }
        
        answer = ans;
        return answer;
    }
}