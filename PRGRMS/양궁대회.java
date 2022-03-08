import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {

    static class Pair implements Comparable<Pair> {
        int[] shootInfo;
        long weight;

        public Pair(int[] shootInfo, long weight) {
            this.shootInfo = shootInfo;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            if (weight > o.weight) {
                return -1;
            } else if (weight == o.weight) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static int shootSize;
    public static final int SCORE_SIZE = 11;
    public static int[] peachShootInfo;
    public static boolean[] isShooted = new boolean[SCORE_SIZE];
    public static LinkedList<Integer[]> candidates = new LinkedList<>();
    public static LinkedList<Integer> candidate = new LinkedList<>();
    public static PriorityQueue<Pair> pq = new PriorityQueue<>();

    public int[] solution(int n, int[] info) {

        int maxDiff = -1;
        shootSize = n;
        peachShootInfo = info;

        for (int pickCnt = 1; pickCnt <= n; ++pickCnt) {
            combination(pickCnt, 0, 0);

            for (Integer[] scoreCandidate : candidates) {

                //n번 뽑은 결과가 들어있음
                int lionTotalShootSize = 0;
                int[] lionShootInfo = new int[SCORE_SIZE];

                for (int targetIdx : scoreCandidate) {
                    int peachShootSize = peachShootInfo[targetIdx];
                    int lionShootSize = peachShootSize + 1;

                    if (shootSize >= lionTotalShootSize + lionShootSize) {
                        lionShootInfo[targetIdx] = lionShootSize;
                        lionTotalShootSize += lionShootSize;
                    }
                }

                if (shootSize > lionTotalShootSize) {
                    int lastIdx = scoreCandidate[scoreCandidate.length - 1];
                    int restShootSize = shootSize - lionTotalShootSize;
                    lionShootInfo[lastIdx] += restShootSize;
                }

                int scoreDifference = getScoreDifference(lionShootInfo);

                if (scoreDifference < 0) {
                    continue;
                }

                if (scoreDifference > maxDiff) {
                    maxDiff = scoreDifference;
                    pq.clear();
                    pq.add(new Pair(lionShootInfo, getWeight(lionShootInfo)));
                } else if (scoreDifference == maxDiff) {
                    pq.add(new Pair(lionShootInfo, getWeight(lionShootInfo)));
                }
            }

            candidates.clear();
            candidate.clear();
        }

        if (pq.isEmpty() || maxDiff == 0) {
            return new int[]{-1};
        } else {
            return pq.poll().shootInfo;
        }
    }

    public int getScore(int idx) {
        return 10 - idx;
    }

    public int getScoreDifference(int[] lionShootInfo) {

        int peachScore = 0;
        int lionScore = 0;

        for (int i = 0; i < SCORE_SIZE; ++i) {
            int peachShootSize = peachShootInfo[i];
            int lionShootSize = lionShootInfo[i];

            if (peachShootSize >= lionShootSize) {
                peachScore += peachShootSize == 0 ? 0 : getScore(i);
            } else {
                lionScore += lionShootSize == 0 ? 0 : getScore(i);
            }
        }

        return lionScore - peachScore;
    }

    public static void combination(int n, int idx, int pickCnt) {
        if (pickCnt == n) {
            Integer[] result = candidate.toArray(new Integer[candidate.size()]);
            candidates.add(result);
            return;
        }

        if (idx == SCORE_SIZE) {
            return;
        }

        if (!isShooted[idx]) {
            isShooted[idx] = true;
            candidate.addLast(idx);
            combination(n, idx + 1, pickCnt + 1);
            candidate.removeLast();
            isShooted[idx] = false;
        }

        combination(n, idx + 1, pickCnt);
    }

    public static long getWeight(int[] shootInfo) {
        long result = 0;

        for (int idx = 0; idx < SCORE_SIZE; ++idx) {
            if (shootInfo[idx] > 0) {
                long weight = (long)Math.pow(10, idx);
                result += (11 * weight);
            }
        }

        return result;
    }
}
