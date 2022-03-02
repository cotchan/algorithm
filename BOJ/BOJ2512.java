import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static int N, M;
    public static List<Integer> budgets = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        String[] budgetInfo = br.readLine().split(" ");
        M = Integer.parseInt(br.readLine());

        int totalCost = 0;

        for (String budget : budgetInfo) {
            int value = Integer.parseInt(budget);
            budgets.add(value);
            totalCost += value;
        }

        if (totalCost <= M) {
            System.out.println(Collections.max(budgets));
            return;
        }

        int minv = 0;
        int maxv = Collections.max(budgets);
        int answer = Integer.MIN_VALUE;

        while (minv <= maxv) {

            int mid = (minv + maxv) / 2;

            if (isPossible(mid)) {
                minv = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                maxv = mid - 1;
            }
        }

        System.out.println(answer);
    }

    public static boolean isPossible(int value) {
        int totalCost = 0;

        for (Integer budget : budgets) {
            if (budget >= value) {
                totalCost += value;
            } else {
                totalCost += budget;
            }
        }

        return totalCost <= M;
    }
}
