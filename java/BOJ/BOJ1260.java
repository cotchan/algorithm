import java.util.*;
import java.io.*;

public class Main {

    static final int CONNECTED = 1;

    static int N,M,V;
    static List<Integer> dfsResult, bfsResult;
    static boolean[] visited;
    static int[][] graph;

    public static void dfs(int node) {
        for (int nxt = 0; nxt < N; ++nxt) {
            if (node != nxt && graph[node][nxt] == CONNECTED && !visited[nxt]) {
                visited[nxt] = true;
                dfsResult.add(nxt+1);
                dfs(nxt);
            }
        }
    }

    public static void bfs(int node) {

        Queue<Integer> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()) {
            int now = q.poll();

            for (int nxt = 0; nxt < N; ++nxt) {
                if (nxt != now && graph[now][nxt] == CONNECTED && !visited[nxt]) {
                    visited[nxt] = true;
                    bfsResult.add(nxt+1);
                    q.add(nxt);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        V = Integer.parseInt(info[2]);

        dfsResult = new LinkedList<>();
        bfsResult = new LinkedList<>();

        visited = new boolean[N];
        graph = new int[N][N];

        for (int loop = 0; loop < M; ++loop) {
            String[] edge = br.readLine().split(" ");
            int src = Integer.parseInt(edge[0]);
            int dst = Integer.parseInt(edge[1]);

            graph[src-1][dst-1] = 1;
            graph[dst-1][src-1] = 1;
        }

        visited[V-1] = true;
        dfsResult.add(V);
        dfs(V-1);

        Arrays.fill(visited, false);

        visited[V-1] = true;
        bfsResult.add(V);
        bfs(V-1);

        StringBuilder sbDfs, sbBfs;

        sbDfs = new StringBuilder();
        sbBfs = new StringBuilder();

        for (int node : dfsResult) {
            sbDfs.append(node);
            sbDfs.append(' ');
        }

        for (int node : bfsResult) {
            sbBfs.append(node);
            sbBfs.append(' ');
        }

        System.out.println(sbDfs.toString());
        System.out.println(sbBfs.toString());
    }
}
