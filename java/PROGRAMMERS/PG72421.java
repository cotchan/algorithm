package PROGRAMMERS;

import java.util.*;

class Solution {
    
	static final String[] LANGS = {"-", "cpp", "java", "python"};
	static final String[] JOBS = {"-", "backend", "frontend"};
	static final String[] YEARS = {"-", "junior","senior"};
	static final String[] FOODS = {"-", "chicken", "pizza"};
	
	static final int OFFSET = 4;
	
	ArrayList<Integer>[] arrs = new ArrayList[256];
	
	public int calculateOffset(String lang, String job, String year, String food) {
		
		int lang_result = 0;
		int job_result = 0;
		int year_result = 0;
		int food_result = 0;
		
		for (int idx = 0; idx < LANGS.length; ++idx) {
			if (lang.compareTo(LANGS[idx]) == 0) {
				lang_result = idx;
				break;
			}
		}
		
		for (int idx = 0; idx < JOBS.length; ++idx) {
			if (job.compareTo(JOBS[idx]) == 0) {
				job_result = idx;
				break;
			}
		}
		
		for (int idx = 0; idx < YEARS.length; ++idx) {
			if (year.compareTo(YEARS[idx]) == 0) {
				year_result = idx;
				break;
			}
		}
		
		for (int idx = 0; idx < FOODS.length; ++idx) {
			if (food.compareTo(FOODS[idx]) == 0) {
				food_result = idx;
				break;
			}
		}
		
		return (lang_result*OFFSET*OFFSET*OFFSET) + (job_result*OFFSET*OFFSET) + (year_result * OFFSET) + (food_result);
	}
	
	public int[] solution(String[] info, String[] query) {
		
		for (int i = 0; i < arrs.length; ++i) {
			arrs[i] = new ArrayList<>();
		}
    	
		for (String infomation : info) {
			String[] infos = infomation.split(" ");
			String lang = infos[0];
			String job = infos[1];
			String year = infos[2];
			String food = infos[3];
			String score = infos[4];
			
			int hash1 = calculateOffset(lang, job, year, food);
			int hash2 = calculateOffset("-", job, year, food);
			int hash3 = calculateOffset(lang, "-", year, food);
			int hash4 = calculateOffset(lang, job, "-", food);
			int hash5 = calculateOffset(lang, job, year, "-");
			int hash6 = calculateOffset("-", "-", year, food);
			int hash7 = calculateOffset("-", job, "-", food);
			int hash8 = calculateOffset("-", job, year, "-");
			int hash9 = calculateOffset(lang, "-", "-", food);
			int hash10 = calculateOffset(lang, "-", year, "-");
			int hash11 = calculateOffset(lang, job, "-", "-");
			int hash12 = calculateOffset("-", "-", "-", food);
			int hash13 = calculateOffset("-", "-", year, "-");
			int hash14 = calculateOffset("-", job, "-", "-");
			int hash15 = calculateOffset(lang, "-", "-", "-");
			int hash16 = calculateOffset("-", "-", "-", "-");
			
			arrs[hash1].add(Integer.parseInt(score));
			arrs[hash2].add(Integer.parseInt(score));
			arrs[hash3].add(Integer.parseInt(score));
			arrs[hash4].add(Integer.parseInt(score));
			arrs[hash5].add(Integer.parseInt(score));
			arrs[hash6].add(Integer.parseInt(score));
			arrs[hash7].add(Integer.parseInt(score));
			arrs[hash8].add(Integer.parseInt(score));
			arrs[hash9].add(Integer.parseInt(score));
			arrs[hash10].add(Integer.parseInt(score));
			arrs[hash11].add(Integer.parseInt(score));
			arrs[hash12].add(Integer.parseInt(score));
			arrs[hash13].add(Integer.parseInt(score));
			arrs[hash14].add(Integer.parseInt(score));
			arrs[hash15].add(Integer.parseInt(score));
			arrs[hash16].add(Integer.parseInt(score));
		}
		
		for (int i = 0; i < arrs.length; ++i) {
			Collections.sort(arrs[i]);
		}
		
		int[] answer = new int[query.length];
		int qIdx = 0;
		
		for (String q : query) {
			
			String[] queryInfo = q.split("and");
			String lang = queryInfo[0].trim();
			String job = queryInfo[1].trim();
			String year = queryInfo[2].trim();
			String restInfo = queryInfo[3].trim();
			
			String[] foodAndScore = restInfo.split(" ");
			String food = foodAndScore[0].trim();
			String score = foodAndScore[1].trim();
			
			int targetHash = calculateOffset(lang, job, year, food);
			int targetScore = Integer.parseInt(score);
			
			int lo,hi;
			lo = 0;
			hi = arrs[targetHash].size();
			
			int before = -1;
			int mid = 0;
			
			while (lo <= hi) {
				mid = (lo + hi) / 2;
				
				if (mid == arrs[targetHash].size()) {
					break;
				}
				
				int userScore = arrs[targetHash].get(mid);
				
				if (userScore < targetScore) {
					before = mid;
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			
			//before는 인덱스를 담고 있으므로 계산한 값에서 -1을 더 뺴줘야 올바른 값이 나옴
			int result = (arrs[targetHash].size() - before) - 1;
			answer[qIdx] = result;
			qIdx++;
		}
    	
        return answer;
    }
}