
import java.io.*;
import java.util.*;

public class Main {

    static int[][] dy = {{-1,1,0,0},{-1,1},{0,0}};
    static int[][] dx = {{0,0,-1,1},{0,0},{-1,1}};
    static int N, M;
    static int stY,stX,enY,enX;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        board = new int[N][M];

        String[] stEnInfo = br.readLine().split(" ");
        stY = Integer.parseInt(stEnInfo[0]);
        stX = Integer.parseInt(stEnInfo[1]);
        enY = Integer.parseInt(stEnInfo[2]);
        enX = Integer.parseInt(stEnInfo[3]);

        stY--; stX--; enY--; enX--;

        for (int i = 0; i < N; ++i) {
            String[] rowInfo = br.readLine().split(" ");
            for (int j = 0; j < rowInfo.length; ++j) {
                int val = Integer.parseInt(rowInfo[j]);
                board[i][j] = val;
            }
        }

        int result = dijkstra();

        if (result == 100_000_000) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static int dijkstra() {
        int[][][] dist = new int[3][N][M];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < N; ++j) {
                Arrays.fill(dist[i][j], 100_000_000);
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
            return a[0] - b[0];
        });

        pq.add(new int[]{board[stY][stX], stY, stX, 1});
        dist[1][stY][stX] = 0;

        while (!pq.isEmpty()) {
            int[] now = pq.poll();
            int nowShock = now[0];
            int nowY = now[1];
            int nowX = now[2];
            int nowOrder = now[3];

            if (dist[nowOrder][nowY][nowX] < nowShock) {
                continue;
            }

            int loopSize = dy[nowOrder].length;
            for (int i = 0; i < loopSize; ++i) {
                int ny = nowY + dy[nowOrder][i];
                int nx = nowX + dx[nowOrder][i];
                int nxtOrder = (nowOrder + 1) % 3;

                if (!safe(ny,nx)) continue;
                if (board[ny][nx]==-1) continue;
                if (dist[nxtOrder][ny][nx] > nowShock + board[ny][nx]) {
                    dist[nxtOrder][ny][nx] = nowShock + board[ny][nx];
                    pq.add(new int[]{dist[nxtOrder][ny][nx], ny, nx, nxtOrder});
                }
            }

        }

        int minv = Integer.MAX_VALUE;
        for (int order = 0; order < 3; ++order) {
            minv = Math.min(minv, dist[order][enY][enX]);
        }

        return minv;
    }

    static boolean safe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
