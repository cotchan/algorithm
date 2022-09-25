import java.util.*;

class Solution {

    static int N;
    static int[] arr;

    public int solution(int distance, int[] rocks, int n) {
        N = n;

        List<Integer> list = new ArrayList<>();
        list.add(0);
        for (int rock : rocks) {
            list.add(rock);
        }

        list.add(distance);

        Collections.sort(list);

        arr = Arrays.stream(list.toArray(new Integer[list.size()])).mapToInt(Integer::new).toArray();

        long st = 0;
        long en = distance;
        int answer = 0;

        while (st <= en) {
            long mid = (st + en)/ 2;

            if (isPossible(mid)) {
                st = mid + 1;
                answer = Math.max(answer, (int)mid);
            } else {
                en = mid - 1;
            }
        }

        return answer;
    }

    boolean isPossible(long distance) {
        int sum = 0;
        int before = 0;

        for (int i = 1; i < arr.length; ++i) {
            int now = arr[i];
            int dist = now - before;
            if (distance > dist) {
                sum++;
            } else {
                //distance <= dist
                before = now;
            }
        }

        return sum <= N;
    }
}
