import java.io.*;
import java.util.*;

public class Main {

    public static int N;
    public static Set<Integer> numberSet = new TreeSet<>();
    public static Map<Integer, Integer> numberMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] numberString = br.readLine().split(" ");
        int[] numbers = Arrays.stream(numberString).map(Integer::new).mapToInt(Integer::intValue).toArray();

        for (int num : numbers) {
            numberSet.add(num);
        }

        List<Integer> list = new ArrayList<>();

        for (int num : numberSet) {
            list.add(num);
        }

        for (int i = 0; i < list.size(); ++i) {
            int now = list.get(i);
            int cnt = binarySearch(list, i);
            numberMap.put(now, cnt);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            int now = numbers[i];
            sb.append(numberMap.get(now)).append(" ");
        }

        System.out.println(sb);
    }

    private static int binarySearch(List<Integer> list, int idx) {
        int st = 0;
        int en = idx - 1;
        int target = list.get(idx);
        int ans = 0;

        while (st <= en) {
            int mid = (st + en) / 2;
            int candidate = list.get(mid);
            if (candidate < target) {
                st = mid + 1;
                ans = Math.max(ans, mid + 1);
            } else {
                en = mid - 1;
            }
        }

        return ans;
    }
}
