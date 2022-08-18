import java.io.*;
import java.util.*;

public class Main {

    static int T, W;
    static int[][][] dp;
    static int[][] dropInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] twInfo = br.readLine().split(" ");
        T = Integer.parseInt(twInfo[0]);
        W = Integer.parseInt(twInfo[1]);
        dp = new int[T+1][W+2][3];

        for (int i = 0; i < T + 1; ++i) {
            for (int j = 0; j < W + 2; ++j) {
                Arrays.fill(dp[i][j], -1000000000);
            }
        }

        dropInfo = new int[T+1][3];

        for (int i = 1; i <= T; ++i) {
            int dropNumber = Integer.parseInt(br.readLine());
            dropInfo[i][dropNumber] = 1;
        }

        dp[0][W][1] = 0;
        dp[0][W-1][2] = 0;

        //dp[][][]: t초에 이동가능횟수는 w만큼 남았고 현재 위치가 0,1인 경우
        for (int t = 0; t < T; ++t) {
            for (int w = 0; w <= W; ++w) {
                dp[t+1][w][1] = Math.max(dp[t][w+1][2], dp[t][w][1]) + dropInfo[t+1][1];
                dp[t+1][w][2] = Math.max(dp[t][w+1][1], dp[t][w][2]) + dropInfo[t+1][2];
            }
        }

        int ans = 0;

        for (int w = 0; w <= W; ++w) {
            ans = Math.max(ans, dp[T][w][1]);
            ans = Math.max(ans, dp[T][w][2]);
        }

        System.out.println(ans);
    }
}
