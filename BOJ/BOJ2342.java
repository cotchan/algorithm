import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    public static int[][][] cache;
    public static List<Integer> tasks = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] dirInfo = br.readLine().split(" ");

        for (String dirString : dirInfo) {
            int val = Integer.parseInt(dirString);
            if (val == 0) break;
            tasks.add(val);
        }

        cache = new int[5][5][100_005];

        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(cache[i][j], -1);
            }
        }

        System.out.println(solve(0,0,0));
    }

    public static int solve(int l, int r, int n) {
        // 기저 사례
        if (n == tasks.size()) {
            return 0;
        }

        if (cache[l][r][n] != -1) {
            return cache[l][r][n];
        }

        int nxt = tasks.get(n);
        int minValue = Math.min(solve(nxt, r, n+1) + getCost(l, nxt), solve(l, nxt,n+1) + getCost(r, nxt));

        return cache[l][r][n] = minValue;
    }

    public static int getCost(int now, int nxt) {
        if (now == nxt) {
            return 1;
        } else if (now == 0) {
            return 2;
        }

        // now != CENTER
        int diff = Math.abs(now - nxt);
        return diff == 2 ? 4 : 3;
    }
}
