import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first,seocnd;
        Pair(int f, int s) {
            first = f;
            seocnd = s;
        }
    }

    public static final int BOARD_SIZE = 8;
    public static final int EMPTY = 0;
    public static final int KING = 1;
    public static final int STONE = 2;

    public static int ky = 0;
    public static int kx = 0;
    public static int sy = 0;
    public static int sx = 0;
    public static int N;
    public static int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

    public static boolean isSafe(int y, int x) {
        return (0 <= y && y < BOARD_SIZE && 0 <= x && x < BOARD_SIZE);
    }

    public static int getColumnPos(char pos) {
        return pos - 'A';
    }

    public static int getRowPos(char pos) {
        return BOARD_SIZE - (pos - '0');
    }

    public static Pair getNextPosition(String dir, int y, int x) {
        if (dir.compareTo("R") == 0) {
            return new Pair(y, x + 1);
        } else if (dir.compareTo("L") == 0) {
            return new Pair(y, x - 1);
        } else if (dir.compareTo("B") == 0) {
            return new Pair(y + 1, x);
        } else if (dir.compareTo("T") == 0) {
            return new Pair(y - 1, x);
        } else if (dir.compareTo("RT") == 0) {
            return new Pair(y - 1, x + 1);
        } else if (dir.compareTo("LT") == 0) {
            return new Pair(y - 1, x - 1);
        } else if (dir.compareTo("RB") == 0) {
            return new Pair(y + 1, x + 1);
        } else {
            return new Pair(y + 1, x - 1);
        }
    }

    public static String parsePosition(int y, int x) {
        StringBuilder sb = new StringBuilder();

        int yString = (BOARD_SIZE - y);
        char xString = (char)(x + 65);
        sb.append(xString);
        sb.append(yString);
        return sb.toString();
    }

    //BOJ1063
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] info = br.readLine().split(" ");

        //킹의 위치, 돌의 위치, 움직이는 횟수N
        String kingPos = info[0];
        String stonePos = info[1];
        N = Integer.parseInt(info[2]);

        ky = getRowPos(kingPos.charAt(1));
        kx = getColumnPos(kingPos.charAt(0));
        sy = getRowPos(stonePos.charAt(1));
        sx = getColumnPos(stonePos.charAt(0));

        board[ky][kx] = KING;
        board[sy][sx] = STONE;

        for (int loop = 0; loop < N; ++loop) {

            String dir = br.readLine();

            Pair nxtKingPos = getNextPosition(dir, ky, kx);
            int nxtKy = nxtKingPos.first;
            int nxtKx = nxtKingPos.seocnd;

            if (!isSafe(nxtKy, nxtKx)) {
                continue;
            } else {
                //돌이거나, 빈 칸이거나
                if (board[nxtKy][nxtKx] == EMPTY) {
                    board[nxtKy][nxtKx] = KING;
                    board[ky][kx] = EMPTY;
                    ky = nxtKy;
                    kx = nxtKx;
                } else {
                    Pair nxtStonePos = getNextPosition(dir, nxtKy, nxtKx);
                    int nxtSy = nxtStonePos.first;
                    int nxtSx = nxtStonePos.seocnd;

                    if (!isSafe(nxtSy, nxtSx)) {
                        continue;
                    } else {
                        board[nxtSy][nxtSx] = STONE;
                        board[nxtKy][nxtKx] = KING;
                        board[ky][kx] = EMPTY;

                        sy = nxtSy;
                        sx = nxtSx;
                        ky = nxtKy;
                        kx = nxtKx;
                    }
                }
            }
        }

        System.out.println(parsePosition(ky, kx));
        System.out.println(parsePosition(sy, sx));
    }
}
