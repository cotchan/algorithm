import java.util.*;

class Solution {

    public static int cnt;
    public static boolean[] visited = new boolean[8];
    public static char[] origin = new char[8];
    public static Map<Integer, Character> friend = new HashMap<>();

    public int solution(int n, String[] data) {

        friend.put(0, 'A');
        friend.put(1, 'C');
        friend.put(2, 'F');
        friend.put(3, 'J');
        friend.put(4, 'M');
        friend.put(5, 'N');
        friend.put(6, 'R');
        friend.put(7, 'T');

        cnt = 0;
        permutation(data, 0);

        int answer = cnt;
        return answer;
    }

    public void permutation(String[] data, int idx) {

        if (idx == 8) {
            if (isAnswer(data)) {
                cnt++;
            }

            return;
        }

        for (int i = 0; i < 8; ++i) {
            if (!visited[i]) {
                visited[i] = true;
                origin[idx] = friend.get(i);
                permutation(data, idx + 1);
                visited[i] = false;
            }
        }
    }

    public boolean isAnswer(String[] data) {

        for (String condition : data) {
            char a = condition.charAt(0);
            char b = condition.charAt(2);
            char oper = condition.charAt(3);
            int diff = (condition.charAt(4) - '0') + 1;

            int aPos = - 1;
            int bPos = -1;

            for (int i = 0; i < 8; ++i) {
                char target = origin[i];

                if (target == a) {
                    aPos = i;
                } else if (target == b) {
                    bPos = i;
                }
            }

            if (oper == '=') {
                if (Math.abs(aPos - bPos) != diff) return false;
            } else if (oper == '<') {
                if (Math.abs(aPos - bPos) >= diff) return false;
            } else {
                if (Math.abs(aPos - bPos) <= diff) return false;
            }
        }

        return true;
    }
}
