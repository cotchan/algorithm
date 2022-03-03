import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static int N,C;
    public static int[] houses;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ncInfo = br.readLine().split(" ");

        N = Integer.parseInt(ncInfo[0]);
        C = Integer.parseInt(ncInfo[1]);

        houses = new int[N];

        for (int i = 0; i < N; ++i) {
            int pos = Integer.parseInt(br.readLine());
            houses[i] = pos;
        }

        Arrays.sort(houses);

        long minv = 0;
        long maxv = houses[N - 1] - houses[0];
        long answer = 0;

        while (minv <= maxv) {
            long mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                answer = Math.max(answer, mid);
                minv = mid + 1;
            } else {
                maxv = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean isPossible(long dist) {
        long cnt = 1;
        long prev = houses[0];

        for (int i = 1; i < houses.length; ++i) {

            long nowDist = houses[i] - prev;

            if (nowDist >= dist) {
                prev = houses[i];
                cnt++;
            }
        }

        return cnt >= C;
    }
}
