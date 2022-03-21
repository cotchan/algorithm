import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static int N;
    public static int[][]  board;
    public static int[] dy = {-1, 1, 0, 0};
    public static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String rowStr = br.readLine();

            for (int j = 0; j < rowStr.length(); ++j) {
                char c = rowStr.charAt(j);
                int val = c - '0';

                board[i][j] = val;
            }
        }

        int ans = Integer.MAX_VALUE;
        int minv = 0;
        int maxv = N*N + 1;

        while (minv <= maxv) {
            int mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                ans = Math.min(ans, mid);
                maxv = mid - 1;
            } else {
                minv = mid + 1;
            }
        }

        System.out.println(ans);
    }

    public static boolean isPossible(int changeCnt) {

        Queue<Integer[]> queue = new LinkedList<>();
        boolean[][][] visited;
        visited = new boolean[changeCnt + 1][N][N];

        visited[changeCnt][0][0] = true;
        queue.add(new Integer[]{changeCnt,0,0});

        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            int breakCnt = now[0];
            int nowY = now[1];
            int nowX = now[2];

            if (nowY == N - 1 && nowX == N - 1) {
                return true;
            }

            for (int dir = 0; dir < 4; ++dir) {
                int ny = nowY + dy[dir];
                int nx = nowX + dx[dir];

                if (isSafe(ny, nx)) {
                    if (breakCnt > 0 && board[ny][nx] == BLACK) {
                        int nxtBreakCnt = breakCnt - 1;
                        if (!visited[nxtBreakCnt][ny][nx]) {
                            visited[nxtBreakCnt][ny][nx] = true;
                            queue.add(new Integer[]{nxtBreakCnt, ny, nx});
                        }
                    } else if (board[ny][nx] == WHITE) {
                        if (!visited[breakCnt][ny][nx]) {
                            visited[breakCnt][ny][nx] = true;
                            queue.add(new Integer[]{breakCnt, ny, nx});
                        }
                    }
                }
            }
        }

        return false;
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
