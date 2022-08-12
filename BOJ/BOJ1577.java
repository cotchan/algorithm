import java.io.*;

public class Main {

    static final int UP = 0;
    static final int LEFT = 1;

    static int N, M, K;
    static long dp[][];
    static boolean[][][] roads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dp = new long[M+1][N+1];
        roads = new boolean[2][M+1][N+1];

        K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int x1 = Integer.parseInt(edgeInfo[0]);
            int y1 = Integer.parseInt(edgeInfo[1]);
            int x2 = Integer.parseInt(edgeInfo[2]);
            int y2 = Integer.parseInt(edgeInfo[3]);

            if (x1 == x2) {
                // 세로로 놓인 경우
                int bigY = Math.max(y1, y2);
                int smallY = Math.min(y1, y2);
                roads[UP][bigY][x1] = true;
            } else if (y1 == y2) {
                // 가로로 놓인 경우
                int bigX = Math.max(x1, x2);
                int smallX = Math.min(x1, x2);
                roads[LEFT][y1][bigX] = true;
            }
        }

        //init
        for (int y = 0; y < M + 1; ++y) {
            if (roads[UP][y][0]) {
                break;
            } else {
                dp[y][0] = 1;
            }
        }

        for (int x = 0; x < N + 1; ++x) {
            if (roads[LEFT][0][x]) {
                break;
            } else {
                dp[0][x] = 1;
            }
        }


        for (int y = 1; y < M + 1; ++y) {
            for (int x = 1; x < N + 1; ++x) {
                if (roads[LEFT][y][x] && roads[UP][y][x]) {
                } else if (roads[LEFT][y][x]) {
                    dp[y][x] = dp[y-1][x];
                } else if (roads[UP][y][x]) {
                    dp[y][x] = dp[y][x-1];
                } else {
                    dp[y][x] = dp[y-1][x] + dp[y][x-1];
                }
            }
        }

        System.out.println(dp[M][N]);
    }
}
