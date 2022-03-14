import java.util.*;

class Solution {

    public static List<Integer>[] parents, children;

    public int solution(int n, int[][] results) {
        int answer = 0;

        parents = new List[n];
        children = new List[n];

        for (int i = 0; i < n; ++i) {
            parents[i] = new ArrayList<>();
            children[i] = new ArrayList<>();
        }

        for (int[] result : results) {
            int parent = result[0];
            int child = result[1];

            parent--; child--;

            parents[child].add(parent);
            children[parent].add(child);
        }

        for (int i = 0; i < n; ++i) {
            int cnt = bfs(n, i);

            if (cnt == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    public int bfs(int n, int node) {

        int result = 0;
        
        Queue<Integer> que = new LinkedList<>();
        boolean[] visited = new boolean[n];
        visited[node] = true;

        que.add(node);

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int nxt : parents[now]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    que.add(nxt);
                    result++;
                }
            }
        }

        que.add(node);

        while (!que.isEmpty()) {
            int now = que.poll();

            for (int nxt : children[now]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    que.add(nxt);
                    result++;
                }
            }
        }

        return result;
    }
}
