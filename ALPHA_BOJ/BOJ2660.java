import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        graph = new List[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList();
        }

        while (true) {
            String[] info = br.readLine().split(" ");
            int n1 = Integer.parseInt(info[0]);
            int n2 = Integer.parseInt(info[1]);

            if (n1 == -1 && n2 == -1) break;

            n1--; n2--;
            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        int minv = Integer.MAX_VALUE;
        int[] results = new int[N];

        for (int i = 0; i < N; ++i) {
            int result = bfs(i);
            result = result == 0 ? Integer.MAX_VALUE : result;
            minv = Math.min(minv, result);
            results[i] = result;
        }

        int cnt = 0;

        for (int i = 0; i < N; ++i) {
            if (results[i] == minv) cnt++;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(minv);
        sb.append(" ");
        sb.append(cnt);
        sb.append("\n");

        for (int i = 0; i < N; ++i) {
            if (results[i] == minv) {
                sb.append(i+1);
                sb.append(" ");
            }
        }

        sb.append("\n");
        System.out.println(sb);
    }

    private static int bfs(int nodeNumber) {
        int ans = 0;
        boolean[] visit = new boolean[N];
        Queue<int[]> q = new LinkedList<>();

        visit[nodeNumber] = true;
        q.add(new int[]{nodeNumber, 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowNodeNumber = now[0];
            int nowDepth = now[1];

            for (int nxt : graph[nowNodeNumber]) {
                if (visit[nxt]) continue;
                visit[nxt] = true;
                ans = Math.max(ans, nowDepth + 1);
                q.add(new int[]{nxt, nowDepth + 1});
            }
        }

        return ans;
    }
}
