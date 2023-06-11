import java.io.*;
import java.util.*;

public class Main {

    public static int[] numbers;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        numbers = new int[n];

        for (int i = 0; i < n; ++i) {
            int number = Integer.parseInt(br.readLine());
            numbers[i] = number;
        }

        Arrays.sort(numbers);

        int st = 0;
        int en = 0;
        int ans = Integer.MAX_VALUE;

        while (en < n && st <= en) {
            int diff = numbers[en] - numbers[st];

            if (diff >= m) {
                ans = Math.min(ans, diff);
                st++;
            } else {
                //diff < m
                en++;
            }
        }

        System.out.println(ans);
    }
}
