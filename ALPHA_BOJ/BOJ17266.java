import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static int[] positions;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        String[] numberString = br.readLine().split(" ");

        positions = Arrays.stream(numberString).map(Integer::new).mapToInt(Integer::intValue).toArray();

        int maxDist = -1;

        for (int i = 0; i < M-1; ++i) {
            int now = i;
            int nxt = i+1;
            int dist = positions[nxt] - positions[now];
            dist = dist % 2 == 0 ? dist/2 : dist/2 + 1;
            maxDist = Math.max(maxDist, dist);
        }

        maxDist = Math.max(maxDist, positions[0] - 0);
        maxDist = Math.max(maxDist, N - positions[M-1]);

        System.out.println(maxDist);
    }
}
