import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int N, M;
    public static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        arr = new int[N];

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());
            arr[i] = num;
        }

        Arrays.sort(arr);

        int st = 0;
        int en = 0;
        int ans = Integer.MAX_VALUE;

        while (en < N && st <= en) {
            int diff = arr[en] - arr[st];

            if (diff < M) {
                //en 전진
                en++;
            } else if (diff == M) {
                ans = M;
                break;
            } else {
                //diff > M이면 st 전진
                ans = Math.min(ans, diff);
                st++;
            }
        }

        System.out.println(ans);
    }
}
