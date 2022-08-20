import java.util.*;

class Solution {

    public int solution(int n, int k) {
        //k진수로 만들기
        StringBuilder sb = new StringBuilder();

        int number = n;

        while (number != 0) {
            int q = number / k;
            int r = number % k;
            number = q;
            sb.append(r);
        }

        sb.reverse();
        String[] numbers = sb.toString().split("0");

        int answer = 0;
        for (String s : numbers) {
            try {
                long value = Long.parseLong(s);
                if (isPrime(value)) answer++;
            } catch (NumberFormatException e) {
                continue;
            }
        }

        return answer;
    }

    public boolean isPrime(long value) {
        if (value == 1) return false;
        else if (value == 2) return true;

        for (int i = 2; i <= (int)Math.sqrt(value); ++i) {
            if (value % i == 0) return false;
        }

        return true;
    }
}
