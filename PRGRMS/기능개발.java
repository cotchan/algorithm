import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        //큐에 다 집어넣기
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < progresses.length; ++i) {
            queue.add(i);
        }

        int day = 0;
        List<Integer> list = new ArrayList<>();

        while (!queue.isEmpty()) {

            int pollCnt = 0;
            int now = queue.peek();

            while (!queue.isEmpty() && isFinished(progresses[now], speeds[now], day)) {
                pollCnt++;
                queue.poll();

                if (!queue.isEmpty()) {
                    now = queue.peek();
                }
            }

            day++;

            if (pollCnt > 0) {
                list.add(pollCnt);
            }

        }

        return Arrays.stream(list.toArray(new Integer[list.size()])).mapToInt(Integer::intValue).toArray();
    }

    public boolean isFinished(int progress, int speed, int day) {
        return (progress + (speed * day)) >= 100;
    }
}
