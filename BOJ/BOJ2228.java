import java.io.*;
import java.util.Arrays;

public class Main {

    static final int IMPOSSIBLE = -1000000000;
    static int N, M;
    static int[] numbers;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        numbers = new int[N+1];
        dp = new int[M+1][N+1];

        for (int i = 1; i <= N; ++i) {
            int num = Integer.parseInt(br.readLine());
            numbers[i] = num;
        }

        for (int i = 0; i < M + 1; ++i) {
            Arrays.fill(dp[i], IMPOSSIBLE);
        }

        dp[0][0] = 0;
        dp[1][1] = numbers[1];

        for (int i = 1; i <= M; ++i) {
            for (int j = 1; j <= N; ++j) {
                //이전 구간에 나를 포함시키거나
                dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + numbers[j]);

                //나부터 새로운 구간 시작
                for (int k = 0; k <= j - 2; ++k) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k] + numbers[j]);
                }
            }
        }

        int maxv = IMPOSSIBLE;
        for (int i = 1; i <= N; ++i) {
            maxv = Math.max(maxv, dp[M][i]);
        }

        System.out.println(maxv);
    }
}
