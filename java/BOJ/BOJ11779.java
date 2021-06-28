import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int first, second;
        Pair(int f, int s) {
            first = f;
            second = s;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.first < o.first) return -1;
            else if (this.first == o.first) return 0;
            else return 1;
        }
    }

    public static int N,M, minCost;
    public static List<Pair>[] graph;
    public static List<Integer> ansList = new LinkedList();

    public static List djikstra(int st, int en) {

        int[] dist = new int[N];
        int[] parent = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, st));

        while (!pq.isEmpty()) {

            Pair now = pq.poll();

            if (dist[now.second] < now.first) {
                continue;
            }

            for (Pair nxt : graph[now.second]) {
                int nxtNode = nxt.first;
                int nxtCost = now.first + nxt.second;

                if (nxtCost < dist[nxtNode]) {
                    dist[nxtNode] = nxtCost;
                    pq.add(new Pair(nxtCost, nxtNode));
                    parent[nxtNode] = now.second;
                }
            }
        }

        minCost = dist[en];

        LinkedList<Integer> result = new LinkedList<>();

        int targetNode = en;

        while (targetNode != st) {
            result.addFirst(targetNode);
            targetNode = parent[targetNode];
        }

        result.addFirst(st);
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new LinkedList[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edges = br.readLine().split(" ");
            int src = Integer.parseInt(edges[0]);
            int dst = Integer.parseInt(edges[1]);
            int cost = Integer.parseInt(edges[2]);

            src--; dst--;
            graph[src].add(new Pair(dst, cost));
        }

        String[] info = br.readLine().split(" ");
        int st = Integer.parseInt(info[0]);
        int en = Integer.parseInt(info[1]);
        st--; en--;

        List<Integer> result = djikstra(st, en);
        System.out.println(minCost);
        System.out.println(result.size());

        StringBuilder sb = new StringBuilder();

        for (int node : result) {
            sb.append(node + 1);
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}
