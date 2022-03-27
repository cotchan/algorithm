import java.util.Arrays;

class Solution {

    public static final int MAX = Integer.MAX_VALUE;
    public static int[] dp = new int[100004];

    public int fibo(int n) {
        if (dp[n] != MAX) {
            return dp[n];
        }

        int v1 = fibo(n - 1) % 1234567;
        int v2 = fibo(n - 2) % 1234567;
        return dp[n] = (v1 + v2) % 1234567;
    }

    public int solution(int n) {
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        dp[1] = 1;

        int answer = fibo(n);
        return answer;
    }
}
