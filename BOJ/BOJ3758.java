import java.io.*;
import java.util.*;

public class Main {

    static class Tuple implements Comparable<Tuple> {
        int score, sendCount, lastSendTime, teamId;

        public Tuple(int score, int sendCount, int lastSendTime, int teamId) {
            this.score = score;
            this.sendCount = sendCount;
            this.lastSendTime = lastSendTime;
            this.teamId = teamId;
        }

        @Override
        public int compareTo(Tuple t) {
            if (score == t.score) {
                if (sendCount == t.sendCount) return lastSendTime - t.lastSendTime;
                return sendCount - t.sendCount;
            }
            return t.score - score;
        }
    }

    static HashSet<Integer> teamIds = new HashSet<>();
    static HashMap<Integer, Integer> lastSendTimes = new HashMap<>();
    static HashMap<Integer, Integer> totalSendCounts = new HashMap<>();
    static HashMap<String, Integer> scoreMap = new HashMap<>();
    static HashMap<Integer, Integer> totalScoreMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; ++t) {
            teamIds.clear();
            lastSendTimes.clear();
            totalSendCounts.clear();
            scoreMap.clear();
            totalScoreMap.clear();

            String[] nkIdm = br.readLine().split(" ");

            int n = Integer.parseInt(nkIdm[0]);
            int k = Integer.parseInt(nkIdm[1]);
            int id = Integer.parseInt(nkIdm[2]);
            int m = Integer.parseInt(nkIdm[3]);

            for (int loop = 0; loop < m; ++loop) {
                String[] logString = br.readLine().split(" ");
                int teamId = Integer.parseInt(logString[0]);
                int problemNumber = Integer.parseInt(logString[1]);
                int score = Integer.parseInt(logString[2]);

                teamIds.add(teamId);
                String key = toKey(teamId, problemNumber);
                int beforeScore = scoreMap.getOrDefault(key, 0);
                scoreMap.put(key, Math.max(score, beforeScore));
                lastSendTimes.put(teamId, loop);
                totalSendCounts.put(teamId, totalSendCounts.getOrDefault(teamId, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : scoreMap.entrySet()) {
                String[] split = entry.getKey().split("-");
                int teamId = Integer.parseInt(split[0]);
                int score = entry.getValue();
                totalScoreMap.put(teamId, totalScoreMap.getOrDefault(teamId, 0) + score);
            }

            ArrayList<Tuple> list = new ArrayList<>();

            for (int teamId : teamIds) {
                int totalScore = totalScoreMap.get(teamId);
                int totalSendCount = totalSendCounts.get(teamId);
                int lastSendTime = lastSendTimes.get(teamId);
                list.add(new Tuple(totalScore, totalSendCount, lastSendTime, teamId));
            }

            Collections.sort(list);

            for (int i = 0; i < list.size(); ++i) {
                Tuple now = list.get(i);
                if (now.teamId == id) {
                    System.out.println(i+1);
                }
            }
        }
    }

    static String toKey(int teamId, int problemNumber) {
        return teamId + "-" + problemNumber;
    }
}
