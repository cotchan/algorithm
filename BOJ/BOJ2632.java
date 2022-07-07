
import java.io.*;

public class Main {

    static int K, N, M;
    static int[] pizzaA, pizzaB, sizeA, sizeB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());

        String[] nmInfo = br.readLine().split(" ");

        N = Integer.parseInt(nmInfo[0]);
        M = Integer.parseInt(nmInfo[1]);

        pizzaA = new int[N];
        pizzaB = new int[M];
        sizeA = new int[1000001];
        sizeB = new int[1000001];

        sizeA[0] = 1;
        sizeB[0] = 1;

        int total = 0;
        for (int i = 0; i < N; ++i) {
            int val = Integer.parseInt(br.readLine());
            pizzaA[i] = val;
            total += val;
        }
        sizeA[total]++;

        total = 0;
        for (int i = 0; i < M; ++i) {
            int val = Integer.parseInt(br.readLine());
            pizzaB[i] = val;
            total += val;
        }
        sizeB[total]++;

        //pizzaA 부분 연속합 구하기
        for (int st = 0; st < N; ++st) {
            int sum = 0;
            for (int len = 0; len < N-1; ++len) {
                sum += pizzaA[(st+len) % N];
                sizeA[sum]++;   //len =0 일 때, len=1 일 때, len=2일 때, 다 저장하려고 이 때 ++ 연산
            }
        }

        //pizzaB 부분 연속합 구하기
        for (int st = 0; st < M; ++st) {
            int sum = 0;
            for (int len = 0; len < M-1; ++len) {
                sum += pizzaB[(st+len) % M];
                sizeB[sum]++;   //len =0 일 때, len=1 일 때, len=2일 때, 다 저장하려고 이 때 ++ 연산
            }
        }

        int ans = 0;
        for (int restSize = 0; restSize <= K; ++restSize) {
            ans += ((sizeA[restSize]) * (sizeB[K-restSize]));
        }

        System.out.println(ans);
    }
}
