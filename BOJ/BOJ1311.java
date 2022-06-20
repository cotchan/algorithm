
import java.io.*;
import java.util.*;


public class Main {

    static final int IMPOSSIBLE = 100_000_000;
    static int N;
    /**
     * dp 상태 정의
     * dp[x][y]: 현재 x번째 사람까지 일이 할당된 상태(y)이고, 나머지 일이 모든 사람에게 할당되었을 때의 최솟값
     */
    static int[][] D, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        D = new int[N][N];
        dp = new int[N][1 << N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; ++i) {
            String[] rowInfo = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int val = Integer.parseInt(rowInfo[j]);
                D[i][j] = val;
            }
        }

        System.out.println(solve(0, 0));
    }

    static int solve(int now, int state) {
        //모든 일이 할당된 경우
        if (state == ((1 << N) - 1)) {
            return 0;
        }

        //방문한 상태라면 중복연산 x
        if (dp[now][state] != -1) {
            return dp[now][state];
        }

        int result = IMPOSSIBLE;

        //아직 할당되지 않은 일(nxt)을 나(now)에게 할당
        for (int nxt = 0; nxt < N; ++nxt) {
            if ((state & (1 << nxt)) != 0) continue;

            result = Math.min(result, solve(now+1, state | (1 << nxt)) + D[now][nxt]);
        }

        return dp[now][state] = result;
    }
}
