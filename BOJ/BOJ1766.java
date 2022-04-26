import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int N, M;
    public static boolean[] visited;
    public static int[] inDegrees;
    public static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        graph = new List[N];
        inDegrees = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] orderInfo = br.readLine().split(" ");

            int parent = Integer.parseInt(orderInfo[0]);
            int child = Integer.parseInt(orderInfo[1]);

            parent--; child--;
            inDegrees[child]++;
            graph[parent].add(child);
        }

        for (int i = 0; i < N; ++i) {
            if (inDegrees[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int now = pq.poll();
            visited[now] = true;

            sb.append((now + 1) + " ");

            for (int nxt : graph[now]) {
                inDegrees[nxt]--;

                if (!visited[nxt] && inDegrees[nxt] == 0) {
                    pq.add(nxt);
                }
            }
        }

        System.out.println(sb);
    }
}
