
import java.io.*;
import java.util.*;

public class Main {

    static final int LEAF = 100_000_000;
    static int N, M;
    static int[] dp;
    static boolean[] visit;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 0; t < testCase; ++t) {
            String[] nmInfo = br.readLine().split(" ");
            N = Integer.parseInt(nmInfo[0]);
            M = Integer.parseInt(nmInfo[1]);

            graph = new List[N];
            for (int j = 0; j < N; ++j) {
                graph[j] = new LinkedList<>();
            }
            visit = new boolean[N];
            dp = new int[N];
            Arrays.fill(dp, -1);

            for (int i = 0; i < M; ++i) {
                String[] dynaInfo = br.readLine().split(" ");
                int st = Integer.parseInt(dynaInfo[0]);
                int en = Integer.parseInt(dynaInfo[1]);
                int cost = Integer.parseInt(dynaInfo[2]);
                st--; en--;

                graph[st].add(new int[]{en,cost});
                graph[en].add(new int[]{st,cost});
            }

            visit[0] = true;
            System.out.println(M == 0 ? 0 : dfs(0, (int)1e9));
        }
    }

    static int dfs(int node, int cost) {
        if (dp[node] != -1) {
            return dp[node];
        }

        int result = 0;

        for (int[] nxt : graph[node]) {
            int nxtNode = nxt[0];
            int nxtCost = nxt[1];

            if (visit[nxtNode]) continue;
            visit[nxtNode] = true;
            result += dfs(nxtNode, nxtCost);
        }

        if (result == 0) {
            result = LEAF;
        }

        return dp[node] = Math.min(result, cost);
    }
}
