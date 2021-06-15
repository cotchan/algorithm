package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Pair implements Comparable<Pair> {

		int first,second;
		
		Pair(int f, int s) {
			first = f;
			second = s;
		}
		
		@Override
		public int compareTo(Pair o) {
			if (this.first == o.first) {
				return this.second < o.second ? -1 : 1;
			} else {
				return this.first < o.first ? -1 : 1;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		int n = Integer.parseInt(br.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		for (int loop = 0; loop < n; ++loop) {
			int number = Integer.parseInt(br.readLine());
			
			if (number == 0) {
				if (pq.isEmpty()) {
					sb.append("0\n");
				} else {
					sb.append(pq.poll().second);
					sb.append('\n');
				}
			} else {
				pq.add(new Pair(Math.abs(number), number));
			}
		}
		
		System.out.print(sb.toString());
	}
}
