import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int N;
    public static HashMap<String, Integer> rightOrder = new HashMap<>();
    public static List<String> nowOrder = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            String car = br.readLine();
            rightOrder.put(car,i+1);
        }

        for (int i = 0; i < N; ++i) {
            String car = br.readLine();
            nowOrder.add(car);
        }

        int cnt = 0;

        for (int now = 0; now < N; ++now) {
            for (int nxt = now + 1; nxt < N; ++nxt) {
                int nowCarRank = rightOrder.get(nowOrder.get(now));
                int nxtCarRank = rightOrder.get(nowOrder.get(nxt));

                if (nowCarRank > nxtCarRank) {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
