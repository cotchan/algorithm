import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y,x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int DIR_SIZE = 5;
    static final long INF = 10000000005L;
    static final int[] dy = {0, -1, 1, 0, 0};
    static final int[] dx = {0, 0, 0, -1, 1};
    static int N;
    static long[][] dp;
    static Pair[][] positions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new long[DIR_SIZE][N+1];
        positions = new Pair[DIR_SIZE][N+1];

        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            Arrays.fill(dp[dir], INF);
        }

        String[] bakeryInfo = br.readLine().split(" ");

        int bY = Integer.parseInt(bakeryInfo[0]);
        int bX = Integer.parseInt(bakeryInfo[1]);
        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            positions[dir][0] = new Pair(bY, bX);
        }

        for (int i = 1; i <= N; ++i) {
            String[] clientInfo = br.readLine().split(" ");

            int cY = Integer.parseInt(clientInfo[0]);
            int cX = Integer.parseInt(clientInfo[1]);
            for (int dir = 0; dir < DIR_SIZE; ++dir) {
                int ny = cY + dy[dir];
                int nx = cX + dx[dir];
                positions[dir][i] = new Pair(ny, nx);
            }
        }

        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            dp[dir][0] = 0;
        }

        for (int i = 1; i <= N; ++i) {
            for (int dir1 = 0; dir1 < DIR_SIZE; ++dir1) {
                Pair before = positions[dir1][i-1];
                for (int dir2 = 0; dir2 < DIR_SIZE; ++dir2) {
                    Pair now = positions[dir2][i];
                    long dist = getDistance(before.y, before.x, now.y, now.x);
                    dp[dir2][i] = Math.min(dp[dir2][i], dp[dir1][i-1] + dist);
                }
            }
        }

        long ans = Long.MAX_VALUE;
        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            ans = Math.min(ans, dp[dir][N]);
        }

        System.out.println(ans);
    }

    static long getDistance(int y1, int x1, int y2, int x2) {
        return Math.abs((long) y1 - (long) y2) + Math.abs((long) x1 - (long) x2);
    }
}
