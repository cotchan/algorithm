import java.io.*;

public class Main {

    public static final int MAX_SIZE = 1024;
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            String[] numberInfo = br.readLine().split(" ");

            int a = Integer.parseInt(numberInfo[0]);
            int b = Integer.parseInt(numberInfo[1]);
            int minv = Math.min(a,b);
            int maxv = Math.max(a,b);

            int parent = find(minv, maxv);
            sb.append(parent);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static int find(int minv, int maxv) {

        boolean[] minvParent = new boolean[MAX_SIZE];
        minvParent[minv] = true;

        while (minv != 0) {
            int parent = minv / 2;
            minvParent[parent] = true;
            minv = parent;
        }

        while (maxv != 0) {
            int parent = maxv / 2;

            if (minvParent[parent]) {
                return parent * 10;
            }
            maxv = parent;
        }

        return 10;
    }
}
