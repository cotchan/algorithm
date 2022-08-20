import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y,x;

        public Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static final int[] dy = {0,0,1,-1,1,1,-1,-1};
    static final int[] dx = {1,-1,0,0,1,-1,1,-1};
    static int R, C;
    static int[][] board;
    static int[] parents;
    static Pair[] position;
    static List<int[]> tuples = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] rcInfo = br.readLine().split(" ");

        R = Integer.parseInt(rcInfo[0]);
        C = Integer.parseInt(rcInfo[1]);
        board = new int[R][C];
        position = new Pair[300001];
        parents = new int[R*C];

        for (int i = 0; i < R; ++i) {
            String[] rowInfo = br.readLine().split(" ");
            for (int j = 0; j < rowInfo.length; ++j) {
                board[i][j] = Integer.parseInt(rowInfo[j]);
                position[board[i][j]] = new Pair(i, j);
                tuples.add(new int[]{board[i][j], i, j});
            }
        }

        for (int idx = 0; idx < R * C; ++idx) {
            parents[idx] = idx;
        }

        Collections.sort(tuples, (a,b) -> {
            return a[0] - b[0];
        });

        //작은놈부터 나옴
        for (int[] tuple : tuples) {
            int now = tuple[0];
            int y = tuple[1];
            int x = tuple[2];

            int parent = move(y, x);

            if (parent != now) {
                Pair pos = position[parent];
                int parentIdx = toIdx(pos.y, pos.x);
                int nowIdx = toIdx(y, x);
                int c1 = find(parentIdx);
                int c2 = find(nowIdx);
                union(c1, c2);
            }
        }

        int[][] result = new int[R+1][C+1];

        for (int p : parents) {
            int y = p / C;
            int x = p % C;
            result[y][x]++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int toIdx(int y, int x) {
        return (y * C) + x;
    }

    static int find(int idx) {
        if (parents[idx] == idx) return idx;
        else return parents[idx] = find(parents[idx]);
    }

    static void union(int c1, int c2) {
        parents[c2] = c1;
    }

    static int move(int y, int x) {
        int minv = board[y][x];

        for (int dir = 0; dir < 8; ++dir) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];
            if (!safe(ny,nx)) continue;

            if (board[ny][nx] < minv) {
                minv = board[ny][nx];
            }
        }

        return minv;
    }

    static boolean safe(int y, int x) {
        return 0 <= y && y < R && 0 <= x && x < C;
    }

}
