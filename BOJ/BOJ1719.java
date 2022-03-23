import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int N, M;
    public static int[][] result;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        result = new int[N][N];

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int a = Integer.parseInt(edgeInfo[0]);
            int b = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);

            a--; b--;
            graph[a].add(new Integer[]{b, cost});
            graph[b].add(new Integer[]{a, cost});
        }

        for (int i = 0; i < N; ++i) {
            djikstra(i);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (result[i][j] == 0) {
                    sb.append("-");
                } else {
                    sb.append(result[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void djikstra(int node) {

        int[] parents = new int[N];
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((c1, c2) -> {
            if (c1[0] < c2[0]) {
                return -1;
            } else if (c1[0] == c2[0]) {
                return 0;
            } else {
                return 1;
            }
        });

        pq.add(new Integer[]{0, node});
        dist[node] = 0;

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowCost = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowCost) {
                continue;
            }

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (dist[nxtNode] > nowCost + nxtCost) {
                    dist[nxtNode] = nowCost + nxtCost;
                    parents[nxtNode] = nowNode;
                    pq.add(new Integer[]{dist[nxtNode], nxtNode});
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            if (i == node) {
                continue;
            }

            int child = i;
            int parent = parents[i];

            while (parent != node) {
                child = parent;
                parent = parents[parent];
            }

            result[node][i] = child + 1;
        }
    }
}
