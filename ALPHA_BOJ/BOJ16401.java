import java.io.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        String[] nm = line.split(" ");
        int m = Integer.parseInt(nm[0]);
        int n = Integer.parseInt(nm[1]);

        String line2 = br.readLine();
        String[] snacks = line2.split(" ");
        int[] snackArray = Arrays.stream(snacks).map(Integer::new).mapToInt(Integer::intValue).toArray();
        Arrays.sort(snackArray);

        int st = 1;
        int en = snackArray[snackArray.length-1];
        int ans = -1;

        while (st <= en) {
            int mid = (st + en) / 2;

            if (isPossible(snackArray, mid, m)) {
                //길이 늘려보기
                ans = mid;
                st = mid + 1;
            } else {
                en = mid - 1;
            }
        }

        int answer = ans == -1 ? 0 : ans;
        System.out.println(answer);
    }

    private static boolean isPossible(int[] arr, int len, int childSize) {
        int cnt = 0;

        for (int snack : arr) {
            cnt += (snack / len);
        }

        return cnt >= childSize;
    }
}
