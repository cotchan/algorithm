import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int G;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());

        List<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= (int) Math.sqrt(G); ++i) {
            int r = G / i;
            int q = G % i;

            if (q == 0) {
                int maxv = Math.max(i, r);
                int minv = Math.min(i, r);
                int x = maxv + minv;

                if (x % 2 == 1) {
                    continue;
                }

                x /= 2;

                int y = maxv - x;

                if (x > 0 && y > 0) {
                    answer.add(x);
                }
            }
        }

        Collections.sort(answer);

        if (answer.size() == 0) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();

            for (int weight : answer) {
                sb.append(weight);
                sb.append("\n");
            }

            System.out.println(sb);
        }

    }
}
