class Solution {

    public int solution(int[][] board, int[][] skill) {

        int n = board.length;
        int m = board[0].length;

        int[][] dp = new int[1010][1010];

        for (int[] skillInfo : skill) {
            int type = skillInfo[0];
            int r1 = skillInfo[1];
            int c1 = skillInfo[2];
            int r2 = skillInfo[3];
            int c2 = skillInfo[4];
            int degree = skillInfo[5];

            if (type == 1) {
                degree = -degree;
            }

            dp[r1][c1] += degree;
            dp[r1][c2+1] -= degree;
            dp[r2+1][c1] -= degree;
            dp[r2+1][c2+1] += degree;
        }

        //왼쪽->오른쪽 방향으로 누적합
        for (int y = 0; y < n; ++y) {
            for (int x = 1; x < m; ++x) {
                dp[y][x] += dp[y][x - 1];
            }
        }

        //위->아래 방향으로 누적합
        for (int y = 1; y < n; ++y) {
            for (int x = 0; x < m; ++x) {
                dp[y][x] += dp[y - 1][x];
            }
        }

        int answer = 0;

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                int durability = board[i][j] + dp[i][j];
                if (durability > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
