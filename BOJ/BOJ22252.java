
import java.io.*;
import java.util.*;

public class Main {

    static int Q;
    static Map<String,PriorityQueue<Integer>> gorilas = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Q = Integer.parseInt(br.readLine());

        long ans = 0;

        for (int loop = 0; loop < Q; ++loop) {
            String[] gorilaInfo = br.readLine().split(" ");

            int type = Integer.parseInt(gorilaInfo[0]);
            String name = gorilaInfo[1];

            if (type == 1) {
                int infoCount = Integer.parseInt(gorilaInfo[2]);

                gorilas.putIfAbsent(name, new PriorityQueue<>(Comparator.reverseOrder()));

                for (int i = 0; i < infoCount; ++i) {
                    int value = Integer.parseInt(gorilaInfo[i+3]);
                    gorilas.get(name).add(value);
                }
            } else {
                int buyCount = Integer.parseInt(gorilaInfo[2]);
                for (int i = 0; i < buyCount; ++i) {
                    if (gorilas.containsKey(name) && !gorilas.get(name).isEmpty()) {
                        ans += gorilas.get(name).poll();
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println(ans);
    }
}
