
import java.io.*;
import java.util.*;


public class Main {

    static int N, M;
    static int[] parents;
    static List<Integer>[] friends, enemies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N];

        for (int i = 0; i < N; ++i) {
            parents[i] = i;
        }

        friends = new List[N];
        enemies = new List[N];

        for (int i = 0; i < N; ++i) {
            friends[i] = new ArrayList<>();
            enemies[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            String[] relationInfo = br.readLine().split(" ");
            char type = relationInfo[0].charAt(0);
            int a = Integer.parseInt(relationInfo[1]);
            int b = Integer.parseInt(relationInfo[2]);

            a--;
            b--;

            if (type == 'E') {
                enemies[a].add(b);
                enemies[b].add(a);
            } else {
                friends[a].add(b);
                friends[b].add(a);
            }
        }

        for (int i = 0; i < N; ++i) {
            //친구
            for (int adj : friends[i]) {
                int d1 = find(i);
                int d2 = find(adj);
                union(d1, d2);

                //친구의 친구
                for (int newFriend : friends[adj]) {
                    int c1 = find(i);
                    int c2 = find(newFriend);
                    union(c1, c2);
                }
            }

            //원수
            for (int adj : enemies[i]) {
                //원수의 원수
                for (int newFriend : enemies[adj]) {
                    int c1 = find(i);
                    int c2 = find(newFriend);
                    union(c1, c2);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (parents[i] == i) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static void union(int a, int b) {
        int minv = Math.min(a, b);
        int maxv = Math.max(a, b);
        parents[maxv] = minv;
    }

    static int find(int node) {
        if (parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }
}
