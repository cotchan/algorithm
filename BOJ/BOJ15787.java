import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class Main {

    public static final int TRAIN_SEAT_SIZE = 20;
    public static int N, M;
    public static boolean[][] trainState;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        trainState = new boolean[N+1][20];

        for (int i = 0; i < M; ++i) {
            String[] cmdInfo = br.readLine().split(" ");
            int cmdType = Integer.parseInt(cmdInfo[0]);
            int trainIdx = Integer.parseInt(cmdInfo[1]);

            trainIdx--;

            if (cmdType == 1) {
                int seatIdx = Integer.parseInt(cmdInfo[2]);
                seatIdx--;
                trainState[trainIdx][seatIdx] = true;
            } else if (cmdType == 2) {
                int seatIdx = Integer.parseInt(cmdInfo[2]);
                seatIdx--;
                trainState[trainIdx][seatIdx] = false;
            } else if (cmdType == 3) {
                for (int idx = TRAIN_SEAT_SIZE -2; idx >= 0; --idx) {
                    trainState[trainIdx][idx+1] = trainState[trainIdx][idx];
                }

                trainState[trainIdx][0] = false;

            } else {
                for (int idx = 0; idx < TRAIN_SEAT_SIZE -1; ++idx) {
                    trainState[trainIdx][idx] = trainState[trainIdx][idx+1];
                }

                trainState[trainIdx][TRAIN_SEAT_SIZE -1] = false;
            }
        }

        Set<Integer> answer = new HashSet<>();

        for (int i = 0; i < N; ++i) {
            int state = 0;
            for (int t = 0; t < TRAIN_SEAT_SIZE; ++t) {
                if (trainState[i][t]) {
                    state |= (1 << t);
                }
            }

            answer.add(state);
        }

        System.out.println(answer.size());
    }
}
