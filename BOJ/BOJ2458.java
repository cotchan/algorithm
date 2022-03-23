import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int N, M;
    public static int[][][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dist = new int[2][N][N];

        for (int i = 0; i < M; ++i) {
            String[] cmpInfo = br.readLine().split(" ");
            int a = Integer.parseInt(cmpInfo[0]);
            int b = Integer.parseInt(cmpInfo[1]);

            a--; b--;

            //a가 b보다 키가 작다.
            dist[0][a][b] = 1;
            //b는 a보다 키가 크다.
            dist[1][b][a] = 1;
        }

        for (int s = 0; s < 2; ++s) {
            for (int k = 0; k < N; ++k) {
                for (int i = 0; i < N; ++i) {
                    for (int j = 0; j < N; ++j) {
                        if (dist[s][i][k] == 1 && dist[s][k][j] == 1) {
                            dist[s][i][j] = 1;
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            int compareCnt = 0;
            for (int s = 0; s < 2; ++s) {
                for (int j = 0; j < N; ++j) {
                    if (dist[s][i][j] == 1) {
                        compareCnt++;
                    }
                }
            }

            if (compareCnt == N - 1) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
