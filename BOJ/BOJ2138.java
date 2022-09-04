import java.io.*;
import java.util.*;

public class Main {

    static int[] dx = {-1, 0, 1};
    static int N;
    static int[] origin1, origin2, wannabe;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String str = br.readLine();
        String str2 = br.readLine();

        origin1 = new int[N];
        origin2 = new int[N];
        wannabe = new int[N];

        for (int i = 0; i < N; ++i) {
            char c = str.charAt(i);
            origin1[i] = c - '0';
            origin2[i] = origin1[i];
        }

        for (int i = 0; i < N; ++i) {
            char c = str2.charAt(i);
            wannabe[i] = c - '0';
        }

        int case1 = 0;

        for (int idx = 1; idx < N; ++idx) {
            int before = idx - 1;
            if (wannabe[before] != origin1[before]) {
                change(origin1, idx);
                case1++;
            }
        }

        if (!isSame(wannabe, origin1)) {
            case1 = Integer.MAX_VALUE;
        }

        change(origin2, 0);
        int case2 = 1;

        for (int idx = 1; idx < N; ++idx) {
            int before = idx - 1;
            if (wannabe[before] != origin2[before]) {
                change(origin2, idx);
                case2++;
            }
        }

        if (!isSame(wannabe, origin2)) {
            case2 = Integer.MAX_VALUE;
        }

        int ans = Math.min(case1, case2);

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    static boolean isSame(int[] arr1, int[] arr2) {
        for (int i = 0; i < N; ++i) {
            if (arr1[i] != arr2[i]) return false;
        }

        return true;
    }

    static void change(int[] arr, int idx) {
        for (int dir = 0; dir < 3; ++dir) {
            int nxt = idx + dx[dir];
            if (!safe(nxt)) continue;
            arr[nxt] = (arr[nxt] + 1) % 2;
        }
    }

    static boolean safe(int idx) {
        return 0 <= idx && idx < N;
    }
}
