import java.io.*;
import java.util.*;

public class Main {

    static class Tuple implements Comparable<Tuple> {
        int score, fiveRank, teamNumber;

        public Tuple(int score, int fiveRank, int teamNumber) {
            this.score = score;
            this.fiveRank = fiveRank;
            this.teamNumber = teamNumber;
        }

        @Override
        public int compareTo(Tuple o) {
            if (score != o.score) {
                return score - o.score;
            }

            return fiveRank - o.fiveRank;
        }
    }

    static final int TEAM_SIZE_MAX = 201;

    static int N;
    static int[] members, scores, memberCounts, fiveRanks;
    static HashMap<Integer, Integer> hashMap = new HashMap<>();
    static ArrayList<Tuple> tuples = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int t = 0; t < N; ++t) {
            int member = Integer.parseInt(br.readLine());
            scores = new int[TEAM_SIZE_MAX];
            memberCounts = new int[TEAM_SIZE_MAX];
            fiveRanks = new int[TEAM_SIZE_MAX];
            tuples.clear();
            hashMap.clear();

            String[] memberString = br.readLine().split(" ");
            members = Arrays.stream(memberString).mapToInt(Integer::new).toArray();

            for (int i = 0; i < members.length; ++i) {
                int teamNumber = members[i];
                hashMap.put(teamNumber, hashMap.getOrDefault(teamNumber, 0) + 1);
            }

            ArrayList<Integer> realRank = new ArrayList<>();

            for (int i = 0; i < members.length; ++i) {
                int teamNumber = members[i];
                int teamSize = hashMap.get(teamNumber);
                if (teamSize != 6) continue;
                realRank.add(teamNumber);
            }

            for (int i = 0; i < realRank.size(); ++i) {
                int teamNumber = realRank.get(i);
                int score = i + 1;
                int rankOfTeam = ++memberCounts[teamNumber];

                if (rankOfTeam == 5) {
                    fiveRanks[teamNumber] = score;
                }

                if (rankOfTeam <= 4) {
                    scores[teamNumber] += score;
                }
            }

            for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
                if (entry.getValue() != 6) continue;

                int teamNumber = entry.getKey();
                int totalScore = scores[teamNumber];
                int fiveRank = fiveRanks[teamNumber];
                tuples.add(new Tuple(totalScore, fiveRank, teamNumber));
            }

            Collections.sort(tuples);

            System.out.println(tuples.get(0).teamNumber);
        }
    }

}
