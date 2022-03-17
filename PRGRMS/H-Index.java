import java.util.*;

class Solution {

    public static int[] refCounts;

    public int solution(int[] citations) {

        Arrays.sort(citations);

        int minv = Integer.MAX_VALUE;
        int maxv = 0;

        for (int refCount : citations) {
            minv = Math.min(minv, refCount);
            maxv = Math.max(maxv, refCount);
        }

        refCounts = new int[maxv + 1];

        for (int refCount : citations) {
            refCounts[refCount]++;
        }

        int answer = 0;

        for (int i = maxv; i >= 0; --i) {
            if (isPossible(i)) {
                answer = i;
                break;
            }
        }

        return answer;
    }

    public boolean isPossible(int val) {

        int over = 0;
        int under = 0;

        for (int i = val; i < refCounts.length; ++i) {
            over += refCounts[i];
        }

        if (val != 0) {
            for (int i = val - 1; i >= 0; --i) {
                under += refCounts[i];
            }
        }

        return over >= val && under < val;
    }
}
