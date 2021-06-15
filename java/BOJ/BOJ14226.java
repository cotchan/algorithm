package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static class Tuple {
		int f,s,t;	//first,second,third
		
		Tuple(int first, int second, int third) {
			f = first;
			s = second;
			t = third;
		}
	}
	
	static boolean[][] isVisited = new boolean[2000][2000];
	
	public static int bfs(int dst) {
		
		Queue<Tuple> q = new LinkedList<>();
		
		//0초에, 1개 이모티콘, 클립보드에 0개
		q.add(new Tuple(0,1,0));
		
		while (!q.isEmpty()) {
			
			Tuple now = q.poll();
			
			if (now.s == dst) {
				return now.f;
			}
			
			if (!isVisited[now.s][now.t]) {
				isVisited[now.s][now.t] = true;
				
				//클립보드에 복사
				if (now.s != now.t) {
					q.add(new Tuple(now.f+1, now.s, now.s));
				}
				
				//붙여넣기
				if (now.t > 0) {
					int nxtValue = now.s + now.t;
					if (nxtValue <= 2 * dst) {
						q.add(new Tuple(now.f+1, nxtValue, now.t));
					}
				}
				
				//화면에 있는 이모티콘 중 하나를 삭제
				if (now.s > 1) {
					q.add(new Tuple(now.f+1, now.s -1, now.t));
				}
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int dst = Integer.parseInt(br.readLine());
		System.out.println(bfs(dst));
	}
}
