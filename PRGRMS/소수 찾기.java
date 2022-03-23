import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

class Solution {

    public static int N;
    public static boolean[] isUsed, isPrime;
    public static Set<Integer> answer = new HashSet<>();
    public static LinkedList<Integer> candidate = new LinkedList<>();

    public int solution(String numbers) {

        N = numbers.length();
        isUsed = new boolean[N];
        isPrime = new boolean[10000000];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i < Math.sqrt(10000000); ++i) {
            if (isPrime[i]) {
                for (int j = i + i; j < 10000000; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        //combination
        for (int state = 1; state < (1 << N); ++state) {

            int nowState = state;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < N; ++i) {
                if ((nowState & (1 << i)) != 0) {
                    sb.append(numbers.charAt(i));
                }
            }

            String nowNumber = sb.toString();

            permutation(nowNumber, 0);
        }

        return answer.size();
    }

    public void permutation(String originNumber, int pickCnt) {

        if (pickCnt == originNumber.length()) {
            StringBuilder sb = new StringBuilder();
            for (int num : candidate) {
                sb.append(num);
            }

            int primeNumber = Integer.parseInt(sb.toString());

            if (isPrime[primeNumber]) {
                answer.add(primeNumber);
            }

            return;
        }


        for (int i = 0; i < originNumber.length(); ++i) {
            if (!isUsed[i]) {
                isUsed[i] = true;
                int number = originNumber.charAt(i) - '0';
                candidate.addLast(number);
                permutation(originNumber, pickCnt + 1);
                candidate.removeLast();
                isUsed[i] = false;
            }
        }
    }
}
