package boj;

import java.io.*;
import java.util.*;

class Solution {
	
	static Map<Long,Long> mappingInfo = new HashMap<>();
	
	public void setNode(long nowNumber, long nxtNumber) {
		mappingInfo.put(nowNumber, nxtNumber);
		return;
	}
	
	public long findRealRoomNumber(long roomNumber) {
		if (mappingInfo.containsKey(roomNumber)) {
			long nxtRoomNumber = findRealRoomNumber(mappingInfo.get(roomNumber));
			setNode(roomNumber, nxtRoomNumber);
			return nxtRoomNumber;
		} else {
			setNode(roomNumber,roomNumber + 1);
			return roomNumber;
		}
	}
	
    public long[] solution(long k, long[] room_number) {
    	
    	long[] answer = new long[room_number.length];
    	
    	int idx = 0;
    	for (long number : room_number) {
    		long result = findRealRoomNumber(number);
    		answer[idx] = result;
    		idx++;
    	}
        
        return answer;
    }
}
