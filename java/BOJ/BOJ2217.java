package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2217 {

	static int N;
	static Integer[] numbers;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		numbers = new Integer[N];
		
		for (int i = 0; i < N; ++i)
		{
			int number;
			number = Integer.parseInt(br.readLine());
			numbers[i] = number;
		}
		
		Arrays.sort(numbers, Collections.reverseOrder());
		
		int maxv = -1;
		
		for (int i = 0; i < N; ++i)
		{
			int candidate = numbers[i]*(i+1);
			
			if (candidate >= maxv)
			{
				maxv = candidate;
			}
			else
			{
				break;
			}
		}
		
		System.out.println(maxv);
		
	}
}
