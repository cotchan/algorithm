import java.util.*;

class Solution {

    static final int PRICE = 0;
    static final int TIME = 1;

    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];
        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < prices.length; ++i) {
            int now = prices[i];

            while (!stack.isEmpty() && now < stack.peek()[PRICE]) {
                int[] top = stack.pop();
                int topTime = top[TIME];
                answer[topTime] = i - topTime;
            }

            stack.add(new int[]{now, i});
        }

        while (!stack.isEmpty()) {
            int[] top = stack.pop();
            int topTime = top[TIME];
            answer[topTime] = prices.length - topTime - 1;
        }

        return answer;
    }
}
