
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

    static String[] numbers = {
            "1110111",  //0
            "0010010",  //1
            "1011101",  //2
            "1011011",  //3
            "0111010",  //4
            "1101011",  //5
            "1101111",  //6
            "1010010",  //7
            "1111111",  //8
            "1111011"   //9
    };

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

    /**
     * @param number: 대상 숫자
     * @param zeroCount: 앞에 채워야 하는 0의 갯수
     * @return: e.g. 000number
     */
    public static String changedNumber(int number, int zeroCount) {
        StringBuilder sb = new StringBuilder(String.valueOf(number));
        for (int i = 0; i < zeroCount; ++i) {
            sb.insert(0, '0');
        }

        return sb.toString();
    }

    /**
     * 자릿수는 맞췄다고 가정
     * getChangedBitCount()를 사용해서 전체 자릿수 바꾸는데 필요한 비트를 계산합니다.
     */
    public static int getTotalChangedBitCount(String from, String to) {
        int loopSize = from.length();
        int totalChangedBitCount = 0;

        for (int i = 0; i < loopSize; ++i) {
            int fromNumber = from.charAt(i) - '0';
            int toNumber = to.charAt(i) - '0';
            totalChangedBitCount += getChangedBitCount(fromNumber, toNumber);
        }

        return totalChangedBitCount;
    }

    //앞에 미리 선언한 'LED 전광판 숫자'를 나타내는 String[] numbers을 통해
    //0~9 한자리 숫자끼리 서로 바꾸는데 몇 개 비트가 필요한지 계산합니다.
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
