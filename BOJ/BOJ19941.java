
import java.io.*;
import java.util.*;


public class Main {

    static int N,K;
    static boolean[] isMapping;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkInfo[0]);
        K = Integer.parseInt(nkInfo[1]);
        isMapping = new boolean[N];

        String table = br.readLine();

        int ans = 0;

        //P: 사람
        //H: 햄벅
        for (int i = 0; i < N; ++i) {
            if (table.charAt(i) == 'P') continue;
            //table.charAt(i) == 'H'
            int min = i - K;
            int max = i + K;
            for (int idx = min; idx <= max; ++idx) {
                if (!isSafe(idx)) continue;
                if (table.charAt(idx) == 'P' && !isMapping[idx]) {
                    isMapping[idx] = true;
                    ans++;
                    break;
                }
            }
        }

        System.out.println(ans);
    }

    static boolean isSafe(int idx) {
        return 0 <= idx && idx < N;
    }
}
