package boj;

import java.io.*;
import java.util.*;


public class Main {

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M;
    public static char[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        board = new char[N][M];

        for (int i = 0; i < N; ++i) {
            String row = br.readLine();
            for (int j = 0; j < M; ++j) {
                char mark = row.charAt(j);

                board[i][j] = mark;
            }
        }

        int maxv = -1;

        for (int i = 0; i < N * M; ++i) {
            int y = i / M;
            int x = i % M;
            if (board[y][x] == 'L') {
                maxv = Math.max(maxv, bfs(y, x));
            }
        }

        System.out.println(maxv);
    }

    public static int bfs(int y, int x) {
        int result = 0;
        boolean[][] visited = new boolean[N][M];

        Queue<Integer[]> queue = new LinkedList<>();

        queue.add(new Integer[]{y, x, 0});
        visited[y][x] = true;

        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            int nowY = now[0];
            int nowX = now[1];
            int cnt = now[2];

            result = Math.max(result, cnt);

            for (int dir = 0; dir < 4; ++dir) {
                int ny = nowY + dy[dir];
                int nx = nowX + dx[dir];

                if (isSafe(ny, nx) && board[ny][nx] == 'L' && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new Integer[]{ny, nx, cnt + 1});
                }
            }
        }

        return result;
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
