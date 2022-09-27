class Solution {

    /**
     * [A,B] => A가 B를 이겼다
     */

    static boolean[][] winnerDp, loserDp;

    public int solution(int n, int[][] results) {
        winnerDp = new boolean[n][n];
        loserDp = new boolean[n][n];


        for (int[] result : results) {
            int winner = result[0];
            int lower = result[1];
            winner--; lower--;

            winnerDp[winner][lower] = true;
            loserDp[lower][winner] = true; //lower가 winner한테 졌다
        }

        for (int k = 0; k < n; ++k) {
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (winnerDp[i][k] && winnerDp[k][j]) winnerDp[i][j] = true;
                    if (loserDp[i][k] && loserDp[k][j]) loserDp[i][j] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < n; ++i) {
            int sum = 0;
            for (int j = 0; j < n; ++j) {
                if (winnerDp[i][j]) sum++;
                if  (loserDp[i][j]) sum++;
            }

            if (sum == n-1) answer++;
        }

        return answer;
    }
}
