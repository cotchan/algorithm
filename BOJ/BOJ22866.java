
import java.io.*;
import java.util.*;


public class Main {

    static class Pair {
        int idx, h;

        public Pair(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }

    static int N;
    static int[] numbers, viewCount;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        String[] nInfo = br.readLine().split(" ");
        numbers = new int[N];
        viewCount = new int[N];
        adj = new List[N];
        for (int i = 0; i < N; ++i) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(nInfo[i]);
            numbers[i] = number;
        }

        //왼쪽 구하기
        Stack<Pair> stk = new Stack<>();

        for (int i = 0; i < N; ++i) {
            int nowHeight = numbers[i];

            //나보다 높이 작은 애들 전부 제거
            while (!stk.isEmpty() && stk.peek().h <= nowHeight) {
                stk.pop();
            }

            //stk.peek().h > nowHeight
            int leftViewCount = stk.size();
            viewCount[i] += leftViewCount;

            if (!stk.isEmpty()) {
                Pair leftAdj = stk.peek();
                adj[i].add(leftAdj.idx);
            }

            stk.add(new Pair(i, numbers[i]));
        }

        //오른쪽 구하기
        stk = new Stack<>();

        for (int i = N-1; i >= 0; --i) {
            int nowHeight = numbers[i];

            //나보다 높이 작은 애들 전부 제거
            while (!stk.isEmpty() && stk.peek().h <= nowHeight) {
                stk.pop();
            }

            //stk.peek().h > nowHeight
            int rightViewCount = stk.size();
            viewCount[i] += rightViewCount;

            if (!stk.isEmpty()) {
                Pair rightAdj = stk.peek();
                adj[i].add(rightAdj.idx);
            }

            stk.add(new Pair(i, numbers[i]));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            if (viewCount[i] == 0) {
                sb.append(0).append('\n');
            } else {
                sb.append(viewCount[i]).append(" ");

                if (adj[i].size() == 2) {
                    int leftDist = Math.abs(i - adj[i].get(0));
                    int rightDist = Math.abs(i - adj[i].get(1));

                    if (leftDist == rightDist) {
                        sb.append(adj[i].get(0) + 1);
                    } else if (leftDist > rightDist) {
                        sb.append(adj[i].get(1) + 1);
                    } else {
                        sb.append(adj[i].get(0) + 1);
                    }

                    sb.append('\n');

                } else if (adj[i].size() == 1) {
                    sb.append(adj[i].get(0) + 1).append('\n');
                }
            }
        }

        System.out.println(sb);
    }
}
