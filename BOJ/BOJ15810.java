import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int N, M;
    public static List<Integer> staffs = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        String[] staffTimes = br.readLine().split(" ");

        for (String staffTime : staffTimes) {
            staffs.add(Integer.parseInt(staffTime));
        }

        long minv = 0;
        long maxv = (long)M * (long)Collections.min(staffs);
        long answer = Long.MAX_VALUE;

        while (minv <= maxv) {
            long mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                maxv = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                minv = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean isPossible(long value) {

        long balloonCnt = 0;

        for (int time : staffs) {
            long cnt = value / time;
            balloonCnt += cnt;
        }

        return balloonCnt >= M;
    }
}
