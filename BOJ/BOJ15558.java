import java.io.*;
import java.util.*;

public class Main {

    static final int LEFT = 0;
    static int N, K;
    static boolean[][] board;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");

        N = Integer.parseInt(nk[0]);
        K = Integer.parseInt(nk[1]);

        board = new boolean[2][N+1];
        visit = new boolean[2][N+1];

        for (int i = 0; i < 2; ++i) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); ++j) {
                char c = row.charAt(j);
                board[i][j+1] = c - '0' == 1;
            }
        }

        int result = bfs() ? 1 : 0;
        System.out.println(result);
    }

    static boolean bfs() {

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{LEFT, 1});  //왼쪽 첫번째 칸

        int time = 1;

        while (!q.isEmpty()) {
            for (int loop = 0; loop < q.size(); ++loop) {
                int[] now = q.poll();
                int nowDirection = now[0];
                int nowNumber = now[1];

                if (nowNumber > N) return true;

                int nxtNumber = nowNumber+1;
                if (nxtNumber > N) return true;

                if (nxtNumber > time && !visit[nowDirection][nxtNumber] && board[nowDirection][nxtNumber]) {
                    visit[nowDirection][nxtNumber] = true;
                    q.add(new int[]{nowDirection, nxtNumber});
                }

                nxtNumber = nowNumber - 1;

                if (nxtNumber > time && !visit[nowDirection][nxtNumber] && board[nowDirection][nxtNumber]) {
                    visit[nowDirection][nxtNumber] = true;
                    q.add(new int[]{nowDirection, nxtNumber});
                }

                nxtNumber = nowNumber + K;
                if (nxtNumber > N) return true;

                int nxtDirection = (nowDirection + 1) % 2;

                if (nxtNumber > time && !visit[nxtDirection][nxtNumber] && board[nxtDirection][nxtNumber]) {
                    visit[nxtDirection][nxtNumber] = true;
                    q.add(new int[]{nxtDirection, nxtNumber});
                }
            }

            time++;

        }

        return false;
    }
}
