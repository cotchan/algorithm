import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static List<Integer> staminaPoints, happyPoints;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] staminaInfo = br.readLine().split(" ");
        String[] happyInfo = br.readLine().split(" ");

        staminaPoints = new ArrayList<>();
        happyPoints = new ArrayList<>();

        for (String stamina : staminaInfo) {
            int val = Integer.parseInt(stamina);
            staminaPoints.add(val);
        }

        for (String happyPoint : happyInfo) {
            int val = Integer.parseInt(happyPoint);
            happyPoints.add(val);
        }


        int maxv = -1;

        for (int state = 0; state < (1 << N); ++state) {

            int staminaSum = 0;
            int happySum = 0;
            int nowState = state;

            for (int idx = 0; idx < N; ++idx) {
                int isPick = nowState % 2;
                
                if (isPick == 1) {
                    staminaSum += staminaPoints.get(idx);
                    happySum += happyPoints.get(idx);
                }

                nowState /= 2;
            }

            if (staminaSum < 100) {
                maxv = Math.max(maxv, happySum);
            }
        }

        System.out.println(maxv);
    }
}
