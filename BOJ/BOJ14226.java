import java.io.*;
import java.util.*;

public class Main {

    static final int S_MAX = 1005;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        dp = new int[S_MAX];
        for (int i = 0; i < S_MAX; ++i) {
            dp[i] = i;
        }

        dp[1] = 0;

        for (int i = 2; i <= S; ++i) {
            int nxt = i+1;
            int loop = 0;
            for (int j = nxt; j < S_MAX; j++) {
                dp[i] = Math.min(dp[i], dp[j] + 1 + loop);
                loop++;
            }

            nxt = 2*i;
            loop = 0;

            for (int j = nxt; j < S_MAX; j += i) {
                dp[j] = Math.min(dp[j], dp[i] + 2 + loop);
                loop++;
            }
        }

        System.out.println(dp[S]);
    }
}
