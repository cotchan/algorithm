import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;
    static final int DIR_SIZE = 3;
    static final int[] dy = {-1,-1,-1};
    static final int[] dx = {-1,0,1};
    static int N, M;
    static int[][] costMap;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        costMap = new int[N][M];
        dp = new int[DIR_SIZE][N][M];

        for (int i = 0; i < N; ++i) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < row.length; ++j) {
                int cost = Integer.parseInt(row[j]);
                costMap[i][j] = cost;
            }
        }

        for (int i = 0; i < DIR_SIZE; ++i) {
            for (int j = 0; j < N; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        for (int dir = 0; dir < DIR_SIZE; ++dir) {
            for (int x = 0; x < M; ++x) {
                dp[dir][0][x] = costMap[0][x];
            }
        }

        for (int y = 1; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                for (int dir = 0; dir < DIR_SIZE; ++dir) {
                    for (int beforeDir = 0; beforeDir < DIR_SIZE; ++beforeDir) {
                        if (dir == beforeDir) continue;

                        int by = y + dy[beforeDir];
                        int bx = x + dx[beforeDir];
                        if (!safe(by,bx)) continue;

                        dp[dir][y][x] = Math.min(dp[dir][y][x], dp[beforeDir][by][bx] + costMap[y][x]);
                    }
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int x = 0; x < M; ++x) {
            for (int dir = 0; dir < DIR_SIZE; ++dir) {
                ans = Math.min(ans, dp[dir][N - 1][x]);
            }
        }

        System.out.println(ans);
    }

    static boolean safe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
