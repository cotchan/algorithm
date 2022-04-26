import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int N, K;
    public static int[] buildTimes, totalBuildTimes, inDegrees;
    public static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < testCase; ++t) {
            String[] nkInfo = br.readLine().split(" ");

            N = Integer.parseInt(nkInfo[0]);
            K = Integer.parseInt(nkInfo[1]);

            graph = new List[N];
            buildTimes = new int[N];
            totalBuildTimes = new int[N];
            inDegrees = new int[N];

            for (int i = 0; i < N; ++i) {
                graph[i] = new ArrayList<>();
            }

            String[] buildTimeString = br.readLine().split(" ");

            for (int i = 0; i < N; ++i) {
                buildTimes[i] = Integer.parseInt(buildTimeString[i]);
            }

            for (int i = 0; i < K; ++i) {
                String[] edgeInfo = br.readLine().split(" ");
                int src = Integer.parseInt(edgeInfo[0]);
                int dst = Integer.parseInt(edgeInfo[1]);

                src--; dst--;

                graph[src].add(dst);
                inDegrees[dst]++;
            }

            int target = Integer.parseInt(br.readLine());
            target--;

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < N; ++i) {
                if (inDegrees[i] == 0) {
                    queue.add(i);
                    totalBuildTimes[i] = buildTimes[i];
                }
            }

            while (!queue.isEmpty()) {
                int now = queue.poll();

                for (int nxt : graph[now]) {
                    int nowBuildTime = totalBuildTimes[now];
                    int myBuildTime = buildTimes[nxt];

                    totalBuildTimes[nxt] = Math.max(totalBuildTimes[nxt], nowBuildTime + myBuildTime);
                    inDegrees[nxt]--;

                    if (inDegrees[nxt] == 0) {
                        queue.add(nxt);
                    }
                }
            }

            sb.append(totalBuildTimes[target]);
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
