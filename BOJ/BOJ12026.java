import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;
    static int N;
    static char[] board;
    static int[] dp;    //현재 칸에 도달할 수 있는 최소 에너지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String b = br.readLine();

        board = new char[N];
        dp = new int[N];

        for (int i = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            board[i] = c;
        }

        Arrays.fill(dp, INF);

        dp[0] = 0;

        for (int i = 0; i < N; ++i) {
            char nowAlphabet = board[i];
            for (int before = 0; before < i; ++before) {
                char beforeAlphabet = board[before];
                int dist = i - before;
                if (nowAlphabet == 'O' && beforeAlphabet == 'B') {
                    dp[i] = Math.min(dp[i], dp[before] + dist*dist);
                } else if (nowAlphabet == 'J' && beforeAlphabet == 'O') {
                    dp[i] = Math.min(dp[i], dp[before] + dist*dist);
                } else if (nowAlphabet == 'B' && beforeAlphabet == 'J') {
                    dp[i] = Math.min(dp[i], dp[before] + dist*dist);
                }
            }
        }

        int answer = dp[N-1] == INF ? -1 : dp[N-1];

        System.out.println(answer);
    }

}
