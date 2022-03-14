import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static class Pair {
        int y,x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static boolean[][] visited;
    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};
    public int M, N;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        M = m;
        N = n;
        visited = new boolean[M][N];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    int componentCnt = bfs(i, j, picture);
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, componentCnt);
                    numberOfArea++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    public int bfs(int stY, int stX, int[][] picture) {

        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(stY, stX));
        visited[stY][stX] = true;
        int componentSize = 1;
        int componentNumber = picture[stY][stX];

        while (!q.isEmpty()) {
            Pair now = q.poll();

            for (int dir = 0; dir < 4; ++dir) {
                int ny = now.y + dy[dir];
                int nx = now.x + dx[dir];

                if (isSafe(ny,nx) && !visited[ny][nx] && picture[ny][nx] == componentNumber) {
                    visited[ny][nx] = true;
                    q.add(new Pair(ny, nx));
                    componentSize++;
                }
            }
        }

        return componentSize;
    }

    public boolean isSafe(int y, int x) {
        return (0 <= y && y < M && 0 <= x && x < N);
    }
}
