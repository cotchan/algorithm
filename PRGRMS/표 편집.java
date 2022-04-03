import java.util.*;

class Solution {

    class Pair {
        int prevValue, erasedValue;

        public Pair(int prevValue, int erasedValue) {
            this.prevValue = prevValue;
            this.erasedValue = erasedValue;
        }
    }

    public static final int MAX_SIZE = 1_200_005;

    public static int unusedIdx = 1;
    public static int[] prev, data, nxt, numbersIdx;

    public int insert(int address, int number) {
        prev[unusedIdx] = address;
        data[unusedIdx] = number;
        nxt[unusedIdx] = nxt[address];

        if (nxt[address] != -1) {
            int before = nxt[address];
            prev[before] = unusedIdx;
        }

        nxt[address] = unusedIdx;
        return unusedIdx++;
    }

    public int erase(int address) {
        //prev 갱신
        int before = prev[address];
        nxt[before] = nxt[address];

        //nxt 갱신
        //현재 선택된 행을 삭제한 후, 바로 아래 행을 선택
        if (nxt[address] != -1) {
            int next = nxt[address];
            prev[next] = before;
            return next;
        }

        //마지막 행이라면 바로 앞 행 리턴
        return prev[address];
    }

    public String solution(int n, int k, String[] cmd) {

        prev = new int[MAX_SIZE];
        data = new int[MAX_SIZE];
        nxt = new int[MAX_SIZE];
        numbersIdx = new int[MAX_SIZE];

        Arrays.fill(prev, -1);
        Arrays.fill(data, -1);
        Arrays.fill(nxt, -1);
        Arrays.fill(numbersIdx, -1);

        Stack<Pair> stk = new Stack<>();

        // init
        for (int i = 0; i < n; ++i) {
            numbersIdx[i] = insert(i, i);
        }

        int cursor = numbersIdx[k];

        for (String commandString : cmd) {
            String[] cmdInfo = commandString.split(" ");
            String command = cmdInfo[0];

            if (command.equals("U")) {
                int value = Integer.parseInt(cmdInfo[1]);

                while (value-- > 0) {
                    cursor = prev[cursor];
                }

            } else if (command.equals("D")) {
                int value = Integer.parseInt(cmdInfo[1]);

                while (value-- > 0) {
                    cursor = nxt[cursor];
                }

            } else if (command.equals("C")) {
                int prevValue = data[prev[cursor]];
                int erasedValue = data[cursor];

                stk.push(new Pair(prevValue, erasedValue));
                cursor = erase(cursor);
            } else {

                Pair recover = stk.pop();

                int prevValue = recover.prevValue;
                int recoverValue = recover.erasedValue;

                int prevIdx;

                if (prevValue == -1) {
                    prevIdx = 0;
                } else {
                    prevIdx = numbersIdx[prevValue];
                }

                numbersIdx[recoverValue] = insert(prevIdx, recoverValue);
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; ++i) {
            sb.append('O');
        }

        while (!stk.isEmpty()) {
            int erasedValue = stk.pop().erasedValue;
            sb.setCharAt(erasedValue, 'X');
        }

        return sb.toString();
    }
}
