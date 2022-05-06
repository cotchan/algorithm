import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {

    public static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCase; ++t) {
            N = Integer.parseInt(br.readLine());

            int[][] dist = new int[N + 2][N + 2];
            List<Integer>[] positions = new List[N + 2];

            for (int i = 0; i < N + 2; ++i) {
                positions[i] = new ArrayList<>();
            }

            for (int i = 0; i < N+2; ++i) {
                String[] posInfo = br.readLine().split(" ");

                int y = Integer.parseInt(posInfo[0]);
                int x = Integer.parseInt(posInfo[1]);

                positions[i] = Stream.of(new Integer[]{y,x}).collect(Collectors.toList());
            }

            //dist
            for (int i = 0; i < N + 2; ++i) {
                for (int j = 0; j < N + 2; ++j) {
                    if (i == j) continue;

                    int y1 = positions[i].get(0);
                    int x1 = positions[i].get(1);
                    int y2 = positions[j].get(0);
                    int x2 = positions[j].get(1);

                    int distance = Math.abs(y1 - y2) + Math.abs(x1 - x2);

                    dist[i][j] = distance;
                    dist[j][i] = distance;
                }
            }

            if (bfs(dist)) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }

        System.out.println(sb);
    }

    public static boolean bfs(int[][] dist) {
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[N + 2];

        que.add(0);
        visited[0] = true;

        while (!que.isEmpty()) {
            int now = que.poll();

            if (now == N + 1) {
                return true;
            }

            for (int nxt = 0; nxt < N + 2; ++nxt) {
                if (!visited[nxt] && dist[now][nxt] <= 1000) {
                    visited[nxt] = true;
                    que.add(nxt);
                }
            }
        }

        return false;
    }
}
