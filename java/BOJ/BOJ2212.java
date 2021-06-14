package boj;

import java.io.*;
import java.util.*;

class Main {
	
	static int N,K;
	static List<Integer> adjs = new ArrayList<>();
	static List<Integer> sensors = new ArrayList<>();
	
	//BOJ2212
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		if (K >= N) {
			System.out.println(0);
			return;
		}
		
		String[] info = br.readLine().split(" ");
		
		for (int i = 0; i < N; ++i) {
			int pos = Integer.parseInt(info[i]);
			sensors.add(pos);
		}
		
		Collections.sort(sensors);
		
		for (int i = 0; i < sensors.size() - 1;  ++i) {
			int length = sensors.get(i+1) - sensors.get(i);
			adjs.add(length);
		}
		
		Collections.sort(adjs);
		
		int answer = 0;
		
		for (int i = 0; i < N-K; ++i) {
			answer += adjs.get(i);
		}
		
		System.out.println(answer);
	}
}
