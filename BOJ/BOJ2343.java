import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static int N, M;
    public static List<Integer> lectures = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        String[] lectureInfo = br.readLine().split(" ");

        for (String lecture : lectureInfo) {
            int length = Integer.parseInt(lecture);
            lectures.add(length);
        }

        int minv = Collections.max(lectures);
        int maxv = N * Collections.max(lectures);
        int answer = Integer.MAX_VALUE;

        while (minv <= maxv) {
            int mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                maxv = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                minv = mid + 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean isPossible(int bluRaySize) {

        int bluRayCnt = 0;
        int sum = 0;

        for (int lectureLength : lectures) {
            if (sum + lectureLength <= bluRaySize) {
                sum += lectureLength;
            } else {
                sum = lectureLength;
                bluRayCnt += 1;
            }
        }

        return bluRayCnt < M;
    }
}
