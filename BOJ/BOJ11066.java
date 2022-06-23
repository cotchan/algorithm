
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] books, pSum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < t; ++testCase) {
            N = Integer.parseInt(br.readLine());

            pSum = new int[N];
            dp = new int[N][N];

            String[] bookInfo = br.readLine().split(" ");
            books = Arrays.stream(bookInfo).mapToInt(Integer::new).toArray();

            pSum[0] = books[0];
            for (int i = 1; i < N; ++i) {
                pSum[i] = pSum[i-1] + books[i];
            }

            for (int range = 1; range < N; ++range) {
                for (int st = 0; st + range < N; ++st) {
                    int end = st + range;
                    int sum = st == 0 ? pSum[end] : pSum[end] - pSum[st-1];

                    dp[st][end] = Integer.MAX_VALUE;
                    for (int r = 0; r < range; ++r) {
                        dp[st][end] = Math.min(dp[st][end], dp[st][st+r] + dp[st+r+1][end] + sum);
                    }
                }
            }

            System.out.println(dp[0][N-1]);
        }
    }
}
