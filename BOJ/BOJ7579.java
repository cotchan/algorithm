import java.io.*;
import java.util.*;

public class Main {

    static final int MAX_COST = 10001;
    static int N, M;
    static int[] memories;
    static int[] costs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dp = new int[N+1][MAX_COST];
        memories = new int[N + 1];
        costs = new int[N + 1];

        String[] memoryInfo = br.readLine().split(" ");

        for (int i = 0; i < N; ++i) {
            memories[i+1] = Integer.parseInt(memoryInfo[i]);
        }

        String[] costInfo = br.readLine().split(" ");

        int totalCost = 0;

        for (int i = 0; i < N; ++i) {
            costs[i+1] = Integer.parseInt(costInfo[i]);
            totalCost += costs[i+1];
        }

        for (int i = 1; i <= N; ++i) {
            for (int cost = 0; cost <= totalCost; ++cost) {
                //i번째 이전까지 고려했을 때
                dp[i][cost] = dp[i-1][cost];

                if (cost >= costs[i]) {
                    dp[i][cost] = Math.max(dp[i-1][cost], dp[i-1][cost - costs[i]] + memories[i]);
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= N; ++i) {
            for (int cost = 0; cost <= totalCost; ++cost) {
                if (dp[i][cost] >= M) {
                    ans = Math.min(ans, cost);
                }
            }
        }

        System.out.println(ans);
    }
}
