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
        long ans = Long.MAX_VALUE;
        int[] ansValues = new int[3];

        for (int i = 0; i < N - 2; ++i) {
            for (int j = i + 1; j < N - 1; ++j) {

                //binarySearch(j+1 ~ N-1)
                int st = j + 1;
                int en = N - 1;

                while (st <= en) {
                    int mid = (st + en) / 2;
                    long value = (long)numbers[i] + (long)numbers[j] + (long)numbers[mid];

                    if (Math.abs(value) < ans) {
                        ans = Math.abs(value);
                        ansValues[0] = numbers[i];
                        ansValues[1] = numbers[j];
                        ansValues[2] = numbers[mid];
                    }

                    if (value < 0) {
                        //mid >>
                        st = mid + 1;
                    } else if (value > 0) {
                        //mid <<
                        en = mid - 1;
                    } else {
                        System.out.println(ansValues[0] + " " + ansValues[1] + " " + ansValues[2]);
                        return;
                    }
                }
            }
        }

        System.out.println(ansValues[0] + " " + ansValues[1] + " " + ansValues[2]);
    }
}
