import java.io.*;
import java.util.*;

public class Main {

    static int N, X;
    static int[] visitors, pSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nx = br.readLine().split(" ");

        N = Integer.parseInt(nx[0]);
        X = Integer.parseInt(nx[1]);
        pSum = new int[N+1];

        String[] vString = br.readLine().split(" ");
        visitors = Arrays.stream(vString).mapToInt(Integer::new).toArray();

        int maxv, maxvCount;
        maxv = -1;
        maxvCount = 0;

        for (int i = 1; i <= N; ++i) {
            pSum[i] = pSum[i-1] + visitors[i-1];
        }

        for (int i = X; i <= N; ++i) {
            int candidate = pSum[i] - pSum[i-X];

            if (candidate > maxv) {
                maxv = candidate;
                maxvCount = 1;
            } else if (candidate == maxv) {
                maxvCount++;
            }
        }

        if (maxv != 0) {
            System.out.println(maxv);
            System.out.println(maxvCount);
        } else {
            System.out.println("SAD");
        }
    }
}
