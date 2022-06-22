
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] dp;
    static List<Integer[]> lines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 0; i < N; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int left = Integer.parseInt(edgeInfo[0]);
            int right = Integer.parseInt(edgeInfo[1]);

            lines.add(new Integer[]{left, right});
        }

        //left 기준으로 오름차순 정렬
        Collections.sort(lines, (a, b) -> {
            return a[0] - b[0];
        });

        for (int head = 0; head < N; ++head) {
            for (int tail = 0; tail < head; ++tail) {
                int headValue = lines.get(head)[1];
                int tailValue = lines.get(tail)[1];

                //head와 tail 관계가 정방향일 때, LIS 증가
                if (tailValue < headValue) {
                    dp[head] = Math.max(dp[head], dp[tail] + 1);
                }
            }
        }

        int ans = -1;
        for (int i = 0; i < N; ++i) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(N - ans);
    }
}
