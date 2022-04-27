import java.io.*;
import java.util.*;


public class Main {

    public static int N, M;
    public static boolean[] isVisited;
    public static int[] inDegrees;
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        isVisited = new boolean[N];
        inDegrees = new int[N];
        graph = new List[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] childInfo = br.readLine().split(" ");

            int first = Integer.parseInt(childInfo[0]);
            int second = Integer.parseInt(childInfo[1]);

            first--; second--;

            inDegrees[second]++;
            graph[first].add(second);
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            if (inDegrees[i] == 0) {
                q.add(i);
                isVisited[i] = true;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            int now = q.poll();

            sb.append((now + 1) + " ");

            for (int nxt : graph[now]) {
                inDegrees[nxt]--;

                if (!isVisited[nxt] && inDegrees[nxt] == 0) {
                    isVisited[nxt] = true;
                    q.add(nxt);
                }
            }
        }

        System.out.println(sb);
    }
}
