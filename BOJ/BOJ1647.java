import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    static class Tuple implements Comparable<Tuple> {
        long cost;
        int src,dst;

        public Tuple(long cost, int src, int dst) {
            this.cost = cost;
            this.src = src;
            this.dst = dst;
        }

        @Override
        public int compareTo(Tuple o) {
            if (cost < o.cost) return -1;
            else if (cost == o.cost) return 0;
            else return 1;
        }
    }

    public static int N, M;
    public static int[] parents;
    public static PriorityQueue<Tuple> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        parents = new int[N];

        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int src = Integer.parseInt(edgeInfo[0]);
            int dst = Integer.parseInt(edgeInfo[1]);
            long cost = Long.parseLong(edgeInfo[2]);

            src--; dst--;

            pq.add(new Tuple(cost, dst, src));
        }

        int edgeCnt = 0;
        long maxv, total;
        total = maxv = 0;

        while (!pq.isEmpty() && (edgeCnt != N-1)) {
            Tuple now = pq.poll();

            int c1 = find(now.src);
            int c2 = find(now.dst);

            if (c1 != c2) {
                union(c1,c2);
                edgeCnt++;
                total += now.cost;
                maxv = Math.max(maxv, now.cost);
            }
        }

        System.out.println(total - maxv);
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
