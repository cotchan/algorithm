
import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] times, incomes, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        times = new int[N+2];
        incomes = new int[N+2];
        dp = new int[N+2];

        for (int i = 0; i < N; ++i) {
            String[] tpInfo = br.readLine().split(" ");
            int t = Integer.parseInt(tpInfo[0]);
            int p = Integer.parseInt(tpInfo[1]);
            times[i+1] = t;
            incomes[i+1] = p;
        }

        //dp[i]: i일 이전까지 얻을 수 있는 최대이익
        for (int i = 1; i <= N; ++i) {
            //i일에 일을 하지 않았을 때
            dp[i] = Math.max(dp[i], dp[i-1]);

            //i일에 일을 했을 때
            if (i + times[i] <= N+1) {
                dp[i+times[i]] = Math.max(dp[i+times[i]], dp[i] + incomes[i]);
            }
        }

        System.out.println(Math.max(dp[N], dp[N+1]));
    }
}
