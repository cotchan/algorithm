import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static final int NUMBER_SIZE = 100_001;
    public static int N, K;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkInfo[0]);
        K = Integer.parseInt(nkInfo[1]);

        String[] numberString = br.readLine().split(" ");

        int[] numbers = new int[N];
        int[] repeatCnt = new int[NUMBER_SIZE];

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(numberString[i]);
            numbers[i] = number;
        }

        int st, en, ans;
        st = en = ans = 0;

        while (en < N && st <= en) {
            if (repeatCnt[numbers[en]] < K) {
                repeatCnt[numbers[en]]++;
                en++;
                ans = Math.max(ans, en - st);
            } else if (repeatCnt[numbers[en]] == K) {
                repeatCnt[numbers[st]]--;
                st++;
            }
        }

        System.out.println(ans);
    }
}
