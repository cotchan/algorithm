
import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);

            st--; en--;

            graph[st].add(new Integer[]{en, cost});
            graph[en].add(new Integer[]{st, cost});
        }

        System.out.println(dijkstra(0,N-1));
    }

    static int dijkstra(int start, int end) {
        int[] dist = new int[N];

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0];
        });

        Arrays.fill(dist, 100_000_000);

        dist[start] = 0;
        pq.add(new Integer[]{0, start});

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
                    pq.add(new Integer[]{nowCost + nxtCost, nxtNode});
                }
            }
        }

        return dist[end];
    }
}
