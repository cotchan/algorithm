import java.io.*;
import java.util.*;

public class Main {

    static final int UP = 0;
    static final int LEFT = 1;
    static final int DOWN = 2;
    static final int RIGHT = 3;
    static final int DIR_SIZE = 4;
    static final int[] dy = {-1,0,1,0};
    static final int[] dx = {0,-1,0,1};
    static final int EMPTY = 0;
    static final int MIRROR = 1;

    public static boolean isSafe(int n, int y, int x) {
        return (1 <= y && y <= n && 1 <= x && x <= n);
    }

    public static int getLaserDirection(int n, int ly, int lx) {
        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            int ny = ly + dy[dir];
            int nx = lx + dx[dir];
            if (isSafe(n, ny, nx)) {
                return dir;
            }
        }
        return -1;
    }

    //BOJ3709
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < t; ++test_case) {
            String[] info = br.readLine().split(" ");
            int n,r;
            n = Integer.parseInt(info[0]);
            r = Integer.parseInt(info[1]);

            int[][] board = new int[n+2][n+2];

            for (int loop = 0; loop < r; ++loop) {
                String[] mirrorPos = br.readLine().split(" ");
                int y = Integer.parseInt(mirrorPos[0]);
                int x = Integer.parseInt(mirrorPos[1]);
                board[y][x] = MIRROR;
            }

            String[] laserPos = br.readLine().split(" ");
            int ly = Integer.parseInt(laserPos[0]);
            int lx = Integer.parseInt(laserPos[1]);

            int laserDir = getLaserDirection(n, ly, lx);

            int sy = ly + dy[laserDir];
            int sx = lx + dx[laserDir];

            boolean[][][] visited = new boolean[DIR_SIZE][n + 2][n + 2];

            boolean isCycle = false;
            int ay = 0;
            int ax = 0;

            while (true) {

                int ny = sy;
                int nx = sx;

                if (board[ny][nx] == MIRROR) {
                    if (visited[laserDir][ny][nx]) {
                        //무한 루프
                        isCycle = true;
                        break;
                    } else {
                        visited[laserDir][ny][nx] = true;
                        laserDir = (laserDir -1 + DIR_SIZE) % DIR_SIZE;
                    }
                }

                boolean isOut = true;

                while (isSafe(n, ny + dy[laserDir], nx + dx[laserDir])) {
                    ny += dy[laserDir];
                    nx += dx[laserDir];

                    if (board[ny][nx] == MIRROR) {
                        isOut = false;
                        break;
                    }
                }

                if (isOut) {
                    ay = ny + dy[laserDir];
                    ax = nx + dx[laserDir];
                    break;
                } else {
                    sy = ny;
                    sx = nx;
                }
            }

            StringBuilder sb = new StringBuilder();

            if (isCycle) {
                sb.append("0 0");
            } else {
                sb.append(ay);
                sb.append(" ");
                sb.append(ax);
            }
            System.out.println(sb.toString());
        }
    }
}
