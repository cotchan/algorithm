import java.io.*;
import java.util.*;

public class Main {

    static char[] a,b;
    static int[][] dp;
    static int len1, len2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str1 = br.readLine();
        String str2 = br.readLine();

        len1 = str1.length();
        len2 = str2.length();

        a = new char[len1+1];
        b = new char[len2+1];

        for (int i = 0; i < len1; ++i) {
            a[i+1] = str1.charAt(i);
        }

        for (int i = 0; i < len2; ++i) {
            b[i+1] = str2.charAt(i);
        }

        dp = new int[len1+1][len2+1];

        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[len1][len2]);
        System.out.println(print(len1, len2));
    }

    static String print(int i, int j) {
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j]) i--;
            else if (dp[i][j] == dp[i][j-1]) j--;
            else {
                sb.append(a[i]);
                i--;
                j--;
            }
        }

        return sb.reverse().toString();
    }
}
