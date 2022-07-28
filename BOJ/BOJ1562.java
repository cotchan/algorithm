import java.io.*;

public class Main {

    static final int MOD = 1000000000;
    static int N;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[1<<10][10][N+1];

        for (int i = 1; i <= 9; ++i) {
            int state = (1 << i);
            dp[state][i][1] = 1;
        }

        for (int i = 2; i <= N; ++i) {
            for (int num = 0; num <= 9; ++num) {
                for (int state = 0; state < (1 << 10); ++state) {
                    int nxtState = (state | (1 << num));
                    if (num == 0) {
                        dp[nxtState][num][i] += (dp[state][num+1][i-1]) % MOD;
                    } else if (num == 9) {
                        dp[nxtState][num][i] += (dp[state][num-1][i-1]) % MOD;
                    } else {
                        dp[nxtState][num][i] += (dp[state][num+1][i-1]) % MOD;
                        dp[nxtState][num][i] += (dp[state][num-1][i-1]) % MOD;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= 9; ++i) {
            ans += (dp[(1<<10) -1][i][N]) % MOD;
            ans %= MOD;
        }

        System.out.println(ans);
    }
}
