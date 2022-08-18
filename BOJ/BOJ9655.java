import java.io.*;
import java.util.*;

public class Main {

    static final int SANGGEUN = -1;
    static final int CHANGYOUNG = 1;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][N+1];
        dp[0][N] = SANGGEUN;

        int now = SANGGEUN;
        for (int i = 0; i < N; ++i) {
            for (int j = N; j >= 1; --j) {
                if (dp[i][j] == 0) continue;

                if (j >= 3) {
                    dp[i+1][j-3] = now;
                }

                dp[i+1][j-1] = now;
            }

            now = -now;
        }

        for (int i = 1; i <= N; ++i) {
            if (dp[i][0] == 0) continue;

            int ans = dp[i][0];
            if (ans == SANGGEUN) {
                System.out.println("SK");
                return;
            } else {
                System.out.println("CY");
                return;
            }
        }
    }
}
