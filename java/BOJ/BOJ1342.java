import java.io.*;
import java.util.*;

public class Main {

    static final int ALPHA_SIZE = 26;

    static String origin;
    static int ans;
    static int[] alphaCnt = new int[ALPHA_SIZE];

    public static void dfs(int depth, int prevAlphaIdx) {
        if (depth == origin.length()) {
            ans++;
            return;
        } else {
            for (int nxt = 0; nxt < ALPHA_SIZE; ++nxt) {
                if (nxt != prevAlphaIdx && alphaCnt[nxt] > 0) {
                    alphaCnt[nxt]--;
                    dfs(depth + 1, nxt);
                    alphaCnt[nxt]++;
                } else {
                    continue;
                }
            }
        }
    }

    public static void solve() {
        for (int alphabet = 0; alphabet < ALPHA_SIZE; ++alphabet) {
            if (alphaCnt[alphabet] > 0) {
                alphaCnt[alphabet]--;
                //dfs
                dfs(1, alphabet);
                alphaCnt[alphabet]++;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        origin = br.readLine();

        for (int idx = 0; idx < origin.length(); ++idx) {
            char c = origin.charAt(idx);
            int i = c - 'a';
            alphaCnt[i]++;
        }

        solve();
        System.out.println(ans);
    }
}
