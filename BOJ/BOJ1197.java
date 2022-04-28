import java.io.*;
import java.util.*;


public class Main {

    public static int V, E;
    public static int[] parents;
    public static PriorityQueue<Integer[]> pq;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] veInfo = br.readLine().split(" ");

        V = Integer.parseInt(veInfo[0]);
        E = Integer.parseInt(veInfo[1]);
        parents = new int[V];

        for (int i = 0; i < V; ++i) {
            parents[i] = i;
        }

        pq = new PriorityQueue<>((a,b) -> {
            if (a[0] < b[0]) return -1;
            else if (a[0] > b[0]) return 1;
            else return 0;
        });

        for (int i = 0; i < E; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);

            st--; en--;
            pq.add(new Integer[]{cost, st, en});
        }

        int edgeCnt = 0;
        int totalCost = 0;

        while (edgeCnt != V - 1 && !pq.isEmpty()) {
            Integer[] now = pq.poll();

            int c1 = find(now[1]);
            int c2 = find(now[2]);

            if (c1 != c2) {
                union(c1, c2);
                edgeCnt++;
                totalCost += now[0];
            }
        }

        System.out.println(totalCost);
    }

    public static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    public static void union(int c1, int c2) {
        parents[c2] = c1;
    }
}
