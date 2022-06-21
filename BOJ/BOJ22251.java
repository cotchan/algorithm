
import java.io.*;
import java.util.*;


public class Main {

    /**
     * N: 빌딩은 1층부터 N층까지 존재
     * K: 디스플레이는 최대 K 자릿수
     * P: 최대 P개 LED 반전
     * X: 현재 층수 X
     */
    static int N, K, P, X;
    static final int BIT_COUNT = 7;
    static String[] numbers = {"1110111","0010010","1011101","1011011","0111010","1101011","1101111","1010010","1111111","1111011"};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkpxInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkpxInfo[0]);
        K = Integer.parseInt(nkpxInfo[1]);
        P = Integer.parseInt(nkpxInfo[2]);
        X = Integer.parseInt(nkpxInfo[3]);

        //자릿수 맞추고, 해당 숫자로 변환 가능 여부 판단
        int ans = 0;

        for (int i = 1; i <= N; ++i) {
            if (X == i) continue;
            if (!isPossible(X, i, P)) continue;
            ans++;
        }

        System.out.println(ans);
    }

    public static boolean isPossible(int before, int after, int ledCount) {
         int totalBitCount = 0;

        //자릿수 맞추고
        int beforeLength = String.valueOf(before).length();
        int afterLength = String.valueOf(after).length();

        String beforeString = String.valueOf(before);
        String afterString = String.valueOf(after);

        if (beforeLength == afterLength) {
            totalBitCount = getTotalChangedBitCount(beforeString, afterString);
        } else if (beforeLength > afterLength) {
            String afterNewString = changedNumber(after, beforeLength - afterLength);
            totalBitCount = getTotalChangedBitCount(beforeString, afterNewString);
        } else {
            String beforeNewString = changedNumber(before, afterLength - beforeLength);
            totalBitCount = getTotalChangedBitCount(beforeNewString, afterString);
        }

        return totalBitCount <= ledCount;
    }

    public static String changedNumber(int before, int zeroCount) {
        StringBuilder sb = new StringBuilder(String.valueOf(before));
        for (int i = 0; i < zeroCount; ++i) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }

    /**
     * 자릿수는 맞췄다고 가정
     */
    public static int getTotalChangedBitCount(String from, String to) {
        int loopSize = from.length();
        int result = 0;

        for (int i = 0; i < loopSize; ++i) {
            int fromNumber = from.charAt(i) - '0';
            int toNumber = to.charAt(i) - '0';
            result += getChangedBitCount(fromNumber, toNumber);
        }

        return result;
    }

    public static int getChangedBitCount(int from, int to) {
        int result = 0;

        for (int i = 0; i < BIT_COUNT; ++i) {
            char fromState = numbers[from].charAt(i);
            char toState = numbers[to].charAt(i);
            if (fromState == toState) continue;
            result++;
        }

        return result;
    }
}
