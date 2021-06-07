package boj;

import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static boolean[] isPrime;
	
	//BOJ1644
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//era
		N = Integer.parseInt(br.readLine());
		
		if (N == 1) {
			System.out.println(0);
			return;
		}
		
		isPrime = new boolean[N+1];
		Arrays.fill(isPrime, true);
		
		int offset = (int)Math.sqrt(Double.valueOf(String.valueOf(N)));
		
		for (int i = 2; i <= offset; ++i) {
			
			if (!isPrime[i]) {
				continue;
			}
			
			for (int j = i+i; j <= N; j += i) {
				isPrime[j] = false;
			}
		}

		//two pointer
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 2; i <= N; ++i) {
			if (isPrime[i]) {
				arr.add(i);
			}
		}
		
		int ans = 0, l = 0, r = 0, sum = arr.get(0);
		
		while (r < arr.size() && l <= r) {
			if (sum == N) {
				ans++;
				sum -= arr.get(l);
				l++;
			}  
			else if (sum < N) {
				r++;
				if (r == arr.size())
					break;
				
				sum += arr.get(r);
			}
			//sum > N
			else {
				sum -= arr.get(l);
				l++;
			}
		}
		
		System.out.println(ans);
	}
}
