import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int N, M;
    public static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N];

        String[] clothInfo = br.readLine().split(" ");

        for (int i = 0; i < N; ++i) {
            int val = Integer.parseInt(clothInfo[i]);
            arr[i] = val;
        }

        int ans = 0;
        Arrays.sort(arr);

        for (int i = 0; i < N-1; ++i) {
            int now = arr[i];

            int minv = i+1;
            int maxv = N - 1;

            while (minv <= maxv) {
                int mid = (minv + maxv) / 2;

                int nxt = arr[mid];
                int candidate = now + nxt;

                if (candidate == M) {
                    ans++;
                    break;
                } else if (candidate > M) {
                    maxv = mid - 1;

                } else {
                    minv = mid + 1;
                }
            }
        }

        System.out.println(ans);
    }
}
