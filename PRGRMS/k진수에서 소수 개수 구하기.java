import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int n, int k) {

        String convertNumber = convertNumber(n, k);
        String[] candidateNumbers = convertNumber.split("0");

        List<String> numbers = Arrays.stream(candidateNumbers)
                                    .filter(number -> !number.isEmpty())
                                    .collect(Collectors.toList());

        List<String> result = numbers.stream().filter(number -> isPrime(number)).collect(Collectors.toList());

        return result.size();
    }

    //진법 변환
    public String convertNumber(int n, int k) {

        int nowNumber = n;
        StringBuilder sb = new StringBuilder();

        while (nowNumber > 0) {
            int q = nowNumber / k;
            int r = nowNumber % k;

            sb.append(r);
            nowNumber = q;
        }

        return sb.reverse().toString();
    }

    public boolean isPrime(String number) {

        Long candidateNumber = Long.valueOf(number);

        if (candidateNumber < 2) {
            return false;
        }

        for (int loop = 2; loop <= (int)Math.sqrt(candidateNumber); ++loop) {
            if (candidateNumber % loop == 0) {
                return false;
            }
        }

        return true;
    }
}
