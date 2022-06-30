
import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] dp, chingChans;
    static List<Integer>[] graph;

    /**
     * 점화식
     * dp[i]: i번쨰 직원이 받은 칭찬 = sum(상사가 받은 칭찬) + 내가 받은 칭찬
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");
        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        dp = new int[N];
        chingChans = new int[N];
        graph = new List[N];
        Arrays.fill(dp, -1);

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        String[] personInfo = br.readLine().split(" ");
        int[] info = Arrays.stream(personInfo).mapToInt(Integer::new).toArray();

        for (int i = 0; i < info.length; ++i) {
            int sangSa = info[i];
            if (sangSa == -1) continue;
            graph[i].add(sangSa-1);
        }

        for (int loop = 0; loop < M; ++loop) {
            String[] iwInfo = br.readLine().split(" ");
            int i = Integer.parseInt(iwInfo[0]);
            int w = Integer.parseInt(iwInfo[1]);
            chingChans[i-1] += w;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; ++i) {
            sb.append(dfs(i)).append(" ");
        }

        System.out.println(sb);
    }

    static int dfs(int node) {
        if (dp[node] != -1) {
            return dp[node];
        }

        int result = chingChans[node];

        //with adj
        for (int adj : graph[node]) {
            result += dfs(adj);
        }

        return dp[node] = result;
    }

}
