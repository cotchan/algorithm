package PROGRAMMERS;

import java.util.*;

class Solution {
   
	static class Pair implements Comparable<Pair> {
		
		int first, second;
		Pair(int f, int s) {
			first = f;
			second = s;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.second > o.second) return -1;
			else if (this.second == o.second) return 0;
			else return 1;
		}
	}
		
	public int[] solution(String s) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        String[] info = s.split("[,|{|}]");
        
        for (String num : info) {
    		if (map.containsKey(num)) {
    			int now = map.get(num);
    			map.put(num, now + 1);
    		}
    		else {
    			map.put(num, 1);
    		}
        }
        
        LinkedList<Pair> list = new LinkedList<>();
        
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        	String number = entry.getKey();
        	int cnt = entry.getValue();
        	
        	list.add(new Pair(Integer.parseInt(number), cnt));
        }
        
        Collections.sort(list);
        List<Integer> result = new ArrayList<>();
        
        int[] answer = new int[list.size()];
        int idx = 0;
        
        for (Pair p : list)
        {
        	answer[idx] = p.first;
        	idx++;
        }
        
        return answer;
    }
}