import java.io.*;
import java.util.*;


public class Main {

    public static int N;
    public static int[] leftSum, rightSum;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        leftSum = new int[N];
        rightSum = new int[N];
        String[] arrInfo = br.readLine().split(" ");

        Integer[] arr = Arrays.stream(arrInfo).map(Integer::new).toArray(Integer[]::new);

        rightSum[0] = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            rightSum[i] = arr[i] + rightSum[i - 1];
        }

        leftSum[N - 1] = arr[N - 1];
        for (int i = N-2; i >= 0; --i) {
            leftSum[i] = arr[i] + leftSum[i + 1];
        }

        int maxv = -1;

        // 벌 벌 꿀
        for (int i = 1; i < N - 1; ++i) {
            int total = 0;
            //왼쪽 끝 벌
            total += (rightSum[N - 1] - arr[i] - arr[0]);

            //중간 벌
            total += (rightSum[N - 1] - rightSum[i]);

            maxv = Math.max(maxv, total);
        }

        // 꿀 벌 벌
        for (int i = N-2; i >= 1; --i) {
            int total = 0;
            //오른쪽 끝 벌
            total += (leftSum[0] - arr[i] - arr[N-1]);

            //중간 벌
            total += (leftSum[0] - leftSum[i]);

            maxv = Math.max(maxv, total);
        }

        // 벌 꿀 벌
        for (int i = 1; i < N-1; ++i) {
            int total = 0;
            //왼쪽 끝 벌
            total += (rightSum[i] - arr[0]);

            //오른쪽 끝 벌
            total += (leftSum[i] - arr[N-1]);

            maxv = Math.max(maxv, total);
        }

        System.out.println(maxv);
    }
}
