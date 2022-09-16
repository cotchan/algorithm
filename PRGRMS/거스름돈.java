class Solution {

    static final int MOD = 1_000_000_007;
    static int[] dp;

    public int solution(int n, int[] money) {

        dp = new int[n+1];
        dp[0] = 1;

        for (int i = 0; i < money.length; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (j >= money[i]) {
                    dp[j] += (dp[j - money[i]]) % MOD;
                }
            }
        }

        return dp[n];
    }
}
