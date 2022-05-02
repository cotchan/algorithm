import java.io.*;
import java.util.*;


public class Main {

    public static int G, P;
    public static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        G = Integer.parseInt(br.readLine());
        P = Integer.parseInt(br.readLine());

        parents = new int[G+1];

        for (int i = 0; i < G+1; ++i) {
            parents[i] = i;
        }

        int ans = 0;

        for (int i = 0; i < P; ++i) {
            int gate = Integer.parseInt(br.readLine());

            int possibleNode = find(gate);

            if (possibleNode == 0) {
                break;
            }

            ans++;
            union(possibleNode - 1, possibleNode);
        }

        System.out.println(ans);
    }

    public static int find(int node) {
        if (parents[node] == node) {
            return node;
        }

        return parents[node] = find(parents[node]);
    }

    public static void union(int node1, int node2) {
        int component1 = find(node1);
        int component2 = find(node2);
        parents[component2] = component1;
    }

}
