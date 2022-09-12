import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    public int solution(int n, int[] cores) {

        if (n <= cores.length) {
            return n;
        }

        int maxv = Arrays.stream(cores).max().getAsInt();

        //위에서 0초에 대한 계산은 했으므로 lowerBound는 1초
        int st = 1;
        int en = maxv * n;
        int time = Integer.MAX_VALUE;

        while (st <= en) {
            int mid = (st + en) / 2;
            int totalJobCount = getTotalJobCount(cores, mid);

            if (totalJobCount >= n) {
                time = Math.min(time, mid);
                en = mid - 1;
            } else {
                st = mid + 1;
            }
        }

        //time -1 초에 처리하는 job 갯수는 n 이하임
        int totalJobCount = getTotalJobCount(cores, time - 1);
        int restJobSize = n - totalJobCount;
        int cnt = 0;

        for (int i = 0; i < cores.length; ++i) {
            if (time % cores[i] == 0) cnt++;
            if (restJobSize == cnt) return i+1;
        }

        int answer = 0;
        return answer;
    }

    int getTotalJobCount(int[] cores, int time) {
        int value = 0;

        for (int core : cores) {
            value += (time / core) + 1;
        }

        return value;
    }
}
