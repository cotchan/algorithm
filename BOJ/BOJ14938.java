import java.io.*;
import java.util.*;


public class Main {

    public static int N, M, R;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmrInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmrInfo[0]);
        M = Integer.parseInt(nmrInfo[1]);
        R = Integer.parseInt(nmrInfo[2]);

        dist = new int[N][N];

        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], 100_000_000);
        }

        String[] itemInfo = br.readLine().split(" ");

        Integer[] items = Arrays.stream(itemInfo).map(Integer::new).toArray(Integer[]::new);

        for (int i = 0; i < R; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]);
            int en = Integer.parseInt(edgeInfo[1]);
            int len = Integer.parseInt(edgeInfo[2]);

            st--; en--;
            dist[st][en] = len;
            dist[en][st] = len;
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int total = Integer.MIN_VALUE;

        for (int i = 0; i < N; ++i) {
            int val = items[i];
            for (int adj = 0; adj < N; ++adj) {
                if (i == adj) continue;
                if (dist[i][adj] <= M) {
                    val += items[adj];
                }
            }

            total = Math.max(total, val);
        }

        System.out.println(total);

    }
}
