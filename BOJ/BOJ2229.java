import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] numbers, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dp = new int[N+2];
        numbers = new int[N+1];

        String[] numberString = br.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            numbers[i+1] = Integer.parseInt(numberString[i]);
        }

        for (int i = 1; i <= N; ++i) {
            int maxv, minv;
            maxv = minv = numbers[i];

            for (int j = i; j > 0; --j) {
                maxv = Math.max(maxv, numbers[j]);
                minv = Math.min(minv, numbers[j]);

                dp[i+1] = Math.max(dp[i+1], dp[j] + (maxv - minv));
            }
        }

        System.out.println(dp[N+1]);
    }
}
