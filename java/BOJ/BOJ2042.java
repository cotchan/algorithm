package boj;

import java.io.*;
import java.util.*;

public class Main {
	
	static final int SEG_BASE_SIZE = 1 << 20;
	static int N,M,K;
	static long[] trees;
	
	public static void update(long[] segTree, int idx, Long num, int bit, int st, int en) {
		
		//±âÀú »ç·Ê
		if (st == en) {
			segTree[bit] = num;
			return;
		}
		
		int mid = (st + en) / 2;
		
		if (idx <= mid) {
			update(segTree, idx, num, 2*bit, st, mid);
		} else {
			update(segTree, idx, num, 2*bit+1, mid+1, en);
		}
		
		segTree[bit] = segTree[2*bit] + segTree[2*bit+1];
	}
	
	public static long sumQuery(long[] segTree, int n1, int n2, int bit, int st, int en) {
		
		if (n2 < n1 || en < n1 || n2 < st) {
			return 0;
		}
		
		int mid = (st + en) / 2;
		
		if (n1 <= st && en <= n2) {
			return segTree[bit];
		}
		
		return sumQuery(segTree, n1, n2, 2*bit, st, mid) + sumQuery(segTree, n1, n2, 2*bit+1, mid+1, en);
	}
	
	//BOJ2042
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		K = Integer.parseInt(info[2]);
		trees = new long[2*SEG_BASE_SIZE];
		
		for (int idx = 0; idx < N; ++idx) {
			long number = Long.parseLong(br.readLine());
			update(trees, idx, number, 1, 0, SEG_BASE_SIZE-1);
		}
		
		for (int loop = 0; loop < M + K; ++loop) {
			String[] oper = br.readLine().split(" ");
			
			int type = Integer.parseInt(oper[0]);
			int b = Integer.parseInt(oper[1]);
			Long c = Long.parseLong(oper[2]);
			
			if (type == 1) {
				update(trees, b-1, c, 1, 0, SEG_BASE_SIZE-1);
			} else {
				int n2 = Long.valueOf(c).intValue();
				long result = sumQuery(trees, b-1,n2-1,1,0, SEG_BASE_SIZE-1);
				bw.append(String.valueOf(result));
				bw.newLine();
			}
		}
		
		bw.flush();
	}
}
