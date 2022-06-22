
import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int value, count;

        public Pair(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    static int N;
    static boolean[] dp;
    static List<Pair> coins = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum;
        dp = new boolean[100005];

        for (int t = 0; t < 3; ++t) {
            N = Integer.parseInt(br.readLine());

            sum = 0;
            coins.clear();
            Arrays.fill(dp, false);
            dp[0] = true;

            for (int i = 0; i < N; ++i) {
                String[] coinInfo = br.readLine().split(" ");
                int coinValue = Integer.parseInt(coinInfo[0]);
                int coinCount = Integer.parseInt(coinInfo[1]);

                sum += (coinValue * coinCount);
                coins.add(new Pair(coinValue, coinCount));
            }

            if (sum % 2 == 1) {
                System.out.println(0);
                continue;
            }

            Collections.sort(coins, (a, b) -> {
                return b.value - a.value;
            });

            for (int i = 0; i < coins.size(); ++i) {
                Pair nowCoin = coins.get(i);

                //최소 1개의 동전을 쓴다고 가정
                for (int now = (sum/2) - nowCoin.value; now >= 0; --now) {

                    //현재 금액을 만들 수 있다면,
                    if (dp[now]) {
                        //nowCoin을 주어진 갯수만큼 더한 금액도 만들 수 있다.
                        for (int j = 1; j <= nowCoin.count; ++j) {
                            int addValue = nowCoin.value * j;
                            if(now + addValue > sum/2) continue;
                            dp[now + addValue] = true;
                        }
                    }
                }
            }

            if (dp[sum/2]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}
