import java.io.*;
import java.util.*;

public class Main {

    static int N, TAESOO, P;
    static TreeSet<Integer> treeSet = new TreeSet<>(Comparator.reverseOrder());
    static HashMap<Integer, Integer> hashMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ntp = br.readLine().split(" ");

        N = Integer.parseInt(ntp[0]);
        TAESOO = Integer.parseInt(ntp[1]);
        P = Integer.parseInt(ntp[2]);

        if (N == 0) {
            System.out.println(1);
            return;
        }

        String[] scoreString = br.readLine().split(" ");
        int[] scores = Arrays.stream(scoreString).mapToInt(Integer::new).toArray();



        for (int i = 0; i < scores.length; ++i) {
            int score = scores[i];
            treeSet.add(score);
            hashMap.put(score, hashMap.getOrDefault(score, 0) + 1);
        }

        treeSet.add(TAESOO);
        hashMap.put(TAESOO, hashMap.getOrDefault(TAESOO, 0) + 1);

        int rank = 0;
        for (int score : treeSet) {
            int nowRank = rank + 1;
            int cnt = hashMap.get(score);
            rank += cnt;

            if (score == TAESOO) {
                if (rank <= P) System.out.println(nowRank);
                else System.out.println(-1);
            }
        }
    }
}
