import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;


public class Main {

    public static class Tuple {
        int[][] arr;
        int count;

        public Tuple(int[][] arr, int count) {
            this.arr = arr;
            this.count = count;
        }
    }

    public static final int[][] reverseMap = {{0,3,6},{1,4,7},{2,5,8},{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6}};
    public static final int FRONT = 1;
    public static final int BACK = -1;
    public static Set<Integer> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < testCase; ++loop) {
            int[][] board = new int[3][3];
            visited.clear();

            for (int i = 0; i < 3; ++i) {
                String[] rows = br.readLine().split(" ");
                for (int j = 0; j < 3; ++j) {
                    board[i][j] = rows[j].equals("H") ? FRONT : BACK;
                }
            }

            System.out.println(bfs(board));
        }
    }

    public static int bfs(int[][] board) {
        Queue<Tuple> que = new LinkedList<>();
        que.add(new Tuple(board, 0));

        int nowState = getState(board);
        visited.add(nowState);

        while (!que.isEmpty()) {
            Tuple now = que.poll();

            if (isAllSame(now.arr)) {
                return now.count;
            }

            for (int i = 0; i < reverseMap.length; ++i) {
                int[][] nxtBoard = new int[3][3];

                for (int y = 0; y < 3; ++y) {
                    for (int x = 0; x < 3; ++x) {
                        nxtBoard[y][x] = now.arr[y][x];
                    }
                }

                for (int nxtIdx : reverseMap[i]) {
                    int[] nxt = idxToPosition(nxtIdx);
                    int ny = nxt[0];
                    int nx = nxt[1];
                    reverse(nxtBoard, ny,nx);
                }

                int nxtState = getState(nxtBoard);

                if (!visited.contains(nxtState)) {
                    visited.add(nxtState);
                    que.add(new Tuple(nxtBoard, now.count + 1));
                }
            }
        }

        return -1;
    }

    public static boolean isAllSame(int[][] board) {
        int value = board[0][0];
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static int[] idxToPosition(int idx) {
        final int y = idx / 3;
        final int x = idx % 3;
        return new int[]{y, x};
    }

    public static int getState(int[][] board) {
        int result = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (board[i][j] == FRONT) {
                    int offset = (i * 3) + j;
                    result = (result | (1 << offset));
                }
            }
        }
        return result;
    }

    public static void reverse(int[][] nowBoard, int y, int x) {
        nowBoard[y][x] = -nowBoard[y][x];
    }
}
