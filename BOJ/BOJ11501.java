import java.io.*;
import java.util.*;

public class Main {

    static int T;
    static LinkedList<Integer> linkedList = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; ++testCase) {
            int day = Integer.parseInt(br.readLine());
            String[] priceString = br.readLine().split(" ");
            int[] prices = Arrays.stream(priceString).mapToInt(Integer::new).toArray();
            int[] maxPrices = new int[day];

            for (int i = day - 1; i >= 0; --i) {
                if (i == day - 1) {
                    maxPrices[i] = prices[i];
                    continue;
                }

                maxPrices[i] = Math.max(maxPrices[i + 1], prices[i]);
            }

            long benefit = 0;

            for (int i = 0; i < day; ++i) {
                //수익 실현이 가능하다면 삼
                if (prices[i] < maxPrices[i]) {
                    linkedList.add(prices[i]);
                } else if (prices[i] == maxPrices[i]) {
                    //팜
                    for (int price : linkedList) {
                        benefit += (prices[i] - price);
                    }
                    linkedList.clear();
                }
            }

            System.out.println(benefit);
        }
    }
}
