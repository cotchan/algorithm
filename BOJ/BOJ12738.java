
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static int[] numbers;
    static List<Integer> lis = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] numberInfo = br.readLine().split(" ");
        numbers = Arrays.stream(numberInfo).mapToInt(Integer::new).toArray();

        for (int number : numbers) {
            int idx = getLowerBound(number);

            if (idx == lis.size()) {
                lis.add(number);
            } else {
                int comparedValue = lis.get(idx);

                if (comparedValue > number) {
                    lis.set(idx, number);
                }
            }
        }

        System.out.println(lis.size());
    }

    static int getLowerBound(int num) {
        int st = 0;
        int en = lis.size() - 1;
        int ans = lis.size();

        while (st <= en) {
            int mid = (st + en) / 2;
            int comparedValue = lis.get(mid);

            if (num <= comparedValue) {
                ans = mid;
                en = mid - 1;
            } else {
                st = mid + 1;
            }
        }

        return ans;
    }
}
