import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y, x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int STATE_SIZE = 4;
    static final int DIR_SIZE = 4;
    static final int IMPOSSIBLE = Integer.MAX_VALUE;
    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, -1, 0, 1};
    static final int EMPTY = 0;
    static final int WALL = 1;
    static int N, M;
    static int[][] board;
    static Pair startPoint;
    static List<Pair> endPoints = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);
        board = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); ++j) {
                char c = row.charAt(j);
                if (c == '#') {
                    board[i][j] = WALL;
                } else if (c == 'C') {
                    board[i][j] = EMPTY;
                    endPoints.add(new Pair(i, j));
                } else if (c == 'S') {
                    board[i][j] = EMPTY;
                    startPoint = new Pair(i, j);
                } else {
                    board[i][j] = EMPTY;
                }
            }
        }

        int ans = bfs(startPoint);

        if (ans == IMPOSSIBLE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static int bfs(Pair start) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start.y, start.x, -1, 0, 0});

        boolean[][][][] visit = new boolean[N][M][STATE_SIZE][DIR_SIZE];

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int nowY = now[0];
            int nowX = now[1];
            int nowDir = now[2];
            int nowDist = now[3];
            int nowState = now[4];

            if (nowState == 3) {
                return nowDist;
            }

            for (int dir = 0; dir < DIR_SIZE; ++dir) {
                if (nowDir == dir) continue;

                int ny = nowY + dy[dir];
                int nx = nowX + dx[dir];

                if (!safe(ny,nx)) continue;
                if (board[ny][nx] == WALL) continue;

                int nxtState = nowState;

                if (ny == endPoints.get(0).y && nx == endPoints.get(0).x) {
                    nxtState |= 1;
                } else if (ny == endPoints.get(1).y && nx == endPoints.get(1).x) {
                    nxtState |= 2;
                }

                if (visit[ny][nx][nxtState][dir]) continue;
                visit[ny][nx][nxtState][dir] = true;

                q.add(new int[]{ny, nx, dir, nowDist + 1, nxtState});
            }
        }

        return IMPOSSIBLE;
    }

    static boolean safe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
