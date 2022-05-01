import java.io.*;
import java.util.*;


public class Main {

    public static final int IMPOSSIBLE = 10_000_000;
    public static int N;
    public static int[][] costs;
    public static int[][] dp;   //[state][node]

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        costs = new int[N][N];
        dp = new int[1<<N][N];

        for (int i = 0; i < (1 << N); ++i) {
            Arrays.fill(dp[i], IMPOSSIBLE);
        }

        for (int i = 0; i < N; ++i) {
            String[] rows = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int cost = Integer.parseInt(rows[j]);

                costs[i][j] = cost;
            }
        }

        System.out.println(func(1, 0));
    }

    public static int func(int visitState, int curNode) {
        // 기저 사례
        if (visitState == (1 << N) - 1) {
            return costs[curNode][0] == 0 ? IMPOSSIBLE : costs[curNode][0];
        }

        if (dp[visitState][curNode] != IMPOSSIBLE) {
            return dp[visitState][curNode];
        }

        int result = IMPOSSIBLE;

        for (int nxt = 0; nxt < N; ++nxt) {
            if (costs[curNode][nxt] == 0) continue;
            if ((visitState & (1 << nxt)) != 0) continue;

            result = Math.min(result, func((visitState | (1 << nxt)), nxt) + costs[curNode][nxt]);
        }

        return dp[visitState][curNode] = result;
    }
}
