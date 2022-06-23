
import java.io.*;
import java.util.*;


public class Main {

    static int N;
    static PriorityQueue<Integer> minHeap, maxHeap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; ++i) {
            int num = Integer.parseInt(br.readLine());

            if (minHeap.size() == maxHeap.size()) {
                maxHeap.add(num);
            } else if (maxHeap.size() > minHeap.size()) {
                minHeap.add(num);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                int minHeapVal = minHeap.poll();
                int maxHeapVal = maxHeap.poll();
                minHeap.add(maxHeapVal);
                maxHeap.add(minHeapVal);
            }

            if (!maxHeap.isEmpty()) {
                sb.append(maxHeap.peek()).append('\n');
            }
        }

        System.out.println(sb);
    }
}
