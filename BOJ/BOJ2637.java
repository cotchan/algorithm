import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {

    public static int N, M;
    public static int[] parent = new int[101];
    public static int[][] dp = new int[101][101];
    public static List<Integer[]>[] graph = new List[101];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for (int i = 0; i <= N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] xykInfo = br.readLine().split(" ");

            //완제품 x
            int x = Integer.parseInt(xykInfo[0]);
            //기본품 y
            int y = Integer.parseInt(xykInfo[1]);
            int k = Integer.parseInt(xykInfo[2]);

            //y 입장에서 x 만드는데 자신이 k개 필요하다는 의미
            graph[y].add(new Integer[]{x, k});

            parent[x]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 1; i < N; ++i) {
            if (parent[i] == 0) {
                q.add(i);
                dp[i][i] = 1;
            }
        }

        while (!q.isEmpty()) {
            int nowPart = q.poll();

            for (Integer[] nxt : graph[nowPart]) {
                int nxtPart = nxt[0];
                int nxtPartNeedCount = nxt[1];

                for (int i = 1; i < N; ++i) {
                    dp[nxtPart][i] += nxtPartNeedCount * dp[nowPart][i];
                }

                parent[nxtPart]--;

                if (parent[nxtPart] == 0) {
                    q.add(nxtPart);
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            if (dp[N][i] != 0) {
                sb.append(i);
                sb.append(" ");
                sb.append(dp[N][i]);
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
