import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    public static int N, D, C;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase;
        testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCase; ++t) {
            String[] ndcInfo = br.readLine().split(" ");
            N = Integer.parseInt(ndcInfo[0]);
            D = Integer.parseInt(ndcInfo[1]);
            C = Integer.parseInt(ndcInfo[2]);
            C--;

            graph = new List[N];
            for (int i = 0; i < N; ++i) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < D; ++i) {
                String[] absInfo = br.readLine().split(" ");

                int a = Integer.parseInt(absInfo[0]);
                int b = Integer.parseInt(absInfo[1]);
                int s = Integer.parseInt(absInfo[2]);

                a--; b--;

                graph[b].add(new Integer[]{a, s});
            }

            int[] result = djikstra(C);
            sb.append(result[0]);
            sb.append(" ");
            sb.append(result[1]);
            sb.append("\n");

            for (int i = 0; i < N; ++i) {
                graph[i].clear();
            }
        }

        System.out.println(sb.toString());

    }

    public static int[] djikstra(int node) {
        int[] dist = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[node] = 0;

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((n1,n2) -> {
            if (n1[0] < n2[0]) {
                return -1;
            } else if (n1[0] == n2[0]) {
                if (n1[1] < n2[1]) {
                    return -1;
                } else if (n1[1] == n2[1]) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        });

        pq.add(new Integer[]{0, node});

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowTime = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowTime) {
                continue;
            }

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtTime = nxt[1];

                if (dist[nxtNode] > nowTime + nxtTime) {
                    dist[nxtNode] = nowTime + nxtTime;
                    pq.add(new Integer[]{dist[nxtNode], nxtNode});
                }
            }
        }

        int visitedCnt = 0;
        int lastComputerTime = 0;

        for (int i = 0; i < dist.length; ++i) {
            if (dist[i] != Integer.MAX_VALUE) {
                visitedCnt++;
                lastComputerTime = Math.max(lastComputerTime, dist[i]);
            }
        }

        return new int[]{visitedCnt, lastComputerTime};
    }
}
