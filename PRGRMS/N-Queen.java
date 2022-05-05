class Solution {

    public static int N, ans;
    public static int[][] board;

    public int solution(int n) {
        N = n;
        board = new int[N][N];
        solve(0);
        return ans;
    }

    public static void solve(int depth) {
        if (depth == N) {
            ans++;
            return;
        }

        for (int i = 0; i < N; ++i) {
            if (board[depth][i] == 0) {
                state(depth,i,1);
                solve(depth+1);
                state(depth,i,-1);
            }
        }
    }

    public static void state(int y, int x, int value) {
        // 가로
        for (int row = 0; row < N; ++row) {
            if (row == x) continue;
            board[y][row] += value;
        }

        // 세로
        for (int col = 0; col < N; ++col) {
            if (col == y) continue;
            board[col][x] += value;
        }

        // 대각
        int ny = y+1;
        int nx = x+1;

        while (isSafe(ny, nx)) {
            board[ny][nx] += value;

            ny += 1;
            nx += 1;
        }

        ny = y - 1;
        nx = x - 1;

        while (isSafe(ny, nx)) {
            board[ny][nx] += value;

            ny -= 1;
            nx -= 1;
        }

        // 대각
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == y && j == x) continue;
                if (i + j == y + x) {
                    board[i][j] += value;
                }
            }
        }


        board[y][x] += value;
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }
}
