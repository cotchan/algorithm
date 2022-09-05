import java.util.*;

class Solution {

    static class Pair implements Comparable<Pair> {
        String start, end;

        public Pair(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair p) {
            if (start.equals(p.start)) {
                return end.compareTo(p.end);
            } else return start.compareTo(p.start);
        }
    }

    static boolean find;
    static int N;
    static List<Pair> ticketList = new ArrayList<>();
    static LinkedList<String> visitList = new LinkedList<>();
    static String[] ans;
    static boolean[] visited;

    public String[] solution(String[][] tickets) {
        N = tickets.length;
        visited = new boolean[N];

        for (String[] ticket : tickets) {
            String start = ticket[0];
            String end = ticket[1];
            ticketList.add(new Pair(start, end));
        }

        Collections.sort(ticketList);

        visitList.addLast("ICN");
        for (int i = 0; i < N; ++i) {
            if (find) break;
            Pair ticket = ticketList.get(i);
            if (!ticket.start.equals("ICN")) continue;

            Arrays.fill(visited, false);
            visited[i] = true;
            dfs(i, 1);
        }

        String[] answer = ans;
        return answer;
    }

    static void dfs(int ticketIdx, int visitCount) {
        if (find) {
            return;
        }

        if (visitCount == N) {
            String finish = ticketList.get(ticketIdx).end;
            visitList.addLast(finish);
            find = true;
            ans = visitList.toArray(new String[N]);
            return;
        }

        String start = ticketList.get(ticketIdx).end;

        for (int nxt = 0; nxt < N; ++nxt) {
            if (nxt == ticketIdx) continue;
            if (visited[nxt]) continue;

            String next = ticketList.get(nxt).start;
            if (!start.equals(next)) continue;

            visited[nxt] = true;
            visitList.addLast(next);
            dfs(nxt, visitCount+1);
            visitList.removeLast();
            visited[nxt] = false;
        }
    }
}
