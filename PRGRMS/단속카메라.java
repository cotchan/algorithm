import java.util.*;

class Solution {

    static class Pair {
        int st, en;

        public Pair(int st, int en) {
            this.st = st;
            this.en = en;
        }
    }

    static Pair[] pairs;

    public int solution(int[][] routes) {
        int answer = 1;

        if (routes.length == 1) {
            return answer;
        }

        pairs = new Pair[routes.length];

        for (int i = 0; i < routes.length; ++i) {
            int st = routes[i][0];
            int en = routes[i][1];
            pairs[i] = new Pair(st, en);
        }

        Arrays.sort(pairs, (a,b) -> {
            if (a.en == b.en) {
                return a.st - b.st;
            }

            return a.en - b.en;
        });

        int en = pairs[0].en;

        for (int i = 1; i < routes.length; ++i) {
            Pair now = pairs[i];

            if (now.st <= en) {
                continue;
            } else {
                en = now.en;
                answer++;
            }
        }

        return answer;
    }
}
