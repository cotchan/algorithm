import java.io.*;
import java.util.*;


public class Main {

    public static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] numberString = br.readLine().split(" ");

        Integer[] numbers = Arrays.stream(numberString).map(Integer::new).toArray(Integer[]::new);

        Arrays.sort(numbers);

        int ans = 0;

        for (int i = 0; i < N; ++i) {
            int target = numbers[i];

            int st = 0;
            int en = N-1;
            int val;

            while (st != en) {
                if (en == i) {
                    en--;
                    continue;
                }
                if (st == i) {
                    st++;
                    continue;
                }

                val = numbers[st] + numbers[en];

                if (val == target) {
                    ans++;
                    break;
                } else if (val < target) {
                    st++;
                } else {
                    //val > target
                    en--;
                }
            }
        }

        System.out.println(ans);
    }
}
