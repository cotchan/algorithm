import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        long cost;
        int node;

        public Pair(long cost, int node) {
            this.cost = cost;
            this.node = node;
        }
    }

    public static int N, M, K;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmkInfo[0]);
        M = Integer.parseInt(nmkInfo[1]);
        K = Integer.parseInt(nmkInfo[2]);

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int u, v, c;
            u = Integer.parseInt(edgeInfo[0]);
            v = Integer.parseInt(edgeInfo[1]);
            c = Integer.parseInt(edgeInfo[2]);

            u--; v--;

            graph[v].add(new Integer[]{u, c});
        }

        String[] interviewsInfo = br.readLine().split(" ");

        Integer[] arr = Arrays.stream(interviewsInfo).map(Integer::new).toArray(Integer[]::new);

        djikstra(arr);
    }

    public static void djikstra(Integer[] interviews) {

        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<Pair> pq = new PriorityQueue<>((a,b) -> {
            if (a.cost < b.cost) return -1;
            else if (a.cost == b.cost) return 0;
            else return 1;
        });

        for (int start : interviews) {
            pq.add(new Pair(0, start-1));
            dist[start-1] = 0;
        }

        while (!pq.isEmpty()) {
            Pair now = pq.poll();

            if (dist[now.node] < now.cost) {
                continue;
            }

            for (Integer[] nxt : graph[now.node]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (dist[nxtNode] > now.cost + (long) nxtCost) {
                    dist[nxtNode] = now.cost + (long) nxtCost;
                    pq.add(new Pair(dist[nxtNode], nxtNode));
                }
            }
        }

        int maxvIdx = -1;
        long maxv = -1;

        for (int i = N - 1; i >= 0; --i) {
            if (dist[i] >= maxv) {
                maxv = dist[i];
                maxvIdx = i;
            }
        }

        System.out.println(maxvIdx+1);
        System.out.println(maxv);
    }
}
