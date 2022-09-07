import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static HashSet<String> keywordSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] nm = br.readLine().split(" ");

        N = Integer.parseInt(nm[0]);
        M = Integer.parseInt(nm[1]);

        for (int i = 0; i < N; ++i) {
            String keyword = br.readLine();
            keywordSet.add(keyword);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; ++i) {
            String[] keywords = br.readLine().split(",");
            for (int j = 0; j < keywords.length; ++j) {
                String keyword = keywords[j];
                keywordSet.remove(keyword);
            }

            sb.append(keywordSet.size()).append("\n");
        }

        System.out.println(sb);
    }
}
