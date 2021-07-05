package com.company;

import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        char first;
        int second;

        Pair(char c, int s) {
            first = c;
            second = s;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String explode = br.readLine();

        Stack<Pair> stk = new Stack<>();

        int searchIdx = 0;

        for (int idx = 0; idx < target.length(); ++idx) {

            char now = target.charAt(idx);

            if (now == explode.charAt(searchIdx)) {
                searchIdx++;
            } else if (now == explode.charAt(0)) {
                searchIdx = 1;
            } else {
                searchIdx = 0;
            }

            stk.push(new Pair(now, searchIdx));

            if (searchIdx == explode.length()) {
                for (int loop = 0; loop < explode.length(); ++loop) {
                    stk.pop();
                }

                if (!stk.isEmpty()) {
                    searchIdx = stk.peek().second;
                } else {
                    searchIdx = 0;
                }
            }
        }

        StringBuilder sb = new StringBuilder();

        if (!stk.isEmpty()) {
            while (!stk.isEmpty()) {
                char c = stk.pop().first;
                sb.append(c);
            }

            System.out.println(sb.reverse().toString());

        } else {
            System.out.println("FRULA");
        }
    }
}
