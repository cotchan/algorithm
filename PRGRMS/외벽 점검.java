import java.util.*;

class Solution {

    static int weakSize, memberSize, minValue;
    static List<int[]> weakList = new ArrayList<>();
    static LinkedList<Integer> picks = new LinkedList<>();

    public int solution(int n, int[] weak, int[] dist) {

        if (weak.length == 1) return 1;

        minValue = Integer.MAX_VALUE;
        memberSize = dist.length;
        weakSize = weak.length;

        for (int st = 0; st < weakSize; ++st) {

            List<Integer> list = new ArrayList<>();

            for (int idx = st; idx < st + weakSize; ++idx) {
                if (idx >= weakSize) {
                    list.add(weak[idx % weakSize] + n);
                } else {
                    list.add(weak[idx]);
                }
            }

            int[] arr = Arrays.stream(list.toArray(new Integer[list.size()])).mapToInt(Integer::intValue).toArray();
            weakList.add(arr);
        }

        return solve(dist);
    }

    int solve(int[] dist) {
        for (int state = 1; state < (1 << memberSize); ++state) {
            int now = state;
            List<Integer> candidate = new ArrayList<>();

            for (int i = 0; i < memberSize; ++i) {
                if (now % 2 == 1) {
                    candidate.add(i);
                }
                now /= 2;
            }

            doPermutation(dist, candidate);
        }

        return minValue == Integer.MAX_VALUE ? -1 : minValue;
    }

    void doPermutation(int[] dist, List<Integer> list) {
        boolean[] used = new boolean[list.size()];
        permutation(dist, list, used, 0);
    }

    void permutation(int[] dist, List<Integer> list, boolean[] used, int pickCount) {
        if (pickCount == list.size()) {
            for (int[] weak : weakList) {
                int idx = 0;
                int start = weak[0];

                for (int member : picks) {
                    int coverLength = start + dist[member];

                    while ((idx < weakSize) && coverLength >= weak[idx]) {
                        idx++;
                    }

                    if (idx == weakSize) {
                        minValue = Math.min(minValue, list.size());
                        break;
                    }

                    start = weak[idx];
                }
            }

            return;
        }

        for (int i = 0; i < list.size(); ++i) {
            if (!used[i]) {
                used[i] = true;
                picks.addLast(list.get(i));
                permutation(dist, list, used, pickCount + 1);
                picks.removeLast();
                used[i] = false;
            }
        }
    }
}
