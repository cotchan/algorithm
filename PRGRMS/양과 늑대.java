import java.util.*;

class Solution {

    public static int N, ans;
    public static List<Integer>[] graph;
    // 모든 방문상태(2^17)를 저장할 배열
    public static boolean[] visited = new boolean[1<<17];

    public int solution(int[] info, int[][] edges) {

        ans = 0;
        N = info.length;
        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edgeInfo : edges) {
            int parent = edgeInfo[0];
            int child = edgeInfo[1];
            graph[parent].add(child);
        }

        // 0번 노드만 포함된 상태에서 dfs 시작
        solve(info, 1);
        return ans;
    }

    public void solve(int[] info, int state) {

        if (visited[state]) {
            return;
        }

        visited[state] = true;

        int animalCnt = 0, wolf = 0;

        // 현재 상태에 대한 전체 동물 갯수, 늑대 갯수 counting
        for (int i = 0; i < N; ++i) {
            if ((state & (1 << i)) != 0) {
                animalCnt++;
                wolf += info[i];
            }
        }

        if ((2 * wolf) >= animalCnt) {
            return;
        }

        // 양 갯수 갱신
        int sheepCnt = animalCnt - wolf;
        ans = Math.max(ans, sheepCnt);

        // 이제 다음 상태로 넘어갈 시간
        for (int i = 0; i < N; ++i) {
            // 현재 비트가 꺼져있는 경우 사용하지 않는 노드(상태)이므로 continue
            if ((state & (1 << i)) == 0) {
                continue;
            }

            // 현재 사용하는 비트라면, 다음 상태로 이동가능한지 확인
            for (int nxt : graph[i]) {
                solve(info, (state | (1 << nxt)));
            }
        }
    }
}
