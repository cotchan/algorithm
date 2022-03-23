class Solution {
    public long solution(int n, int[] times) {

        long maxv = 0;

        for (int time : times) {
            maxv = Math.max(maxv, time);
        }

        long minv = 0;
        maxv *= (n / times.length);
        long ans = maxv;

        while (minv <= maxv) {
            long mid = (minv + maxv) / 2;

            if (isPossible(mid, n, times)) {
                maxv = mid - 1;
                ans = Math.min(ans, mid);
            } else {
                minv = mid + 1;
            }
        }

        return ans;
    }

    public boolean isPossible(long value, int n, int[] times) {
        long result = 0;

        for (int time : times) {
            result += (value / time);
        }

        return result >= n;
    }
}
