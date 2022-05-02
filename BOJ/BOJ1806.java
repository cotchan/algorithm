import java.io.*;
import java.util.*;


public class Main {

    public static int N, S;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nsInfo = br.readLine().split(" ");

        N = Integer.parseInt(nsInfo[0]);
        S = Integer.parseInt(nsInfo[1]);

        String[] numberString = br.readLine().split(" ");

        Integer[] numbers = Arrays.stream(numberString)
                                    .map(Integer::new)
                                    .toArray(Integer[]::new);

        int st = 0;
        int en = 0;
        int sum = numbers[en];
        int ans = Integer.MAX_VALUE;

        while (en < N && st <= en) {
            if (sum >= S) {
                ans = Math.min(ans, en - st + 1);

                sum -= numbers[st];
                st++;
            } else {
                en++;
                if (en < N) {
                    sum += numbers[en];
                }
            }
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(ans);
        }
    }

}
