import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int first, second;
        Pair (int f, int s) {
            first = f;
            second = s;
        }
    }

    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String[] persons = br.readLine().split(" ");

        int target1, target2;
        target1 = Integer.parseInt(persons[0]);
        target2 = Integer.parseInt(persons[1]);
        target1--; target2--;

        int conn = Integer.parseInt(br.readLine());

        graph = new List[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new LinkedList<>();
        }

        for (int loop = 0; loop < conn; ++loop) {
            String[] info = br.readLine().split(" ");

            int p1 = Integer.parseInt(info[0]);
            int p2 = Integer.parseInt(info[1]);
            p1--; p2--;

            graph[p1].add(p2);
            graph[p2].add(p1);
        }

        //bfs
        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[n];

        q.add(new Pair(target1, 0));
        visited[target1] = true;

        int answer = -1;

        while (!q.isEmpty()) {

            Pair now = q.poll();

            if (now.first == target2) {
                answer = now.second;
                break;
            }

            for (int nxt : graph[now.first]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    q.add(new Pair(nxt, now.second + 1));
                }
            }
        }

        System.out.println(answer);
    }
}
