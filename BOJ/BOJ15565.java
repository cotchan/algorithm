import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static int N, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkInfo[0]);
        K = Integer.parseInt(nkInfo[1]);

        String[] numberString = br.readLine().split(" ");

        int[] arr = new int[N];

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(numberString[i]);
            arr[i] = number;
        }

        int st = 0;
        int en = 0;
        int lionCnt = arr[0] == 1 ? 1 : 0;
        int ans = Integer.MAX_VALUE;

        while (en < N && st <= en) {
            if (lionCnt < K) {
                en++;
                if (en == N) break;
                if (arr[en] == 1) {
                    lionCnt++;
                }
            } else {
                ans = Math.min(ans, en - st + 1);
                if (arr[st] == 1) {
                    lionCnt--;
                }
                st++;
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }
}
