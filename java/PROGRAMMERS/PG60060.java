package PROGRAMMERS;

import java.util.*;

class Solution {
	
	public static final int TRIE_SIZE = 26;
	
	static class Trie {
		
		int child;
		boolean isFinished;
		Trie[] nxt;
		
		Trie() {
			child = 0;
			isFinished = false;
			nxt = new Trie[TRIE_SIZE];
		}
		
		void insert(String str, int idx) {
			
			if (idx == str.length()) {
				isFinished = true;
				return;
			} else {
				
				int target = str.charAt(idx) - 'a';
				
				if (nxt[target] == null) {
					nxt[target] = new Trie();
				} 
				
				nxt[target].insert(str,  idx + 1);
				child++;
			}
		}
		
		int find(String str, int idx) {
			
			if (idx == str.length()) {
				return isFinished == true ? 1 : 0;
			} else if (str.charAt(idx) == '?'){
				return child;
			} else {
				int target = str.charAt(idx) - 'a';
				
				if (nxt[target] == null)
					return 0;
				else 
					return nxt[target].find(str,  idx + 1);
			}
		}
	}
	
    public int[] solution(String[] words, String[] queries) {
        
        Trie[] root = new Trie[100001];
        Trie[] reverseRoot = new Trie[100001];
        
        for (String word : words) {
        	StringBuilder sb = new StringBuilder(word);
        	String reverseWord = sb.reverse().toString();
        	
        	int len = word.length();
        	
        	if (root[len] == null) {
        		root[len] = new Trie();
        		reverseRoot[len] = new Trie();
        	}
        	
        	root[len].insert(word, 0);
        	reverseRoot[len].insert(reverseWord, 0);
        }
        
        int[] answer = new int[queries.length];
        int idx = 0;
        for (String query : queries) {
        
        	StringBuilder sb = new StringBuilder(query);
        	String reverseQuery = sb.reverse().toString();
        	
        	int result = 0;
        	int len = query.length();
        	
        	if (root[len] != null) {
            	if (query.charAt(0) == '?') {
            		result = reverseRoot[len].find(reverseQuery, 0);   
        		} else {
        			result = root[len].find(query, 0);	     		
    			}
        	} 
        	answer[idx] = result;
        	idx += 1;
        }
      
        return answer;
    }
}
