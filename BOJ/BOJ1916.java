package boj;

import java.io.*;
import java.util.*;


public class Main {

    public static int N, M, START, END;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] busInfo = br.readLine().split(" ");

            int src = Integer.parseInt(busInfo[0]);
            int dst = Integer.parseInt(busInfo[1]);
            int cost = Integer.parseInt(busInfo[2]);

            src--; dst--;

            graph[src].add(new Integer[]{dst, cost});
        }

        String[] nodeInfo = br.readLine().split(" ");

        START = Integer.parseInt(nodeInfo[0]);
        END = Integer.parseInt(nodeInfo[1]);

        START--; END--;

        System.out.println(djikstra(START,END));
    }

    private static int djikstra(int start, int end) {
        int[] dist = new int[N];
        Arrays.fill(dist, 200_000_000);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a,b) -> {
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

            if (dist[nowNode] < nowDist) continue;

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
