import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int[] dy = {-1,1,0,0};
    public static int[] dx = {0,0,-1,1};

    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int NOT_VISITED = 0;
    public static final int VISITING = 1;
    public static final int FINISHED = 2;
    public static int N, M, cycleSize;
    public static int[][] graph;
    public static int[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);
        graph = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; ++i) {
            String row = br.readLine();

            for (int j = 0; j < row.length(); ++j) {
                char dir = row.charAt(j);

                int dirValue;
                switch (dir) {
                    case 'U':
                        dirValue = UP;
                        break;
                    case 'D':
                        dirValue = DOWN;
                        break;
                    case 'L':
                        dirValue = LEFT;
                        break;
                    default:
                        dirValue = RIGHT;
                }

                graph[i][j] = dirValue;
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j] == NOT_VISITED) {
                    visited[i][j] = VISITING;
                    dfs(i, j);
                }
            }
        }

        System.out.println(cycleSize);
    }

    public static void dfs(int y, int x) {

        int direction = graph[y][x];

        int ny = y + dy[direction];
        int nx = x + dx[direction];

        //find cycle
        if (visited[ny][nx] == VISITING) {
            cycleSize++;
        } else if (visited[ny][nx] == NOT_VISITED) {
            visited[ny][nx] = VISITING;
            dfs(ny, nx);
        }

        visited[y][x] = FINISHED;
    }
}
