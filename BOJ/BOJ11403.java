import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static int N;
    public static int[][] dist;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        dist = new int[N][N];

        for (int i = 0; i < N; ++i) {
            String[] numberString = br.readLine().split(" ");

            for (int j = 0; j < N; ++j) {
                dist[i][j] = Integer.parseInt(numberString[j]);
            }
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (dist[i][k] + dist[k][j] == 2) {
                        dist[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                sb.append(dist[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
