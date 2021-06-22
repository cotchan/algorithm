import java.io.*;
import java.util.*;

public class Main {

    static int N,C,W;
    static List<Integer> woods = new LinkedList<>();

    public static long solve(int length) {
        
        long totalIncome = 0;

        for (int wood : woods) {
            int q = wood / length;
            int r = wood % length;

            int income = q * W * length;
            int cost = 0;
            if (q != 0) {
                cost = r == 0 ? (q-1)*C : q*C;
            }

            if (income > cost) {
                totalIncome += (income - cost);
            }
        }

        return totalIncome;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");

        N = Integer.parseInt(info[0]);
        C = Integer.parseInt(info[1]);
        W = Integer.parseInt(info[2]);

        int maxLength = -1;
        for (int loop = 0; loop < N; ++loop) {
            int wood = Integer.parseInt(br.readLine());
            maxLength = Math.max(maxLength, wood);
            woods.add(wood);
        }

        long maxv = -1;
        for (int targetLength = 1; targetLength <= maxLength; ++targetLength) {
            maxv = Math.max(maxv, solve(targetLength));
        }

        System.out.println(maxv);
    }
}
