import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static class Pair {
        String number;
        int cnt;

        public Pair(String number, int cnt) {
            this.number = number;
            this.cnt = cnt;
        }
    }

    public static int N, K;
    public static List<String> answer;
    public static Set<String> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nkInfo = br.readLine().split(" ");

        N = Integer.parseInt(nkInfo[0]);
        K = Integer.parseInt(nkInfo[1]);

        String[] numbers = br.readLine().split(" ");

        List<String> origin = Arrays.stream(numbers).collect(Collectors.toList());
        answer = Arrays.stream(numbers).collect(Collectors.toList());
        Collections.sort(answer);

        System.out.println(bfs(origin));
    }

    public static long bfs(List<String> numbers) {

        Queue<Pair> queue = new LinkedList<>();

        String initialNumbers = listToString(numbers);
        Pair initialState = new Pair(initialNumbers, 0);

        queue.add(initialState);
        visited.add(initialNumbers);

        while (!queue.isEmpty()) {
            Pair now = queue.poll();

            if (isAnswer(stringToList(now.number))) {
                return now.cnt;
            }

            for (int i = 0; i <= N - K; ++i) {
                List<String> origin = stringToList(now.number);

                List<String> head = i == 0 ? Collections.emptyList() : origin.subList(0, i);
                List<String> mid = origin.subList(i, i + K);
                List<String> tail = i + K == N ? Collections.emptyList() : origin.subList(i + K, N);
                Collections.reverse(mid);

                List<String> nxt = new ArrayList<>();
                nxt.addAll(head);
                nxt.addAll(mid);
                nxt.addAll(tail);

                String nxtNumber = listToString(nxt);

                if (!visited.contains(nxtNumber)) {
                    visited.add(nxtNumber);
                    queue.add(new Pair(nxtNumber, now.cnt + 1));
                }
            }
        }

        return -1;
    }

    public static String listToString(List<String> numbers) {
        StringBuilder sb = new StringBuilder();
        for (String number : numbers) {
            sb.append(number);
        }
        return sb.toString();
    }

    public static List<String> stringToList(String numbers) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numbers.length(); ++i) {
            result.add(String.valueOf(numbers.charAt(i)));
        }

        return result;
    }

    public static boolean isAnswer(List<String> candidate) {
        for (int i = 0; i < N; ++i) {
            if (!answer.get(i).equals(candidate.get(i))) {
                return false;
            }
        }
        return true;
    }
}
