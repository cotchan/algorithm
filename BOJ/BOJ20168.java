import java.io.*;
import java.util.*;


public class Main {

    public static int N, M, A, B, C;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmabcInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmabcInfo[0]);
        M = Integer.parseInt(nmabcInfo[1]);
        A = Integer.parseInt(nmabcInfo[2]);
        B = Integer.parseInt(nmabcInfo[3]);
        C = Integer.parseInt(nmabcInfo[4]);

        A--; B--;

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

        int maxCost = getMaxCost(A, B);
        if (maxCost == -1) {
            System.out.println(-1);
            return;
        }

        int answer = -1;
        for (int cost = 0; cost <= maxCost; ++cost) {
            if (dijkstra(A, B, C, cost)) {
                answer = cost;
                System.out.println(answer);
                return;
            }
        }

        System.out.println(-1);
        return;
    }

    public static boolean dijkstra(int st, int en, int totalCost, int upperBound) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[st] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new Integer[]{0, st});

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowCost = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowCost) continue;

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (nxtCost > upperBound) continue;

                if (dist[nxtNode] > nowCost + nxtCost) {
                    dist[nxtNode] = nowCost + nxtCost;
                    pq.add(new Integer[]{nowCost + nxtCost, nxtNode});
                }
            }
        }

        return dist[en] <= totalCost;
    }

    public static int getMaxCost(int st, int en) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int maxv = -1;

        dist[st] = 0;
        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new Integer[]{0, st});

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowCost = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowCost) continue;

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (dist[nxtNode] > nowCost + nxtCost) {
                    if (nxtCost > maxv) {
                        maxv = nxtCost;
                    }

                    dist[nxtNode] = nowCost + nxtCost;
                    pq.add(new Integer[]{nowCost + nxtCost, nxtNode});
                }
            }
        }

        return maxv;
    }
}
