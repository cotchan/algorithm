
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] indegree, times;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        indegree = new int[N];
        times = new int[N];

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            String[] jobInfo = br.readLine().split(" ");
            int time = Integer.parseInt(jobInfo[0]);
            int preCnt = Integer.parseInt(jobInfo[1]);

            times[i] = time;

            for (int j = 0; j < preCnt; ++j) {
                int preJobNumber = Integer.parseInt(jobInfo[j+2]);
                preJobNumber--;
                graph[preJobNumber].add(i);
                indegree[i]++;
            }
        }

        int[] dist = new int[N];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; ++i) {
            if (indegree[i] == 0) {
                dist[i] = times[i];
                q.add(i);
            }
        }


        while (!q.isEmpty()) {
            int now = q.poll();

            for (int nxt : graph[now]) {
                if (dist[nxt] < dist[now] + times[nxt]) {
                    dist[nxt] = dist[now] + times[nxt];
                }

                /**
                 * 3. 큐에서 정점을 하나씩 빼면서 인접한 모든 정점을 탐색하고
                 * 그 때 마다 `--indegree[nxt]`을 해주면서 indegree[nxt] == 0`이 되면 해당 정점을 큐에 넣어줍니다.
                 */
                if (--indegree[nxt] == 0) {
                    q.add(nxt);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; ++i) ans = Math.max(ans,dist[i]);

        System.out.println(ans);
    }
}
