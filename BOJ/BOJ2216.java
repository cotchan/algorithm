import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1000000000;
    static int A, B, C;
    static int[][] dp;
    static String a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] abcInfo = br.readLine().split(" ");
        A = Integer.parseInt(abcInfo[0]);
        B = Integer.parseInt(abcInfo[1]);
        C = Integer.parseInt(abcInfo[2]);

        a = br.readLine();
        b = br.readLine();

        dp = new int[a.length()+1][b.length()+1];
        for (int i = 0; i < a.length(); ++i) {
            Arrays.fill(dp[i], INF);
        }

        System.out.println(func(0,0));
    }

    static int func(int aIdx, int bIdx) {
        if (aIdx == a.length() || bIdx == b.length()) {
            return aIdx == a.length() ? B * (b.length() - bIdx) : B * (a.length() - aIdx);
        }

        if (dp[aIdx][bIdx] != INF) {
            return dp[aIdx][bIdx];
        }

        int ret;

        if (a.charAt(aIdx) == b.charAt(bIdx)) {
            ret = A + func(aIdx+1, bIdx+1);
        } else {
            ret = C + func(aIdx+1, bIdx+1);
        }

        ret = Math.max(ret, B + func(aIdx+1, bIdx));
        ret = Math.max(ret, B + func(aIdx, bIdx+1));

        return dp[aIdx][bIdx] = ret;
    }
}
