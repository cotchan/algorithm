import java.io.*;
import java.util.*;


public class Main {

    public static List<Integer>[] graph;
    public static int[] visit;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int loop = 0; loop < k; ++loop) {

            String[] veInfo = br.readLine().split(" ");
            int v = Integer.parseInt(veInfo[0]);
            int e = Integer.parseInt(veInfo[1]);

            visit = new int[v];
            graph = new List[v];
            for (int i = 0; i < v; ++i) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 0; i < e; ++i) {
                String[] edgeInfo = br.readLine().split(" ");

                int st = Integer.parseInt(edgeInfo[0]);
                int en = Integer.parseInt(edgeInfo[1]);
                st--; en--;
                graph[st].add(en);
                graph[en].add(st);
            }

            boolean result = true;

            for (int i = 0; i < v; ++i) {
                if (visit[i] == 0) {
                    visit[i] = 1;
                    if (dfs(i, 1) != 0) {
                        result = false;
                    }
                }
            }

            if (result) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }

        System.out.println(sb);
    }

    public static int dfs(int node, int color) {

        int result = 0;

        for (Integer nxt : graph[node]) {
            if (visit[nxt] == 0) {
                visit[nxt] = -color;
                result += dfs(nxt, -color);
            } else {
                if (visit[nxt] == color) {
                    result += 1;
                }
            }
        }

        return result;
    }
}
