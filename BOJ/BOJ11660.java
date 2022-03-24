import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int N, M;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        int[][] board, dp;

        board = new int[N][N];
        dp = new int[2000][2000];

        for (int i = 0; i < N; ++i) {
            String[] rowInfo = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int num = Integer.parseInt(rowInfo[j]);
                board[i][j] = num;
            }
        }

        for (int i = N - 1; i >= 0; --i) {
            for (int j = N - 1; j >= 0; --j) {
                dp[i][j] = board[i][j];
                dp[i][j] += dp[i+1][j];
                dp[i][j] += dp[i][j+1];
                dp[i][j] -= dp[i+1][j+1];
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; ++i) {
            String[] posInfo = br.readLine().split(" ");
            int r1 = Integer.parseInt(posInfo[0]);
            int c1 = Integer.parseInt(posInfo[1]);
            int r2 = Integer.parseInt(posInfo[2]);
            int c2 = Integer.parseInt(posInfo[3]);

            r1--; c1--; r2--; c2--;

            int result = dp[r1][c1] - dp[r2+1][c1] - dp[r1][c2+1] + dp[r2+1][c2+1];

            sb.append(result);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean safe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

}
