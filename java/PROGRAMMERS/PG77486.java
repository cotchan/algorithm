package PROGRAMMERS;

import java.util.*;

class Solution {
	
	static final String ROOT = "MINHO_MINHO";
	static final int PRICE = 100;
	
	Map<String,String> sellerTree = new HashMap<>();
	Map<String,Integer> indexTree = new HashMap<>();
	int[] incomes;
	
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
    	
    	incomes = new int[enroll.length];
    	
    	for (int idx = 0; idx < referral.length; ++idx) 
    	{
    		String parent = referral[idx];
    		String child = enroll[idx];
    		
    		if (parent.compareTo("-") == 0) {
    			sellerTree.put(child, ROOT);
    		} else {
    			sellerTree.put(child, parent);
    		}
    		
    		indexTree.put(child, idx);
    	}
    	
    	for (int idx = 0; idx < seller.length; ++idx) {
    		
    		int income = amount[idx] * PRICE;
    		String person = seller[idx];
    		
    		while (income > 0 && (person.compareTo(ROOT) != 0)) {
    		
    			int myIdx = indexTree.get(person);
    			int myIncome = income - (income / 10) ;
    		
    			incomes[myIdx] += myIncome;
    			
    			income /= 10;
    			person = sellerTree.get(person);
    		}
    	}
    	
        return incomes;
    }
}