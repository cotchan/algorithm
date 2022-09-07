import java.io.*;
import java.util.*;

public class Main {

    static int N, D, K, C;
    static int[] rices, types;
    static HashSet<Integer> typeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] ndkc = br.readLine().split(" ");

        N = Integer.parseInt(ndkc[0]);
        D = Integer.parseInt(ndkc[1]);
        K = Integer.parseInt(ndkc[2]);
        C = Integer.parseInt(ndkc[3]);

        rices = new int[N];
        types = new int[D+1];

        for (int i = 0; i < N; ++i) {
            int number = Integer.parseInt(br.readLine());
            rices[i] = number;
        }

        if (N == K) {
            System.out.println(K);
            return;
        }

        int st = 0;
        int en = K-1;
        int typeCnt = 0;

        for (int i = 0; i < K; ++i) {
            int choBab = rices[i];
            if (types[choBab] == 0) {
                typeSet.add(choBab);
                typeCnt++;
            }

            types[choBab]++;
        }

        typeCnt = typeSet.size();

        for (st = 0; st < N; ++st) {
            //st는 제거될 초밥
            int removedChobab = rices[st];
            types[removedChobab]--;

            if (types[removedChobab] == 0) {
                typeSet.remove(removedChobab);
            }

            //새로 추가될 초밥
            en = (en + 1) % N;

            int newChobab = rices[en];
            types[newChobab]++;

            if (types[newChobab] == 1) {
                typeSet.add(newChobab);
            }

            int candidate = types[C] == 0 ? typeSet.size() + 1 : typeSet.size();
            typeCnt = Math.max(typeCnt, candidate);
        }

        System.out.println(typeCnt);
    }
}
