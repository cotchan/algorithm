import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {

        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int scov : scoville) {
            pq.add((long)scov);
        }

        int loopCnt = 0;

        while (pq.size() >= 2 && (pq.peek() < K)) {
            long minv1 = pq.poll();
            long minv2 = pq.poll();

            long newValue = minv1 + (minv2 * 2);
            pq.add(newValue);

            loopCnt++;
        }

        if (!pq.isEmpty() && pq.peek() >= K) {
            return loopCnt;
        } else {
            return -1;
        }
    }
}
