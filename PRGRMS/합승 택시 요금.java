import java.util.*;

class Solution {

    public static final int IMPOSSIBLE = 100_000_000;
    public static int N;
    public static int[][] graph;

    public int solution(int n, int s, int a, int b, int[][] fares) {

        N = n;
        graph = new int[N][N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(graph[i], IMPOSSIBLE);
        }

        for (int[] fare : fares) {
            int st = fare[0];
            int en = fare[1];
            int cost = fare[2];
            st--; en--;
            graph[st][en] = cost;
            graph[en][st] = cost;
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (graph[i][k] + graph[k][j] < graph[i][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }

        int answer = IMPOSSIBLE;

        s--; a--; b--;

        answer = Math.min(answer, graph[s][a] + graph[a][b]);
        answer = Math.min(answer, graph[s][b] + graph[b][a]);
        answer = Math.min(answer, graph[s][a] + graph[s][b]);

        for (int i = 0; i < N; ++i) {
            if (i == s || i == a || i == b) continue;

            answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }

        return answer;
    }
}
