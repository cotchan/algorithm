import java.io.*;
import java.util.*;


public class Main {

    public static int N, M;
    public static boolean[] isKnowTruth;
    public static boolean[][] graph;
    public static List<Integer>[] parties;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        isKnowTruth = new boolean[N];
        parties = new List[M];

        for (int i = 0; i < M; ++i) {
            parties[i] = new ArrayList<>();
        }

        graph = new boolean[N][N];

        String[] personInfo = br.readLine().split(" ");

        int personSize = Integer.parseInt(personInfo[0]);

        for (int i = 0; i < personSize; ++i) {
            int idx = Integer.parseInt(personInfo[i + 1]);
            idx--;

            isKnowTruth[idx] = true;
        }

        for (int loop = 0; loop < M; ++loop) {
            String[] partyInfo = br.readLine().split(" ");

            int participantSize = Integer.parseInt(partyInfo[0]);

            List<Integer> persons = new ArrayList<>();

            for (int i = 0; i < participantSize; ++i) {
                int idx = Integer.parseInt(partyInfo[i + 1]);
                idx--;

                persons.add(idx);
            }

            parties[loop] = persons;

            for (int i = 0; i < participantSize - 1; ++i) {
                for (int j = i + 1; j < participantSize; ++j) {
                    int p1 = persons.get(i);
                    int p2 = persons.get(j);
                    graph[p1][p2] = true;
                    graph[p2][p1] = true;
                }
            }
        }

        for (int k = 0; k < N; ++k) {
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            if (isKnowTruth[i]) {
                for (int j = 0; j < N; ++j) {
                    if (graph[i][j]) {
                        isKnowTruth[j] = true;
                    }
                }
            }
        }

        int ans = 0;
        for (List<Integer> party : parties) {

            boolean isPossible = true;

            for (int personIdx : party) {
                if (isKnowTruth[personIdx]) {
                    isPossible = false;
                    break;

                }
            }

            if (isPossible) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}
