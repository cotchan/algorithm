import java.io.*;
import java.util.*;

public class Main {

    static int N, M, K;
    static int[][] dp;
    static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmkInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmkInfo[0]);
        M = Integer.parseInt(nmkInfo[1]);
        K = Integer.parseInt(nmkInfo[2]);

        dp = new int[N+1][M+1];
        graph = new int[N+1][N+1];

        for (int i = 0; i < K; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int score = Integer.parseInt(edgeInfo[2]);

            st--; en--;

            graph[st][en] = Math.max(graph[st][en], score);
        }

        for (int i = 1; i < N; ++i) {
            if (graph[0][i] == 0) continue;
            dp[i][2] = Math.max(dp[i][2], graph[0][i]);
        }

        for (int visit = 2; visit < M; ++visit) {
            for (int now = 0; now < N; ++now) {
                if (dp[now][visit] != 0) {
                    for (int nxt = now+1; nxt < N; ++nxt) {
                        if (graph[now][nxt] == 0) continue;
                        int score = graph[now][nxt];
                        dp[nxt][visit+1] = Math.max(dp[nxt][visit+1], dp[now][visit] + score);
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 2; i <= M; ++i) {
            ans = Math.max(ans, dp[N-1][i]);
        }

        System.out.println(ans);
    }
}
