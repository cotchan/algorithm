import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        String name;
        int power;

        public Pair(String name, int power) {
            this.name = name;
            this.power = power;
        }

        @Override
        public int compareTo(Pair p) {
            return power - p.power;
        }
    }

    static int N, M;
    static HashMap<Integer, String> powerMap = new HashMap<>();
    static Pair[] pairs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        for (int i = 0; i < N; ++i) {
            String[] info = br.readLine().split(" ");
            String name = info[0];
            int power = Integer.parseInt(info[1]);

            if (!powerMap.containsKey(power)) {
                powerMap.put(power, name);
            }
        }

        pairs = new Pair[powerMap.size()];

        int idx = 0;
        for (Map.Entry<Integer, String> entry : powerMap.entrySet()) {
            pairs[idx++] = new Pair(entry.getValue(), entry.getKey());
        }

        Arrays.sort(pairs);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; ++i) {
            int power = Integer.parseInt(br.readLine());
            sb.append(findName(power)).append("\n");
        }

        System.out.println(sb);
    }

    static String findName(int power) {

        int st = 0;
        int en = pairs.length - 1;
        String answer = "";

        while (st <= en) {
            int mid = (st + en) / 2;
            Pair now = pairs[mid];

            if (power == now.power) {
                return now.name;
            } else if (power < now.power) {
                answer = now.name;
                en = mid - 1;
            } else {
                st = mid + 1;
            }
        }

        return answer;
    }
}
