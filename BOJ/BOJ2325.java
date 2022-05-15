import java.io.*;
import java.util.*;


public class Main {

    public static int N, M;
    public static List<Integer[]>[] graph;

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
            int dist = Integer.parseInt(edgeInfo[2]);

            st--; en--;
            graph[st].add(new Integer[]{en, dist});
            graph[en].add(new Integer[]{st, dist});
        }

        System.out.println(djikstra(0));
    }

    public static int djikstra(int node) {

        int[] dist = new int[N];
        int[] parents = new int[N];

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new Integer[]{0, node});
        dist[node] = 0;

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
                    parents[nxtNode] = nowNode;
                    pq.add(new Integer[]{nowDist + nxtDist, nxtNode});
                }
            }
        }

        int currentNode = N - 1;
        List<Integer[]> candidates = new ArrayList<>();

        while (currentNode != parents[currentNode]) {
            int parent = parents[currentNode];
            candidates.add(new Integer[]{parent, currentNode});
            currentNode = parent;
        }

        int answer = -1;

        for (Integer[] candidate : candidates) {

            boolean[][] disabled = new boolean[N][N];

            int st = candidate[0];
            int en = candidate[1];

            disabled[st][en] = true;
            disabled[en][st] = true;

            answer = Math.max(answer,djikstraWithDisabled(disabled, node));
        }

        return answer;
    }

    public static int djikstraWithDisabled(boolean[][] disabled, int node) {
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });

        pq.add(new Integer[]{0, node});
        dist[node] = 0;

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowDist = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowDist) continue;

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtDist = nxt[1];
                if (disabled[nowNode][nxtNode] || disabled[nxtNode][nowNode]) continue;

                if (dist[nxtNode] > nowDist + nxtDist) {
                    dist[nxtNode] = nowDist + nxtDist;
                    pq.add(new Integer[]{nowDist + nxtDist, nxtNode});
                }
            }
        }

        return dist[N - 1];
    }
}
