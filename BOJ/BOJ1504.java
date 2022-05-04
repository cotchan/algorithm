import java.io.*;
import java.util.*;


public class Main {

    public static final int IMPOSSIBLE = 100_000_000;
    public static int N, E, V1, V2;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] neInfo = br.readLine().split(" ");

        N = Integer.parseInt(neInfo[0]);
        E = Integer.parseInt(neInfo[1]);

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int loop = 0; loop < E; ++loop) {
            String[] edgeInfoString = br.readLine().split(" ");

            int src = Integer.parseInt(edgeInfoString[0]);
            int dst = Integer.parseInt(edgeInfoString[1]);
            int dist = Integer.parseInt(edgeInfoString[2]);

            src--; dst--;
            graph[src].add(new Integer[]{dst, dist});
            graph[dst].add(new Integer[]{src, dist});
        }

        String[] nodeInfo = br.readLine().split(" ");

        V1 = Integer.parseInt(nodeInfo[0]);
        V2 = Integer.parseInt(nodeInfo[1]);

        V1--; V2--;

        //1 => N
        //1 => V1 => V2 => N
        //1 => V2 => V1 => N

        int e1 = djikstra(0, V1) + djikstra(V1, V2) + djikstra(V2, N-1);
        int e2 = djikstra(0, V2) + djikstra(V2, V1) + djikstra(V1, N-1);

        int ans = Math.min(e1, e2);

        if (ans >= IMPOSSIBLE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static int djikstra(int start, int end) {

        int[] dist = new int[N];
        Arrays.fill(dist, IMPOSSIBLE);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] == b[0]) return 0;
            else return 1;
        });

        dist[start] = 0;
        pq.add(new Integer[]{0, start});

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowDist = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowDist) {
                continue;
            }

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtDist = nxt[1];

                if (dist[nxtNode] > nowDist + nxtDist) {
                    dist[nxtNode] = nowDist + nxtDist;
                    pq.add(new Integer[]{dist[nxtNode], nxtNode});
                }
            }
        }

        return dist[end];
    }
}
