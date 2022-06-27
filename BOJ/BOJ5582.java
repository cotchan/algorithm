
import java.io.*;
import java.util.*;


public class Main {

    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        int len1 = str1.length();
        int len2 = str2.length();

        int size = Math.max(len1, len2);
        dp = new int[size+1][size+1];

        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                char c1 = str1.charAt(i-1);
                char c2 = str2.charAt(j-1);

                if (c1 == c2) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + 1);
                }
            }
        }

        int ans = 0;

        for (int i = 0; i <= len1; ++i) {
            for (int j = 0; j <= len2; ++j) {
                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans);
    }
}
