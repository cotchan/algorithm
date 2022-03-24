import java.util.HashSet;
import java.util.Set;

class Solution {

    public static final int NUMBER_SIZE = 9;

    public int solution(int N, int number) {

        Set<Integer>[] numberSets = new HashSet[NUMBER_SIZE];

        for (int i = 0; i < NUMBER_SIZE; ++i) {
            numberSets[i] = new HashSet<>();
        }

        numberSets[1].add(N);

        for (int now = 2; now < NUMBER_SIZE; ++now) {

            Set<Integer> nowSet = numberSets[now];

            for (int nxt = 1; nxt <= now; ++nxt) {
                Set<Integer> firstSet = numberSets[nxt];
                Set<Integer> secondSet = numberSets[now-nxt];

                for (int firstNumber : firstSet) {
                    for (int secondNumber : secondSet) {
                        nowSet.add(firstNumber + secondNumber);
                        nowSet.add(firstNumber - secondNumber);
                        nowSet.add(firstNumber * secondNumber);

                        if (firstNumber != 0 && secondNumber != 0) {
                            nowSet.add(firstNumber / secondNumber);
                        }
                    }
                }
            }

            nowSet.add(getRepeatNumber(N, now));
        }

        for (int i = 1; i < NUMBER_SIZE; ++i) {
            for (int num : numberSets[i]) {
                if (num == number) {
                    return i;
                }
            }
        }

        return -1;
    }

    public int getRepeatNumber(int n, int loopCnt) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < loopCnt; ++i) {
            sb.append(n);
        }

        return Integer.parseInt(sb.toString());
    }
}
