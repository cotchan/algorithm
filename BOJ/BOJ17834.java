
import java.io.*;
import java.util.*;

public class Main {

    static final int NOT_VISIT = 0;
    static final int RED = 1;
    static final int BLUE = -1;
    static int N, M, redCnt, blueCnt;
    static List<Integer>[] graph;
    static int[] visit; //0: NOT_VISIT, 1: RED, -1: BLUE

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        visit = new int[N];

        graph = new List[N];
        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] edge = br.readLine().split(" ");
            int st = Integer.parseInt(edge[0]);
            int en = Integer.parseInt(edge[1]);
            st--; en--;
            graph[st].add(en);
            graph[en].add(st);
        }

        int result = dfs(0, RED);
        if (result == 0) System.out.println(redCnt * blueCnt * 2);
        else System.out.println(0);
    }

    public static int dfs(int node, int color) {
        if (visit[node] != NOT_VISIT) {
            if (visit[node] == color) return 0;
            else return 1;
        }

        visit[node] = color;

        if (color == RED) redCnt++;
        else blueCnt++;

        int result = 0;

        for (int adj : graph[node]) {
            result += dfs(adj, -color);
        }

        return result;
    }
}
