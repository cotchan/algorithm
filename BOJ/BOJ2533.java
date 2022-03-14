import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static List<Integer>[] graph;
    public static int[][] dp;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new List[N];
        dp = new int[2][N];
        visited = new boolean[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; ++i) {
            String[] edgeInfo = br.readLine().split(" ");

            int a = Integer.parseInt(edgeInfo[0]);
            int b = Integer.parseInt(edgeInfo[1]);

            a--; b--;

            graph[a].add(b);
            graph[b].add(a);
        }

        visited[0] = true;
        dfs(0);

        System.out.println(Math.min(dp[0][0], dp[1][0]));
    }

    public static void dfs(int node) {

        //얼리 어답터가 아닌경우
        dp[0][node] = 0;
        //얼리 어답터인 경우
        dp[1][node] = 1;

        for (int nxt : graph[node]) {
            if (!visited[nxt]) {
                visited[nxt] = true;

                dfs(nxt);

                dp[0][node] += dp[1][nxt];
                dp[1][node] += Math.min(dp[0][nxt], dp[1][nxt]);
            }
        }
    }
}
