import java.io.*;
import java.util.*;


public class Main {

    public static int N, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkInfo[0]);
        K = Integer.parseInt(nkInfo[1]);

        int[] result = getShortestPath();

        System.out.println(result[0]);
        System.out.println(result[1]);
    }

    public static int[] getShortestPath() {

        int shortestTime = 0;
        int cnt = 0;
        boolean[] visit = new boolean[100_001];

        Queue<Integer[]> q = new LinkedList<>();

        q.add(new Integer[]{0, N});
        visit[N] = true;

        while (!q.isEmpty()) {
            Integer[] now = q.poll();

            int nowTime = now[0];
            int nowPos = now[1];

            visit[nowPos] = true;

            if (nowPos == K && cnt == 0) {
                shortestTime = nowTime;
                cnt++;
            } else if (nowPos == K && nowTime == shortestTime) {
                cnt++;
            }

            if (safe(nowPos+1) && !visit[nowPos+1]) {
                q.add(new Integer[]{nowTime+1, nowPos+1});
            }

            if (safe(nowPos-1) && !visit[nowPos-1]) {
                q.add(new Integer[]{nowTime+1, nowPos-1});
            }

            if (safe(nowPos*2) && !visit[nowPos*2]) {
                q.add(new Integer[]{nowTime + 1, nowPos*2});
            }
        }

        return new int[]{shortestTime,cnt};
    }

    public static boolean safe(int pos) {
        return 0 <= pos && pos <= 100000;
    }
}
