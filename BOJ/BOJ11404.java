import java.io.*;
import java.util.*;


public class Main {

    public static final int IMPOSSIBLE = 100_000_000;
    public static int N, M;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dist = new int[N][N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], IMPOSSIBLE);
        }

        for (int loop = 0; loop < M; ++loop) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int cost = Integer.parseInt(edgeInfo[2]);

            st--; en--;
            dist[st][en] = Math.min(dist[st][en], cost);
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (i == j) continue;

                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int val = dist[i][j];

                if (val == IMPOSSIBLE) {
                    sb.append(0 + " ");
                } else {
                    sb.append(val + " ");
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

}
