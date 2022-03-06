import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int N, M;
    public static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        parent = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
        }

        int answer = 0;

        for (int loop = 0; loop < M; ++loop) {
            String[] numbers = br.readLine().split(" ");
            int n1 = Integer.parseInt(numbers[0]);
            int n2 = Integer.parseInt(numbers[1]);

            int minv = Math.min(n1,n2);
            int maxv = Math.max(n1,n2);

            int component1 = find(minv);
            int component2 = find(maxv);

            if (component1 == component2) {
                answer = loop + 1;
                break;
            } else {
                union(component1, component2);
            }
        }

        System.out.println(answer);
    }

    public static void union(int node1, int node2) {
        parent[node2] = node1;
    }

    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        }

        return parent[node] = find(parent[node]);
    }
}
