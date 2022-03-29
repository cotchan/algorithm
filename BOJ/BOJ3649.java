import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    public static final int OFFSET = 10_000_000;
    public static int X, N;
    public static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s;

        while ((s = br.readLine()) != null) {
            X = Integer.parseInt(s) * OFFSET;
            N = Integer.parseInt(br.readLine());
            arr = new int[N];

            for (int i = 0; i < N; ++i) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(arr);

            int st = 0;
            int en = N-1;
            int diff = -1;
            int minv = -1;
            int maxv = -1;

            while (st < en) {

                int sum = arr[st] + arr[en];

                if (sum > X) {
                    en--;
                } else if (sum == X) {
                    int candidate = Math.abs(arr[st] - arr[en]);
                    if (candidate > diff) {
                        diff = candidate;
                        minv = arr[st];
                        maxv = arr[en];
                    }
                    en--;
                } else {
                    //sum < X
                    st++;
                }
            }

            if (minv == -1) {
                System.out.println("danger");
            } else {
                System.out.println(String.format("yes %d %d", minv, maxv));
            }
        }
    }
}
