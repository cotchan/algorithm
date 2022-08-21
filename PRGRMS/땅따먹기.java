class Solution {

    static int N;
    static int[][] dp;

    int solution(int[][] land) {

        N = land.length;
        dp = new int[N+1][4];

        for (int j = 0; j < 4; ++j) {
            dp[1][j] = land[0][j];
        }

        for (int i = 1; i < N; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (j == k) continue;
                    dp[i+1][j] = Math.max(dp[i+1][j], dp[i][k] + land[i][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; ++i) {
            answer = Math.max(answer, dp[N][i]);
        }

        return answer;
    }
}
