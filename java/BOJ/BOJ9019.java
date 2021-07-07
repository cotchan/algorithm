package com.company;

import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        int number;
        char operation;

        Pair(int n, char o) {
            number = n;
            operation = o;
        }

    }

    public static final int MOD= 10000;
    public static final int MAX_NUMBER = 9999;
    public static final int MIN_NUMBER = 0;

    public static int calculateL(int number) {
        int tail = number / 1000;
        int rest = number % 1000;
        return rest * 10 + tail;
    }

    public static int calculateR(int number) {
        int head = number % 10;
        int rest = number / 10;
        return head * 1000 + rest;
    }

    public static int calculateS(int number) {
        if (number == MIN_NUMBER) {
            return MAX_NUMBER;
        } else {
            return number - 1;
        }
    }

    public static int calculateD(int number) {
        int nxtNumber = number * 2;
        if (nxtNumber > MAX_NUMBER) {
            return nxtNumber % MOD;
        } else {
            return nxtNumber;
        }
    }

    public static String bfs(int src, int dst) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[10000];
        Map<Integer, Pair> stateInfo = new HashMap<>();

        visited[src] = true;
        q.add(src);

        while (!q.isEmpty()) {
            int now = q.poll();

            if (now == dst) {
                break;
            }

            int Dresult = calculateD(now);
            int Sresult = calculateS(now);
            int Lresult = calculateL(now);
            int Rresult = calculateR(now);

            if (!visited[Dresult]) {
                visited[Dresult] = true;
                q.add(Dresult);
                stateInfo.put(Dresult, new Pair(now, 'D'));
            }

            if (!visited[Sresult]) {
                visited[Sresult] = true;
                q.add(Sresult);
                stateInfo.put(Sresult, new Pair(now, 'S'));
            }

            if (!visited[Lresult]) {
                visited[Lresult] = true;
                q.add(Lresult);
                stateInfo.put(Lresult, new Pair(now, 'L'));
            }

            if (!visited[Rresult]) {
                visited[Rresult] = true;
                q.add(Rresult);
                stateInfo.put(Rresult, new Pair(now, 'R'));
            }
        }

        Pair before = stateInfo.get(dst);

        StringBuilder sb = new StringBuilder();

        while (before.number != src) {
            int nxtNumber = before.number;
            sb.append(before.operation);
            before = stateInfo.get(nxtNumber);
        }

        sb.append(before.operation);

        return sb.reverse().toString();
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int loop = 0; loop < t; ++loop) {
            String[] numbers = br.readLine().split(" ");

            int src = Integer.parseInt(numbers[0]);
            int dst = Integer.parseInt(numbers[1]);

            bw.write(bfs(src, dst));
            bw.newLine();
        }

        bw.flush();
    }
}
