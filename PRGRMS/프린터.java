import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static int[] myPriorities, priorityCnt;

    public int solution(int[] priorities, int location) {
        int answer = 0;

        myPriorities = new int[priorities.length];
        priorityCnt = new int[10];

        Queue<Pair> que = new LinkedList<>();

        for (int idx = 0; idx < priorities.length; ++idx) {
            int priority = priorities[idx];
            priorityCnt[priority]++;
            myPriorities[idx] = priority;
            que.add(new Pair(idx, priority));
        }

        while (!que.isEmpty()) {
            Pair now = que.poll();

            int nowLocation = now.first;
            int nowPriority = now.second;

            int maxPriority = getMaxPriority();

            if (nowPriority == maxPriority) {
                answer++;
                priorityCnt[nowPriority]--;

                if (nowLocation == location) {
                    return answer;
                }
            } else {
                que.add(now);
            }
        }

        return answer;
    }

    public int getMaxPriority() {
        for (int priority = 9; priority > 0; --priority) {
            if (priorityCnt[priority] > 0) {
                return priority;
            }
        }
        return -1;
    }
}
