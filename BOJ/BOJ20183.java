
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    static int N,M,A,B;
    static long C;
    static List<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmabcInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmabcInfo[0]);
        M = Integer.parseInt(nmabcInfo[1]);
        A = Integer.parseInt(nmabcInfo[2]);
        B = Integer.parseInt(nmabcInfo[3]);
        C = Long.parseLong(nmabcInfo[4]);

        A--; B--;

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new LinkedList<>();
        }

        int maxCost = Integer.MIN_VALUE;

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);
            st--; en--;
            graph[st].add(new int[]{en, cost});
            graph[en].add(new int[]{st, cost});

            maxCost = Math.max(maxCost, cost);
        }

        int st = 1;
        int ans = Integer.MAX_VALUE;
        int en = maxCost;

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
        long[] dist = new long[N];
        Arrays.fill(dist, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> {
            long aValue = a[0];
            long bValue = b[0];
            if (aValue < bValue) return -1;
            else if (aValue == bValue) return 0;
            else return 1;
        });

        pq.add(new long[]{0, A});
        dist[A] = 0;

        while (!pq.isEmpty()) {
            long[] now = pq.poll();
            long nowCost = now[0];
            int nowNode = (int)now[1];

            if (dist[nowNode] < nowCost) continue;

            for (int[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtCost = nxt[1];

                if (nxtCost > maxCost) continue;
                if (dist[nxtNode] > nowCost + (long)nxtCost) {
                    dist[nxtNode] = nowCost + (long)nxtCost;
                    pq.add(new long[]{dist[nxtNode], nxtNode});
                }
            }
        }

        return dist[B] <= C;
    }
}
