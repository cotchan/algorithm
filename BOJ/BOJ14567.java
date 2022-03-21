import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[] parentCnt, ans;
    public static boolean[] visited;
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        graph = new List[N];
        parentCnt = new int[N];
        ans = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] classInfo = br.readLine().split(" ");

            int pre = Integer.parseInt(classInfo[0]) - 1;
            int nxt = Integer.parseInt(classInfo[1]) - 1;

            parentCnt[nxt]++;
            graph[pre].add(nxt);
        }

        Queue<Integer> que = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            if (parentCnt[i] == 0) {
                que.add(i);
                visited[i] = true;
            }
        }

        int semester = 1;

        while (!que.isEmpty()) {

            int loopSize = que.size();

            for (int i = 0; i < loopSize; ++i) {

                int now = que.poll();
                ans[now] = semester;
                for (int nxt : graph[now]) {
                    parentCnt[nxt]--;
                }
            }

            for (int i = 0; i < N; ++i) {
                if (!visited[i] && parentCnt[i] == 0) {
                    que.add(i);
                    visited[i] = true;
                }
            }

            semester++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            sb.append(ans[i]);
            sb.append(" ");
        }

        System.out.println(sb.toString());
    }
}
