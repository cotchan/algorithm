
import java.io.*;
import java.util.*;

public class Main {

    static final int IMPOSSIBLE = 100_000_000;
    static int N,M;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dist = new int[N][N];
        for (int i = 0; i < N; ++i) {
            Arrays.fill(dist[i], IMPOSSIBLE);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; ++i) {
            String[] edgeInfo = br.readLine().split(" ");
            int st = Integer.parseInt(edgeInfo[0]) - 1;
            int en = Integer.parseInt(edgeInfo[1]) - 1;

            dist[st][en] = 1;
            dist[en][st] = 1;
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (dist[i][k] != IMPOSSIBLE && dist[k][j] != IMPOSSIBLE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int c1 = -1, c2 = 1;
        int minv = IMPOSSIBLE;

        for (int first = 0; first < N - 1; ++first) {
            for (int second = first + 1; second < N; ++second) {

                //identifier

                //get sum
                int sum = 0;
                for (int i = 0; i < N; ++i) {
                    if (i == first || i == second) continue;
                    int d1 = dist[i][first] * 2;
                    int d2 = dist[i][second] * 2;
                    sum += Math.min(d1,d2);
                }

                if (sum < minv) {
                    minv = sum;
                    c1 = first+1;
                    c2 = second+1;
                }
            }
        }

        System.out.println(c1 + " " + c2 + " " + minv);
    }
}
