package prgrms;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Solution {

    public static int N;
    public static List<Integer>[] graph;

    public int solution(int n, int[][] edge) {

        N = n;
        graph = new List[N];

        for (int i = 0; i < N; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edgeInfo : edge) {
            int src = edgeInfo[0];
            int dst = edgeInfo[1];

            src--; dst--;

            graph[src].add(dst);
            graph[dst].add(src);
        }

        List<Integer> dists = dijkstra(0);

        int maxv = Collections.max(dists);
        int answer = 0;

        for (int dist : dists) {
            if (dist == maxv) {
                answer++;
            }
        }

        return answer;
    }

    private List<Integer> dijkstra(int src) {

        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        Queue<Integer[]> que = new LinkedList<>();
        dist[src] = 0;
        que.add(new Integer[]{src, 0});

        while (!que.isEmpty()) {
            Integer[] now = que.poll();

            int nowNode = now[0];
            int nowDist = now[1];

            for (int nxt : graph[nowNode]) {
                if (dist[nxt] == Integer.MAX_VALUE) {
                    dist[nxt] = nowDist + 1;
                    que.add(new Integer[]{nxt, nowDist + 1});
                }
            }
        }

        Integer[] boxed = Arrays.stream(dist).boxed().toArray(Integer[]::new);
        return Stream.of(boxed).collect(Collectors.toList());
    }
}
