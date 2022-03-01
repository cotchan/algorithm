import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static final int CONNECTED = 1;
    public static int N, M;
    public static boolean[] visited;
    public static boolean[][] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        graph = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            String[] mapInfo = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int value = Integer.parseInt(mapInfo[j]);

                if (value == CONNECTED) {
                    graph[i][j] = true;
                    graph[j][i] = true;
                }
            }
        }

        boolean isPossible = true;
        String[] courseInfo = br.readLine().split(" ");

        for (int idx = 0; idx < courseInfo.length - 1; ++idx) {
            int nowNode = Integer.parseInt(courseInfo[idx]);
            int targetNode = Integer.parseInt(courseInfo[idx+1]);

            nowNode--; targetNode--;
            Arrays.fill(visited, false);
            visited[nowNode] = true;
            boolean result = dfs(nowNode, targetNode);

            if (!result) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static boolean dfs(int nowNode, int targetNode) {

        if (nowNode == targetNode) {
            return true;
        }

        boolean result = false;

        for (int nxtNode = 0; nxtNode < N; ++nxtNode) {
            if (!visited[nxtNode] && (nxtNode != nowNode) && graph[nowNode][nxtNode]) {
                visited[nxtNode] = true;
                boolean isFindTarget = dfs(nxtNode, targetNode);

                if (isFindTarget) {
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
