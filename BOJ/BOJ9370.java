import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static List<Integer> candidates = new ArrayList<>();
    public static List<Integer[]>[] graph;
    public static int N, M, T, S, G, H;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCase; ++t) {
            String[] nmtInfo = br.readLine().split(" ");

            N = Integer.parseInt(nmtInfo[0]);
            M = Integer.parseInt(nmtInfo[1]);
            T = Integer.parseInt(nmtInfo[2]);

            candidates.clear();

            graph = new List[N];
            for (int i = 0; i < N; ++i) {
                graph[i] = new ArrayList<>();
            }

            String[] sghInfo = br.readLine().split(" ");

            S = Integer.parseInt(sghInfo[0]);
            G = Integer.parseInt(sghInfo[1]);
            H = Integer.parseInt(sghInfo[2]);

            S--; G--; H--;

            for (int i = 0; i < M; ++i) {
                String[] edgeInfo = br.readLine().split(" ");
                int a = Integer.parseInt(edgeInfo[0]);
                int b = Integer.parseInt(edgeInfo[1]);
                int d = Integer.parseInt(edgeInfo[2]);
                a--; b--;

                graph[a].add(new Integer[]{b, d});
                graph[b].add(new Integer[]{a, d});
            }

            for (int i = 0; i < T; ++i) {
                int candidate = Integer.parseInt(br.readLine());
                candidate--;
                candidates.add(candidate);
            }

            List<Integer> answer = new ArrayList<>();

            int s2g = djikstra(S, G);
            int s2h = djikstra(S, H);

            int g2h = djikstra(G, H);
            int h2g = djikstra(H, G);

            for (int candidate : candidates) {
                int s2t = djikstra(S, candidate);
                int h2t = djikstra(H, candidate);
                int g2t = djikstra(G, candidate);

                if (s2t == s2g + g2h + h2t) {
                    answer.add(candidate);
                } else if (s2t == s2h + h2g + g2t) {
                    answer.add(candidate);
                }
            }

            Collections.sort(answer);

            for (int ans : answer) {
                sb.append(ans + 1);
                sb.append(" ");
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int djikstra(int st, int en) {
        int[] dist = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((c1,c2) -> {
            if (c1[0] < c2[0]) {
                return -1;
            } else if (c1[0] == c2[0]) {
                return 0;
            } else {
                return 1;
            }
        });

        pq.add(new Integer[]{0, st});
        dist[st] = 0;

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowCost = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowCost) {
                continue;
            }

            for (Integer[] nxtInfo : graph[nowNode]) {
                int nxtNode = nxtInfo[0];
                int nxtCost = nxtInfo[1];

                if (dist[nxtNode] > nowCost + nxtCost) {
                    dist[nxtNode] = nowCost + nxtCost;
                    pq.add(new Integer[]{dist[nxtNode], nxtNode});
                }
            }
        }

        return dist[en];
    }
}
