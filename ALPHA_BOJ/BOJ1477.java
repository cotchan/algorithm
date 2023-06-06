import java.io.*;
import java.util.*;

public class Main {

    public static List<Integer> positionList = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nml = br.readLine().split(" ");

        int n = Integer.parseInt(nml[0]);
        int m = Integer.parseInt(nml[1]);
        int l = Integer.parseInt(nml[2]);

        if (n != 0) {
            String[] positionStrings = br.readLine().split(" ");
            int[] positions = Arrays.stream(positionStrings).map(Integer::new).mapToInt(Integer::intValue).toArray();
            for (int position : positions) {
                positionList.add(position);
            }
        }

        positionList.add(0);
        positionList.add(l);
        Collections.sort(positionList);

        int st = 0;
        int en = l;
        int ans = Integer.MAX_VALUE;

        while (st <= en) {
            int mid = (st + en) / 2;

            if (isAnswer(m, mid)) {
                ans = Math.min(ans, mid);
                en = mid - 1;
            } else {
                st = mid + 1;
            }
        }

        System.out.println(ans);
    }

    private static boolean isAnswer(int m, int minDistance) {
        if (minDistance == 0) return false;

        int cnt = 0;

        for (int i = 0; i < positionList.size() - 1; ++i) {
            int distance = positionList.get(i+1) - positionList.get(i);

            if (distance <= minDistance) continue;

            int q = distance / minDistance;
            int r = distance % minDistance;

            cnt += (q - 1);

            if (r != 0) cnt++;
        }

        return cnt <= m;
    }
}
