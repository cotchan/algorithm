import java.io.*;
import java.util.*;

public class Main {

    static final int NMAX = 25;
    static int N, M;
    static int[] opens;
    static int[][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new int[NMAX][NMAX][NMAX];

        N = Integer.parseInt(br.readLine());
        String[] openInfo = br.readLine().split(" ");

        int open1 = Integer.parseInt(openInfo[0]);
        int open2 = Integer.parseInt(openInfo[1]);

        M = Integer.parseInt(br.readLine());
        opens = new int[M];
        for (int i = 0; i < M; ++i) {
            int now = Integer.parseInt(br.readLine());
            opens[i] = now;
        }

        System.out.println(func(0,open1,open2));
    }

    static int func(int cnt, int open1, int open2) {
        if (cnt == M) {
            return 0;
        }

        if (dp[cnt][open1][open2] != 0) {
            return dp[cnt][open1][open2];
        }

        int nxt = opens[cnt];
        //open1 이동
        int ret = func(cnt+1, nxt, open2) + Math.abs(nxt - open1);

        //open2 이동
        ret = Math.min(ret, func(cnt+1, nxt, open1) + Math.abs(nxt - open2));
        
        return dp[cnt][open1][open2] = ret;
    }
}
