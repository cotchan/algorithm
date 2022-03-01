import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M, K;
    public static boolean[] visited;
    public static Map<Integer, Integer> friendShipFeeMap = new HashMap<>();
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        K = Integer.parseInt(info[2]);

        visited = new boolean[N];
        graph = new LinkedList[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new LinkedList<>();
        }

        String[] friendShipFees = br.readLine().split(" ");

        for (int idx = 0; idx < friendShipFees.length; ++idx) {
            int fee = Integer.parseInt(friendShipFees[idx]);
            friendShipFeeMap.put(idx, fee);
        }

        for (int loop = 0; loop < M; ++loop) {
            String[] friend = br.readLine().split(" ");

            int v = Integer.parseInt(friend[0]);
            int w = Integer.parseInt(friend[1]);

            v--; w--;

            graph[v].add(w);
            graph[w].add(v);
        }

        int totalFee = 0;

        for (int idx = 0; idx < N; ++idx) {
            if (!visited[idx]) {
                visited[idx] = true;
                int cost = bfs(idx);
                totalFee += cost;
            }
        }

        if (totalFee <= K) {
            System.out.println(totalFee);
        } else {
            System.out.println("Oh no");
        }
    }

    public static int bfs(int idx) {

        Queue<Integer> queue = new LinkedList<>();

        queue.add(idx);
        int minFee = friendShipFeeMap.get(idx);

        while (!queue.isEmpty()) {
            Integer now = queue.poll();

            for (Integer nxt : graph[now]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    queue.add(nxt);
                    minFee = Math.min(minFee, friendShipFeeMap.get(nxt));
                }
            }
        }

        return minFee;
    }
}
