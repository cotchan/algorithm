import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long X;
    static int[] products;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nxInfo = br.readLine().split(" ");
        N = Integer.parseInt(nxInfo[0]);
        X = Long.parseLong(nxInfo[1]);

        String[] productsInfo = br.readLine().split(" ");
        products = Arrays.stream(productsInfo).mapToInt(Integer::new).toArray();

        int st = 1;
        int en = N;

        int ans = Integer.MAX_VALUE;

        while (st <= en) {
            int mid = (st + en) / 2;

            if (isPossible(mid)) {
                ans = Math.min(ans, mid);
                en = mid-1;
            } else {
                st = mid +1;
            }
        }

        System.out.println(ans);
    }

    static boolean isPossible(int value) {

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < value; ++i) {
            pq.add(0L);
        }

        long maxv = 0;

        for (int i = 0; i < N; ++i) {
            int productTime = products[i];
            if (!pq.isEmpty()) {
                long now = pq.poll();
                long nxtTime = now + productTime;
                maxv = Math.max(maxv, nxtTime);
                pq.add(nxtTime);
            }
        }

        return maxv <= X;
    }
}
