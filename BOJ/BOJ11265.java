import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static int N, M;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dist = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String[] rowInfo = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                int time = Integer.parseInt(rowInfo[j]);
                dist[i][j] = time;
            }
        }

        //floyd
        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; ++i) {
            String[] queryInfo = br.readLine().split(" ");
            int src = Integer.parseInt(queryInfo[0]);
            int dst = Integer.parseInt(queryInfo[1]);
            int time = Integer.parseInt(queryInfo[2]);

            src--; dst--;

            if (dist[src][dst] <= time) {
                sb.append("Enjoy other party\n");
            } else {
                sb.append("Stay here\n");
            }
        }

        System.out.println(sb);
    }
}
