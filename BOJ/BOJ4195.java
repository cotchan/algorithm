import java.io.*;
import java.util.*;

public class Main {

    public static final int MAX_SIZE = 1000000;
    public static int T, F, personIdx;
    public static int[] parent, componentSize;
    public static Map<String, Integer> friends = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; ++testCase) {

            personIdx = 0;

            friends.clear();

            F = Integer.parseInt(br.readLine());
            parent = new int[MAX_SIZE];
            componentSize = new int[MAX_SIZE];

            Arrays.fill(componentSize, 1);

            for (int i = 0; i < MAX_SIZE; ++i) {
                parent[i] = i;
            }

            for (int loop = 0; loop < F; ++loop) {

                String[] friendInfo = br.readLine().split(" ");

                Arrays.sort(friendInfo);

                String firstName = friendInfo[0];
                String secondName = friendInfo[1];

                if (friends.get(firstName) == null) {
                    friends.put(firstName, personIdx++);
                }

                if (friends.get(secondName) == null) {
                    friends.put(secondName, personIdx++);
                }

                int node1 = friends.get(firstName);
                int node2 = friends.get(secondName);

                int c1 = find(node1);
                int c2 = find(node2);

                if (c1 != c2) {
                    union(c1, c2);
                    componentSize[c1] += componentSize[c2];
                }

                bw.append(String.valueOf(componentSize[c1]));

                //bfs
                bw.newLine();
            }
        }

        bw.flush();
    }

    public static int find(int node) {
        if (parent[node] == node) {
            return node;
        } else {
            return parent[node] = find(parent[node]);
        }
    }

    public static void union(int node1, int node2) {
        parent[node2] = node1;
    }
}
