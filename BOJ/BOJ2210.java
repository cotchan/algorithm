import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;


public class Main {

    public static final int BOARD_SIZE = 5;
    public static final int[] dy = {-1,1,0,0};
    public static final int[] dx = {0,0,-1,1};
    public static int[][] board;
    public static Set<String> numbers = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        board = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; ++i) {
            String[] rowInfo = br.readLine().split(" ");

            for (int j = 0; j < BOARD_SIZE; ++j) {
                char c = rowInfo[j].charAt(0);
                int num = c - '0';
                board[i][j] = num;
            }
        }

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                dfs(i, j, 1, "" + board[i][j]);
            }
        }

        System.out.println(numbers.size());
    }

    public static void dfs(int y, int x, int depth, String number) {
        if (depth == 6) {
            numbers.add(number);
            return;
        }

        for (int dir = 0; dir < 4; ++dir) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (isSafe(ny, nx)) {
                int nowNumber = board[ny][nx];
                String nxtNumber = number + nowNumber;
                dfs(ny, nx, depth + 1, nxtNumber);
            }
        }
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < BOARD_SIZE && 0 <= x && x < BOARD_SIZE;
    }
}
