import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;
    static int N, D;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nd = br.readLine().split(" ");

        N = Integer.parseInt(nd[0]);
        D = Integer.parseInt(nd[1]);

        graph = new List[D+1];
        for (int i = 0; i < D + 1; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            //시작,도착,길이
            String[] road = br.readLine().split(" ");
            int st = Integer.parseInt(road[0]);
            int en = Integer.parseInt(road[1]);
            int dist = Integer.parseInt(road[2]);

            if (st > D || en > D) continue;

            graph[st].add(new int[]{en, dist});
        }

        System.out.println(dijkstra(0));
    }

    static int dijkstra(int node) {
        int[] d = new int[D+1];
        Arrays.fill(d, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.add(new int[]{0, 0});

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowDist = now[0];
            int nowNode = now[1];

            if (d[nowNode] < nowDist) continue;

            for (int[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int dist = nxt[1];
                int nxtDist = nowDist + dist;

                if (d[nxtNode] > nxtDist) {
                    d[nxtNode] = nxtDist;
                    pq.add(new int[]{nxtDist, nxtNode});
                }
            }

            for (int nxt = nowNode + 1; nxt <= D; ++nxt) {
                int dist = nxt - nowNode;
                int nxtDist = nowDist + dist;
                if (d[nxt] > nxtDist) {
                    d[nxt] = nxtDist;
                    pq.add(new int[]{nxtDist, nxt});
                }
            }
        }

        return d[D];
    }
}
