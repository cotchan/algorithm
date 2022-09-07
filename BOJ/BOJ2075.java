import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static TreeSet<Integer> maxHeap = new TreeSet<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; ++i) {
            String[] row = br.readLine().split(" ");
            for (int j = 0; j < N; ++j) {
                int number = Integer.parseInt(row[j]);

                if (maxHeap.size() < N) {
                    maxHeap.add(number);
                } else {
                    //maxHeap.size() == N
                    int minValue = maxHeap.last();

                    if (number > minValue) {
                        maxHeap.remove(minValue);
                        maxHeap.add(number);
                    }
                }
            }
        }

        System.out.println(maxHeap.last());
    }
}
