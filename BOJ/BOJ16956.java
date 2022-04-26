import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static final int EMPTY = 0;
    public static final int SHEEP = 1;
    public static final int WOLF = 2;
    public static final int WALL = 3;
    public static final int[] dy = {-1,1,0,0};
    public static final int[] dx = {0,0,-1,1};

    public static int R, C;
    public static int[][] board;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rcInfo = br.readLine().split(" ");

        R = Integer.parseInt(rcInfo[0]);
        C = Integer.parseInt(rcInfo[1]);

        board = new int[R][C];

        for (int i = 0; i < R; ++i) {
            String row = br.readLine();

            for (int j = 0; j < C; ++j) {
                char c = row.charAt(j);

                if (c == '.') {
                    board[i][j] = EMPTY;
                } else if (c == 'S') {
                    board[i][j] = SHEEP;
                } else {
                    board[i][j] = WOLF;
                }
            }
        }

        boolean isAdjacent = false;

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (board[i][j] == WOLF) {
                    for (int dir = 0; dir < 4; ++dir) {
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];

                        if (isSafe(ny, nx) && board[ny][nx] == SHEEP) {
                            isAdjacent = true;
                        }
                    }
                }
            }
        }

        if (isAdjacent) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (board[i][j] == SHEEP) {
                    for (int dir = 0; dir < 4; ++dir) {
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];

                        if (isSafe(ny,nx) && board[ny][nx] == EMPTY) {
                            board[ny][nx] = WALL;
                        }
                    }
                }
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append("1\n");

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (board[i][j] == EMPTY) {
                    answer.append('.');
                } else if (board[i][j] == SHEEP) {
                    answer.append('S');
                } else if (board[i][j] == WOLF) {
                    answer.append('W');
                } else {
                    answer.append('D');
                }
            }
            answer.append("\n");
        }

        System.out.println(answer);
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }
}
