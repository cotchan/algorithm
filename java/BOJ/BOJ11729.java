package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static StringBuilder sb = new StringBuilder();
	static int callCnt;
	
	public static void solve(int n, int src, int dst) {
		
		//기저 사례
		if (n == 0) {
			return;	
		}
		
		callCnt += 1;
		
		int mid = 3 - (src + dst);
		solve(n-1, src, mid);
		
		sb.append(src+1);
		sb.append(" ");
		sb.append(dst+1);
		sb.append('\n');
		
		solve(n-1,mid,dst);
	}

	//BOJ2493
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int K = Integer.parseInt(br.readLine());
		solve(K,0,2);
		
		System.out.println(callCnt);
		System.out.println(sb.toString());
	}
}
