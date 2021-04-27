package PROGRAMMERS;

import java.util.*;

class Solution {
	
	List<File> myFiles = new ArrayList<>();
	
	static class File implements Comparable<File> {
		String head;
		int number;
		int isbn;
		String origin;
		
		public File(String head, int number, int isbn, String origin) {
			this.head = head;
			this.number = number;
			this.isbn = isbn;
			this.origin = origin;
		}

		@Override
		public int compareTo(File o) {
			int result = this.head.compareTo(o.head);
			
			if (result == 0) 
			{
				if (this.number == o.number) 
				{
					return (this.isbn < o.isbn) ? -1 : 1;
				}
				else 
				{
					if (this.number <= o.number) 
						return -1;
					else 
						return 1;
				}
			}
			else 
			{
				return result;
			}
		}
	}
	
	int zero = '0';
	int nine = '9';
	
    public String[] solution(String[] files) {
    	
    	int isbn_idx = 0;
    	
    	for (String file : files) {
    		
    		String target = file.toLowerCase();
    		
    		//숫자 부분 찾기 Start
    		int min_idx, max_idx;
    		
    		min_idx = target.length();
    		max_idx = -1;
    		
    		boolean isFindNumberPart = false;
    		
    		for (int idx = 0; idx < target.length(); ++idx) {
    		
    			if (isFindNumberPart)
    				break;
    			
    			char c = target.charAt(idx);
    			int target_ascii = c;

    			if (zero <= target_ascii && target_ascii <= nine) {
    				
    				int number_idx = idx;
    				
    				while (number_idx < target.length()) {
    					
    	    			char c2 = target.charAt(number_idx);
    	    			int target_ascii2 = c2;
    	    			
    	    			if (zero <= target_ascii2 && target_ascii2 <= nine) 
    	    			{
            				min_idx = Math.min(min_idx, number_idx);
            				max_idx = Math.max(max_idx, number_idx);
    	    			}
    	    			else
    	    			{
    	    				isFindNumberPart = true;
    	    				break;
    	    			}
    	    			
    	    			number_idx += 1;
    				}
    			}
    		}
    		//숫자 부분 찾기 End
    		
    		String headerPart = target.substring(0, min_idx);
    		String numberPart = target.substring(min_idx, max_idx+1);
    		int number = Integer.parseInt(numberPart);
    		
    		File newFile = new File(headerPart, number, isbn_idx, file);
    		
    		myFiles.add(newFile);
    		isbn_idx += 1;
    	}
    	
    	Collections.sort(myFiles);
    	
    	String[] answer = new String[files.length];
    	
    	for (int i = 0; i < myFiles.size(); ++i) 
    	{
    		answer[i] = myFiles.get(i).origin;
    	}
        
        return answer;
    }
}