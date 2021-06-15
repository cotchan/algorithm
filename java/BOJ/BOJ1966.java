package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Pair {
		int first, second;
		
		Pair (int f, int s) {
			first = f;
			second = s;
		}
	}
	
	//BOJ1966
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = Integer.parseInt(br.readLine());
		
		for (int loop = 0; loop < t; ++loop) {
			int n,m;
			
			String[] info = br.readLine().split(" ");
			
			n = Integer.parseInt(info[0]);
			m = Integer.parseInt(info[1]);
			
			String[] rows = br.readLine().split(" ");
			
			Queue<Pair> q = new LinkedList<>();
			LinkedList<Integer> importances = new LinkedList<>();
			
			for (int i = 0; i < n; ++i) {
				q.add(new Pair(Integer.parseInt(rows[i]), i));
				importances.addLast(Integer.parseInt(rows[i]));
			}
			
			Collections.sort(importances, (a,b) -> {
				if (a > b) return -1;
				else if (a == b) return 0;
				else return 1;
			});
			
			int cnt = 1;
			int answer = 0;
			
			while (!q.isEmpty()) {
				Pair now = q.poll();
				
				if (importances.getFirst() == now.first && now.second == m) {
					answer = cnt;
					break;
				} else if (importances.getFirst() == now.first) {
					importances.pollFirst();
					cnt++;
				} else {
					q.add(now);
				}
			}
			
			bw.append(String.valueOf(answer));
			bw.newLine();
		}
		
		bw.flush();
	}
}
