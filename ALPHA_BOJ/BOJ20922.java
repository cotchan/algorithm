import java.io.*;
import java.util.Arrays;

public class Main {

    public static final int MAX_NUMBER = 100_001;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = br.readLine().split(" ");

        int N = Integer.parseInt(nk[0]);
        int K = Integer.parseInt(nk[1]);

        String[] numberString = br.readLine().split(" ");

        int[] numbers = Arrays.stream(numberString)
                .map(Integer::new)
                .mapToInt(Integer::intValue)
                .toArray();

        int[] numberCounts = new int[MAX_NUMBER];
        int st = 0;
        int en = 0;
        numberCounts[numbers[0]] = 1;
        int ans = 1;

        while (en < N && st <= en) {
            en++;

            if (en >= N) break;

            int newNumber = numbers[en];
            numberCounts[newNumber]++;

            while (numberCounts[newNumber] > K && st <= en) {
                int head = numbers[st];
                numberCounts[head]--;
                st++;
            }

            ans = Math.max(ans, en - st + 1);
        }

        System.out.println(ans);
    }
}
