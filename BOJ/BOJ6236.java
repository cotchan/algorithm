import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int N, M;
    public static List<Integer> costs = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        for (int i = 0; i < N; ++i) {
            int cost = Integer.parseInt(br.readLine());
            costs.add(cost);
        }

        int minv = Collections.max(costs);
        int maxv = N * Collections.max(costs);
        int answer = Integer.MAX_VALUE;

        while (minv <= maxv) {
            int mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                maxv = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                minv = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean isPossible(int k) {
        int withDrawCnt = 0;
        int rest = 0;

        for (int cost : costs) {
            if (rest >= cost) {
                rest -= cost;
            } else {
                rest = k - cost;
                withDrawCnt++;
            }
        }

        return withDrawCnt <= M;
    }
}
