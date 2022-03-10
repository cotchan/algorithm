import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {

    static class Pair {
        int number;
        int cnt;

        public Pair(int number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }
    }

    public static final int MAX_NUMBER = 100001;

    public int[] solution(String s) {

        String[] numbers = s.split("[,{}]");

        int[] numberCnt = new int[MAX_NUMBER];

        for (String number : numbers) {
            if (!number.isEmpty()) {
                int targetNumber = Integer.parseInt(number);
                numberCnt[targetNumber]++;
            }
        }

        List<Pair> result = new ArrayList<>();

        for (int i = 0; i < MAX_NUMBER; ++i) {
            if (numberCnt[i] > 0) {
                result.add(new Pair(i, numberCnt[i]));
            }
        }

        Collections.sort(result, (p1, p2) -> {
            if (p1.cnt > p2.cnt) return -1;
            else if (p1.cnt == p2.cnt) return 0;
            else return 1;
        });

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); ++i) {
            int num = result.get(i).number;
            answer[i] = num;
        }
        
        return answer;
    }
}
