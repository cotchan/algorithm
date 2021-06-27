import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first, second;
        Pair (int f, int s) {
            first = f;
            second = s;
        }
    }

    static class Tuple implements Comparable<Tuple> {
        long cost;
        int first, second;

        Tuple (long l, int f, int s) {
            cost = l;
            first = f;
            second = s;
        }

        @Override
        public int compareTo(Tuple o) {
            if (cost < o.cost) return -1;
            else if (cost == o.cost) return 0;
            else return 1;
        }
    }

    static int N,M,K;
    static List<Pair>[] graph;

    public static long[][] djikstra(int src, int dst) {
        long[][] dist = new long[N][K+1];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }

        for (int i = 0; i <= K; ++i) {
            dist[0][i] = 0;
        }

        PriorityQueue<Tuple> pq = new PriorityQueue<>();

        pq.add(new Tuple(0, src, 0));

        while (!pq.isEmpty()) {
            Tuple now = pq.poll();

            long nowCost = now.cost;
            int nowNode = now.first;
            int nowCnt = now.second;

            if (dist[nowNode][nowCnt] < nowCost) {
                continue;
            }

            for (Pair nxt : graph[nowNode]) {
                int nxtNode = nxt.first;
                long nxtCost = nowCost + nxt.second;

                if (nxtCost < dist[nxtNode][nowCnt]) {
                    dist[nxtNode][nowCnt] = nxtCost;
                    pq.add(new Tuple(nxtCost, nxtNode, nowCnt));
                }

                if (nowCnt < K && nowCost < dist[nxtNode][nowCnt+1]) {
                    dist[nxtNode][nowCnt + 1] = nowCost;
                    pq.add(new Tuple(nowCost, nxtNode, nowCnt + 1));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        K = Integer.parseInt(info[2]);

        graph = new LinkedList[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new LinkedList<>();
        }

        for (int loop = 0; loop < M; ++loop) {
            String[] edges = br.readLine().split(" ");
            int src,dst, time;

            src = Integer.parseInt(edges[0]);
            dst = Integer.parseInt(edges[1]);
            time = Integer.parseInt(edges[2]);
            src--; dst--;

            graph[src].add(new Pair(dst, time));
            graph[dst].add(new Pair(src, time));
        }

        long[][] result = djikstra(0, N - 1);

        long ans = Long.MAX_VALUE;

        for (int i = 0; i <= K; ++i) {
            ans = Math.min(ans, result[N - 1][i]);
        }

        System.out.println(ans);
    }
}
