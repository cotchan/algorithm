import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] bString = br.readLine().split(" ");
        int[] B = Arrays.stream(bString).mapToInt(Integer::new).toArray();
        int result = 0;

        while (true) {
            boolean isAllZero = true;
            boolean isAllEven = true;

            for (int i = 0; i < N; ++i) {
                if (B[i] != 0) isAllZero = false;

                if (B[i] % 2 == 1) {
                    B[i]--;
                    result++;
                    isAllEven = false;
                }
            }

            if (isAllZero) break;
            if (isAllEven) {
                for (int i = 0; i < N; ++i) {
                    B[i] /= 2;
                }
                result++;
            }
        }

        System.out.println(result);
    }
}
