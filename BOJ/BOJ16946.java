import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        //node index, node total count
        int idx,cnt;

        public Pair(int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
        }
    }

    public static final int dy[] = {-1,1,0,0};
    public static final int dx[] = {0,0,-1,1};

    public static int N, M;
    public static int[][] board, ans;
    public static boolean[][] visited, visitedOfPair;
    public static Pair[][] stateBoard;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        board = new int[N][M];
        stateBoard = new Pair[N][M];
        ans = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String row = br.readLine();

            for (int j = 0; j < M; ++j) {
                int val = row.charAt(j) - '0';

                board[i][j] = val;
            }
        }

        visited = new boolean[N][M];
        visitedOfPair = new boolean[N][M];

        int componentIdx = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 0 && !visited[i][j]) {
                    visited[i][j] = true;
                    int componentSize = bfs(i, j);

                    bfsOfPair(i, j, componentIdx, componentSize);
                    componentIdx++;
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 1) {

                    int result = 1;
                    Set<Integer> componentSet = new HashSet<>();

                    for (int dir = 0; dir < 4; ++dir) {
                        int ny = i + dy[dir];
                        int nx = j + dx[dir];

                        if (isSafe(ny, nx) && board[ny][nx] == 0) {
                            int idx = stateBoard[ny][nx].idx;

                            if (!componentSet.contains(idx)) {
                                componentSet.add(idx);
                                result += stateBoard[ny][nx].cnt;
                            }
                        }
                    }

                    ans[i][j] = result % 10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                sb.append(ans[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);

    }

    public static int bfs(int y, int x) {

        Queue<Integer[]> queue = new LinkedList<>();

        int result = 1;

        queue.add(new Integer[]{y, x});

        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            for (int dir = 0; dir < 4; ++dir) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];

                if (isSafe(ny, nx) && board[ny][nx] == 0 && !visited[ny][nx]) {
                    visited[ny][nx] = true;
                    queue.add(new Integer[]{ny, nx});
                    result++;
                }
            }
        }

        return result;
    }

    public static void bfsOfPair(int y, int x, int componentIdx, int componentSize) {

        Queue<Integer[]> queue = new LinkedList<>();

        visitedOfPair[y][x] = true;
        stateBoard[y][x] = new Pair(componentIdx, componentSize);
        queue.add(new Integer[]{y, x});

        while (!queue.isEmpty()) {
            Integer[] now = queue.poll();

            for (int dir = 0; dir < 4; ++dir) {
                int ny = now[0] + dy[dir];
                int nx = now[1] + dx[dir];

                if (isSafe(ny, nx) && board[ny][nx] == 0 && !visitedOfPair[ny][nx]) {
                    visitedOfPair[ny][nx] = true;
                    stateBoard[ny][nx] = new Pair(componentIdx, componentSize);
                    queue.add(new Integer[]{ny, nx});
                }
            }
        }
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }
}
