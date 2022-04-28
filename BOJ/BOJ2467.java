import java.io.*;
import java.util.*;


public class Main {

    public static int N;
    public static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        String[] numberStrings = br.readLine().split(" ");

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(numberStrings[i]);
            numbers[i] = number;
        }

        Arrays.sort(numbers);

        int st = 0;
        int en = N-1;
        int ans = Integer.MAX_VALUE;
        int[] ansValues = new int[2];

        while (st < en) {
            int value = numbers[st] + numbers[en];

            if (Math.abs(value) < ans) {
                ans = Math.abs(value);
                ansValues[0] = numbers[st];
                ansValues[1] = numbers[en];
            }

            if (value > 0) {
                en--;
            } else if (value < 0) {
                st++;
            } else {
                break;
            }
        }

        System.out.println(ansValues[0] + " " + ansValues[1]);
    }
}
