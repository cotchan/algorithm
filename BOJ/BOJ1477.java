import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int N, M, L;
    public static List<Integer> restAreas = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);
        L = Integer.parseInt(nmInfo[2]);

        if (N > 0) {
            String[] posInfo = br.readLine().split(" ");
            for (String posStr : posInfo) {
                int pos = Integer.parseInt(posStr);
                restAreas.add(pos);
            }
        }

        restAreas.add(0);
        restAreas.add(L);

        Collections.sort(restAreas);

        int minv = 1;
        int maxv = L-1;
        int answer = Integer.MAX_VALUE;

        while (minv <= maxv) {
            int mid = (minv + maxv) / 2;

            int moreAreaNumber = getMoreAreaCount(mid);

            if (moreAreaNumber <= M) {
                maxv = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                minv = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static int getMoreAreaCount(int value) {
        int sum = 0;

        for (int i = 1; i < restAreas.size(); ++i) {
            int length = restAreas.get(i) - restAreas.get(i - 1);
            int moreAreaCount = length / value;

            if (length % value == 0) {
                moreAreaCount--;
            }

            sum += moreAreaCount;
        }

        return sum;
    }
}
