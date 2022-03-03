package prgrms;

import java.util.*;

public class Solution {

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static final int[] dy = {-1,1,0,0};
    public static final int[] dx = {0,0,-1,1};
    public static final int PLACE_SIZE = 5;
    public static boolean[][] visited = new boolean[PLACE_SIZE][PLACE_SIZE];

    public int[] solution(String[][] places) {

        List<Integer> answerList = new LinkedList<>();

        for (String[] place : places) {

            for (int i = 0; i < PLACE_SIZE; ++i) {
                Arrays.fill(visited[i], false);
            }

            boolean result = true;

            for (int i = 0; i < PLACE_SIZE; ++i) {
                for (int j = 0; j < PLACE_SIZE; ++j) {
                    if (place[i].charAt(j) == 'P' && !visited[i][j]) {
                        visited[i][j] = true;
                        boolean adjacent = isAdjacent(place, i, j);

                        if (adjacent) {
                            result = false;
                        }
                    }
                }
            }

            answerList.add(result == true ? 1 : 0);
        }

        Integer[] answerWrapper = answerList.toArray(new Integer[PLACE_SIZE]);
        int[] answer = Arrays.stream(answerWrapper).mapToInt(Integer::intValue).toArray();

        return answer;
    }

    public static boolean isAdjacent(String[] place, int y, int x) {

        Queue<Pair> que = new LinkedList<>();
        que.add(new Pair(y, x));

        int intervieweeY = y;
        int intervieweeX = x;

        while (!que.isEmpty()) {
            Pair now = que.poll();

            for (int dir = 0; dir < 4; ++dir) {
                int ny = now.first + dy[dir];
                int nx = now.second + dx[dir];

                if (isSafe(ny, nx) && place[ny].charAt(nx) != 'X' && !visited[ny][nx]) {
                    visited[ny][nx] = true;

                    if (place[ny].charAt(nx) == 'P') {
                        int distance = getDistance(intervieweeY, intervieweeX, ny, nx);
                        if (distance <= 2) {
                            return true;
                        } else {
                            intervieweeY = ny;
                            intervieweeX = nx;
                        }
                    }

                    que.add(new Pair(ny, nx));
                }
            }
        }

        return false;
    }

    public static int getDistance(int y1, int x1, int y2, int x2) {
        return Math.abs(y1 - y2) + Math.abs(x1 - x2);
    }

    public static boolean isSafe(int y, int x) {
        return 0 <= y && y < PLACE_SIZE && 0 <= x && x < PLACE_SIZE;
    }
}
