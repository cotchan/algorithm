package boj;

import java.io.*;
import java.util.*;

public class Main {

	static class Pair {
		int first;
		long second;
		
		Pair(int f, long s) {
			first = f;
			second = s;
		}
	}
	
	static final long MAX_VALUE = 10000000000L;
	static int N,M;
	static List<Pair>[] graphs;
	
	//BOJ11657
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String[] info = br.readLine().split(" ");
		
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		
		graphs = new LinkedList[N];
		for (int i = 0; i < N; ++i) {
			graphs[i] = new LinkedList<>();
		}
		
		for (int i = 0; i < M; ++i) {
			String[] edges = br.readLine().split(" ");
			int a,b;
			long c;
			a = Integer.parseInt(edges[0]);
			b = Integer.parseInt(edges[1]);
			c = Long.parseLong(edges[2]);
			graphs[a-1].add(new Pair(b-1,c));
		}
		
		long[] dists = new long[N];
		Arrays.fill(dists, MAX_VALUE);
		dists[0] = 0L;
		
		boolean isMinusCycle = false;
		
		for (int loop = 0; loop < N; ++loop) {
			for (int i = 0; i < N; ++i) {
				for (Pair nxt : graphs[i]) {
					int nxtNode = nxt.first;
					long nxtWeight = nxt.second;
					
					if (dists[i] != MAX_VALUE && dists[nxtNode] > dists[i] + nxtWeight) {
						dists[nxtNode] = dists[i] + nxtWeight;
						
						if (loop == N-1) {
							isMinusCycle = true;
						}
					}
				}
			}
		}
		
		if (isMinusCycle) {
			bw.append(String.valueOf(-1));
		} else {
			for (int i = 1; i < N; ++i) {
				if (dists[i] == MAX_VALUE) {
					bw.append(String.valueOf(-1));
				} else {
					bw.append(String.valueOf(dists[i]));
				}
				bw.newLine();
			}
		}
		bw.flush();
	}
}
