import java.util.ArrayList;
import java.util.List;

class Solution {

    public static List<Integer[]> answers = new ArrayList<>();

    public int[][] solution(int n) {

        solve(n, 1, 3);

        int[][] answer = new int[answers.size()][2];

        for (int i = 0; i < answers.size(); ++i) {
            Integer[] node = answers.get(i);
            int st = node[0];
            int en = node[1];

            answer[i][0] = st;
            answer[i][1] = en;
        }

        return answer;
    }

    public void solve(int n, int st, int en) {
        // 기저 사례
        if (n == 0) {
            return;
        }

        int mid = 6 - (st + en);
        solve(n - 1, st, mid);
        answers.add(new Integer[]{st, en});
        solve(n - 1, mid, en);
    }
}
