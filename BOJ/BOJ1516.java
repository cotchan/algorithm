import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] buildTimes;
    static int[] indegree;
    static List<Integer>[] graph;
    static List<Integer>[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        buildTimes = new int[N];
        graph = new List[N];
        parents = new List[N];
        indegree = new int[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
            parents[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            String[] buildInfo = br.readLine().split(" ");
            int buildTime = Integer.parseInt(buildInfo[0]);
            buildTimes[i] = buildTime;

            for (int j = 1; j < buildInfo.length - 1; ++j) {
                int parent = Integer.parseInt(buildInfo[j]);
                parent--;
                indegree[i]++;
                graph[parent].add(i);
                parents[i].add(parent);
            }
        }

        topological();
    }

    static void topological() {
        int[] totalBuildTimes = new int[N];
        Arrays.fill(totalBuildTimes, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            if (indegree[i] == 0) {
                q.add(i);
                totalBuildTimes[i] = buildTimes[i];
            }
        }

        while (!q.isEmpty()) {
            int top = q.poll();

            for (int nxt : graph[top]) {
                indegree[nxt]--;

                if (indegree[nxt] == 0) {
                    int maxv = -1;
                    for (int parent : parents[nxt]) {
                        maxv = Math.max(maxv, totalBuildTimes[parent]);
                    }

                    totalBuildTimes[nxt] = maxv + buildTimes[nxt];
                    q.add(nxt);
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            System.out.println(totalBuildTimes[i]);
        }
    }
}
