import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    static class Pair {
        int weight, time;

        public Pair(int weight, int time) {
            this.weight = weight;
            this.time = time;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Deque<Pair> q = new LinkedList<>();

        int time = 1;
        int totalWeight = 0;

        for (int truckWeight : truck_weights) {
            while (!q.isEmpty() && (weight - totalWeight < truckWeight)) {
                Pair now = q.poll();
                int restTime = bridge_length - (time - now.time);

                if (restTime > 0) {
                    time += restTime;
                }

                totalWeight -= now.weight;
            }

            q.add(new Pair(truckWeight, time));
            totalWeight += truckWeight;

            time++;
        }

        int answer = q.peekLast().time + bridge_length;
        return answer;
    }
}
