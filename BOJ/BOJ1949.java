import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static List<Integer>[] graph;
    public static int[] person;
    public static boolean[] visited;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        person = new int[N];
        visited = new boolean[N];
        dp = new int[2][N];

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        String[] townInfo = br.readLine().split(" ");

        for (int i = 0; i < townInfo.length; ++i) {
            person[i] = Integer.parseInt(townInfo[i]);
        }

        for (int i = 0; i < N - 1; ++i) {
            String[] edgeInfo = br.readLine().split(" ");

            int src = Integer.parseInt(edgeInfo[0]);
            int dst = Integer.parseInt(edgeInfo[1]);

            src--; dst--;

            graph[src].add(dst);
            graph[dst].add(src);
        }

        visited[0] = true;
        dfs(0);

        System.out.println(Math.max(dp[0][0], dp[1][0]));
    }

    public static void dfs(int node) {

        //우수마을이 아닌 경우
        dp[0][node] = 0;
        //우수마을인 경우
        dp[1][node] = person[node];

        for (int nxt : graph[node]) {
            if (!visited[nxt]) {
                visited[node] = true;
                dfs(nxt);

                dp[0][node] += Math.max(dp[0][nxt], dp[1][nxt]);
                dp[1][node] += dp[0][nxt];
            }
        }
    }

}
