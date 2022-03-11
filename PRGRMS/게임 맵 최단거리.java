import java.util.*;

class Solution {

    static class Pair {
        int y,x,cnt;

        public Pair(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static final int WALL = 0;
    public static final int EMPTY = 1;
    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N,M;
    public static boolean[][] visited;

    public int solution(int[][] maps) {
        int answer = -1;

        N = maps.length;
        M = maps[0].length;
        visited = new boolean[N][M];

        Queue<Pair> queue = new LinkedList<>();

        visited[0][0] = true;
        queue.add(new Pair(0, 0,1));

        while (!queue.isEmpty()) {
            Pair now = queue.poll();

            if (now.y == N - 1 && now.x == M - 1) {
                return now.cnt;
            }

            for (int dir = 0; dir < 4; ++dir) {
                int ny = now.y +dy[dir];
                int nx = now.x + dx[dir];

                if (isSafe(ny, nx) && !visited[ny][nx] && (maps[ny][nx] == EMPTY)) {
                    visited[ny][nx] = true;
                    queue.add(new Pair(ny, nx, now.cnt + 1));
                }
            }
        }

        return answer;
    }

    public boolean isSafe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
