import java.io.*;
import java.util.*;

public class Main {

    static String DNA;
    static int N;
    static int[][] dp;
    static char[] to = new char['z'];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        DNA = br.readLine();
        N = DNA.length();

        to['a'] = 't';
        to['g'] = 'c';

        dp = new int[N+1][N+1];

        for (int len = 1; len <= N; ++len) {
            for (int st = 0; st + len < N; ++st) {
                int en = st + len;
                char head = DNA.charAt(st);
                char tail = DNA.charAt(en);

                if ((head == 'a' || head == 'g') && (tail == to[head])) {
                    dp[st][en] = 2 + dp[st+1][en-1];
                }

                for (int mid = st; mid < en; ++mid) {
                    dp[st][en] = Math.max(dp[st][en], dp[st][mid] + dp[mid+1][en]);
                }
            }
        }

        System.out.println(dp[0][N-1]);
    }
}
