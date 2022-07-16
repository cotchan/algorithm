
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int N,M,A,B,C;
    static List<int[]>[] graph;

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
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);
            st--; en--;
            graph[st].add(new int[]{en, cost});
            graph[en].add(new int[]{st, cost});
        }

        int st = 1;
        int ans = Integer.MAX_VALUE;
        int en = 20;

        while (st <= en) {
            int mid = (st + en) / 2;

            boolean isPossible = dijkstra(mid);

            if (isPossible) {
                ans = Math.min(ans, mid);
                en = mid-1;
            } else {
                st = mid+1;
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static boolean dijkstra(int maxCost) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[]{0, A});
        dist[A] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowCost = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowCost) continue;

            for (int[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (nxtCost > maxCost) continue;
                if (dist[nxtNode] > nowCost + nxtCost) {
                    dist[nxtNode] = nowCost + nxtCost;
                    pq.add(new int[]{dist[nxtNode], nxtNode});
                }
            }
        }

        return dist[B] <= C;
    }
}
