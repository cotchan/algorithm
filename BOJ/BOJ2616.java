
import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] guests, pSum;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[4][50001];
        pSum = new int[N+1];

        String[] guestInfos = br.readLine().split(" ");
        guests = Arrays.stream(guestInfos).mapToInt(Integer::new).toArray();

        for (int i = 1; i <= N; ++i) {
            if (i == 1) {
                pSum[i] = guests[i-1];
                continue;
            }

            pSum[i] = pSum[i-1] + guests[i-1];
        }

        M = Integer.parseInt(br.readLine());

        for (int train = 1; train <= 3; ++train) {
            for (int i = M; i <= N; ++i) {
                if (train == 1) {
                    dp[train][i] = Math.max(dp[train][i-1], pSum[i] - pSum[i-M]);
                } else {
                    if (i < train*M) continue;
                    dp[train][i] = Math.max(dp[train][i-1], dp[train-1][i-M] + pSum[i] - pSum[i-M]);
                }
            }
        }

        System.out.println(dp[3][N]);
    }
}
