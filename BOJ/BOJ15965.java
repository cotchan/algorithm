import java.io.*;
import java.util.*;


public class Main {

    public static int K;
    public static boolean[] isPrime;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        final int MAX_VALUE = 100 * K;

        isPrime = new boolean[MAX_VALUE];
        Arrays.fill(isPrime, true);

        for (int i = 2; i <= (int) Math.sqrt(MAX_VALUE); ++i) {
            if (isPrime[i]) {
                for (int j = i + i; j < MAX_VALUE; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        if (K == 1) {
            System.out.println(2);
            return;
        }

        int primeCnt = 2;
        int value = 3;

        while (true) {
            if (primeCnt == K) {
                break;
            }

            value += 2;

            if (isPrime[value]) {
                primeCnt++;
            }
        }

        System.out.println(value);
    }
}
