package boj;

import java.io.*;
import java.util.*;

class Main {

	static class Pair {
		int number, height;
		
		Pair(int n, int h) {
			number = n;
			height = h;
		}
	}
	
	static int N;
	static Stack<Pair> rests;	
	static Queue<Pair> tops;
	
	//BOJ2493
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		tops = new LinkedList<>();
		rests = new Stack<>();
		
		N = Integer.parseInt(br.readLine());
		
		String[] heights = br.readLine().split(" ");
		
		List<Integer> results = new LinkedList<>();
		
		for (int idx = 0; idx < heights.length; ++idx) {
			String height = heights[idx];
			int h = Integer.parseInt(height);
			tops.add(new Pair(idx+1, h));
		}
		
		for (Pair now : tops) {
			
			if (rests.isEmpty()) {
				results.add(0);
				rests.push(now);
			} else {
				
				while (!rests.isEmpty() && rests.peek().height <= now.height) {
					rests.pop();
				}
				
				if (rests.isEmpty()) {
					results.add(0);
					rests.push(now);
				} else {
					results.add(rests.peek().number);
					rests.push(now);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int result : results) {
			sb.append(result);
			sb.append(' ');
		}
		
		System.out.println(sb.toString());
	}
}

