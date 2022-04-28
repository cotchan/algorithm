import java.io.*;
import java.util.*;


public class Main {

    public static int V;
    public static List<Integer[]>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        V = Integer.parseInt(br.readLine());
        graph = new List[V];

        for (int i = 0; i < V; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; ++i) {
            String[] nodeInfos = br.readLine().split(" ");
            int nodeNumber = Integer.parseInt(nodeInfos[0]);

            nodeNumber--;

            for (int idx = 1; idx < nodeInfos.length; idx += 2) {
                int adjNode = Integer.parseInt(nodeInfos[idx]);
                if (adjNode == -1) {
                    break;
                }

                adjNode--;

                int length = Integer.parseInt(nodeInfos[idx+1]);
                graph[nodeNumber].add(new Integer[]{adjNode, length});
            }
        }

        int[] result = djikstra(0);
        int[] result2 = djikstra(result[0]);

        System.out.println(result2[1]);
    }

    public static int[] djikstra(int node) {

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);

        int maxNode = 0;
        int maxDist = Integer.MIN_VALUE;

        PriorityQueue<Integer[]> pq = new PriorityQueue<>((a,b) -> {
            if (a[0] < b[0]) {
                return -1;
            } else if (a[0] > b[0]) {
                return 1;
            } else {
                return 0;
            }
        });

        dist[node] = 0;
        pq.add(new Integer[]{0, node});

        while (!pq.isEmpty()) {
            Integer[] now = pq.poll();

            int nowDist = now[0];
            int nowNode = now[1];

            if (dist[nowNode] < nowDist) {
                continue;
            }

            for (Integer[] nxt : graph[nowNode]) {
                int nxtNode = nxt[0];
                int nxtDist = nxt[1];

                if (dist[nxtNode] > nowDist + nxtDist) {
                    dist[nxtNode] = nowDist + nxtDist;
                    pq.add(new Integer[]{dist[nxtNode], nxtNode});
                }
            }
        }

        for (int i = 0; i < V; ++i) {
            if (maxDist < dist[i]) {
                maxDist = dist[i];
                maxNode = i;
            }
        }

        return new int[]{maxNode, maxDist};
    }
}
