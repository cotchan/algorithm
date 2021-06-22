import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long arrivedTime;

    public static long getBusArrivedTime(long stTime, long timeOffset, int busNumber) {
        return (stTime + (timeOffset * (busNumber - 1)));
    }

    public static boolean isPossible(long time) {
        return time >= arrivedTime ? true : false;
    }

    //BOJ1590
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        arrivedTime = Long.parseLong(info[1]);

        long ans = Long.MAX_VALUE;

        for (int loop = 0; loop < N; ++loop) {
            String[] info2 = br.readLine().split(" ");
            //시작 시각, 간격, 버스 갯수
            long stTime = Long.parseLong(info2[0]);
            long timeOffset = Long.parseLong(info2[1]);
            int busCnt = Integer.parseInt(info2[2]);

            long enTime = stTime + (timeOffset * busCnt - 1);

            int st = 1;
            int en = busCnt;

            long candidate = Long.MAX_VALUE;

            while (st <= en) {
                int mid = (st + en) / 2;
                long midTime = getBusArrivedTime(stTime, timeOffset, mid);
                if (isPossible(midTime)) {
                    candidate = Math.min(candidate, midTime);
                    en = mid-1;
                } else {
                    st = mid+1;
                }
            }

            ans = Math.min(ans, candidate);
        }

        if (ans == Long.MAX_VALUE) {
            System.out.println(-1);
        } else {
            long diff = ans - arrivedTime;
            System.out.println(diff);
        }
    }
}
