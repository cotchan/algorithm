
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] numbers;
    static boolean[] visited, isCycle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        visited = new boolean[N];
        isCycle = new boolean[N];

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());
            num--;
            numbers[i] = num;
        }

        int totalCycleSize = 0;

        for (int i = 0; i < N; ++i) {
            if (isCycle[i]) continue;
            Arrays.fill(visited, false);

            List<Integer> candidates = new ArrayList<>();
            candidates.add(i);

            visited[i] = true;
            int cycleSize = dfs(i, i, candidates);
            totalCycleSize += cycleSize;

            if (cycleSize > 0) {
                for (int candidate : candidates) {
                    isCycle[candidate] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalCycleSize).append("\n");
        for (int i = 0; i < N; ++i) {
            if (isCycle[i]) {
                sb.append(i + 1).append("\n");
            }
        }
        System.out.println(sb);
    }

    static int dfs(int st, int now, List<Integer> candidates) {
        int nxt = numbers[now];
        if (visited[nxt]) {
            boolean hasCycle = (st == nxt);

            if (!hasCycle) return 0;
            return candidates.size();
        } else {
            visited[nxt] = true;
            candidates.add(nxt);
            return dfs(st, nxt, candidates);
        }
    }
}
