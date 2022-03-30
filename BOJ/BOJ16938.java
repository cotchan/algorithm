import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

    public static int N, L, R, X;
    public static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nlrxInfo = br.readLine().split(" ");

        N = Integer.parseInt(nlrxInfo[0]);
        L = Integer.parseInt(nlrxInfo[1]);
        R = Integer.parseInt(nlrxInfo[2]);
        X = Integer.parseInt(nlrxInfo[3]);

        numbers = new int[N];

        int ans = 0;
        String[] numberString = br.readLine().split(" ");

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(numberString[i]);
            numbers[i] = number;
        }

        for (int state = 0; state < (1 << N); ++state) {
            int nowState = state;

            List<Integer> candidate = new ArrayList<>();

            for (int i = 0; i < N; ++i) {
                if ((nowState & (1 << i)) != 0) {
                    candidate.add(numbers[i]);
                }
            }

            if (candidate.size() < 2) {
                continue;
            }

            if (isPossible(candidate)) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    public static boolean isPossible(List<Integer> arr) {
        int minv, maxv, sum = 0;
        minv = Collections.min(arr);
        maxv = Collections.max(arr);

        for (int number : arr) {
            sum += number;
        }

        return Math.abs(minv-maxv) >= X && ((L <= sum) && (sum <= R));
    }

}
