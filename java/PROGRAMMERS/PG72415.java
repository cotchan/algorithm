import java.util.*;

class Solution {

    static class Pair {
        int first, second;
        Pair (int f, int s) {
            first = f;
            second = s;
        }
    }

    final int EMPTY = 0;
    final int BOARD_SIZE = 4;
    final int DIR_SIZE = 4;
    final int DY[] = {-1,1,0,0};
    final int DX[] = {0,0,-1,1};

    int answer = Integer.MAX_VALUE;
    int cardSize;
    int[] targets;
    boolean[] used;
    Pair[][] cardPositions;

    LinkedList<Integer> orders = new LinkedList<>();

    public boolean isSafe(int y, int x) {
        return 0 <= y && y < BOARD_SIZE && 0 <= x && x < BOARD_SIZE;
    }

    public int realBfs(int[][] board, Pair st, Pair en) {

        Queue<Pair> q = new LinkedList<>();
        boolean[][] visited = new boolean[BOARD_SIZE][BOARD_SIZE];

        q.add(st);
        visited[st.first][st.second] = true;

        int moveCnt = 0;

        while (!q.isEmpty()) {

            int loop = q.size();

            for (int l = 0; l < loop; ++l) {

                Pair now = q.poll();

                if (now.first == en.first && now.second == en.second) {
                    return moveCnt;
                }

                for (int dir = 0; dir < DIR_SIZE; ++dir) {

                    int ny = now.first;
                    int nx = now.second;

                    int loopCnt = 0;

                    while (isSafe(ny + DY[dir], nx + DX[dir])) {
                        ny += DY[dir];
                        nx += DX[dir];

                        if (board[ny][nx] != EMPTY) {
                            break;
                        } else if (loopCnt == 0) {
                            if (!visited[ny][nx]) {
                                visited[ny][nx] = true;
                                q.add(new Pair(ny,nx));
                            }
                        }

                        loopCnt++;
                    }

                    if (!visited[ny][nx]) {
                        visited[ny][nx] = true;
                        q.add(new Pair(ny,nx));
                    }
                }
            }

            moveCnt++;
        }

        return 0;
    }

    //orders와 candidateOrder를 가지고 bfs 순회
    public int bfs(int[][] board, int r, int c, int candidateOrder) {

        //deep copy
        int[][] tmpBoard = new int[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; ++i) {
            tmpBoard[i] = Arrays.copyOf(board[i], BOARD_SIZE);
        }

        int result = 0;
        int stY = r;
        int stX = c;

        for (int order : orders) {
            int targetNumber = order + 1;
            int targetIdx = candidateOrder % 2;

            //path1. stY,stX => target1
            Pair target1 = cardPositions[targetNumber][targetIdx];

            int path1 = realBfs(tmpBoard, new Pair(stY,stX), target1);
            result += (path1 + 1);

            tmpBoard[target1.first][target1.second] = EMPTY;

            //path2. target1 => target2
            int nxtTargetIdx = (targetIdx + 1) % 2;
            Pair target2 = cardPositions[targetNumber][nxtTargetIdx];
            int path2 = realBfs(tmpBoard, target1, target2);
            result += (path2 + 1);

            tmpBoard[target2.first][target2.second] = EMPTY;

            stY = target2.first;
            stX = target2.second;

            candidateOrder /= 2;
        }

        return result;
    }

    public void permutation(int[][] board, int r, int c, int pickCnt) {
        if (pickCnt == cardSize) {
            //do something with orders
            for (int candidate = 0; candidate < (1 << cardSize); ++candidate) {
                int cursorCnt = bfs(board,r,c,candidate);
                answer = Math.min(answer, cursorCnt);
            }

        } else {
            for (int nxt = 0; nxt < cardSize; ++nxt) {
                if (!used[nxt]) {
                    used[nxt] = true;
                    orders.addLast(nxt);
                    permutation(board, r, c, pickCnt+1);
                    orders.removeLast();
                    used[nxt] = false;
                }
            }
        }
    }

    public int solution(int[][] board, int r, int c) {

        //카드 갯수 카운팅
        int cardNumber = -1;

        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] != EMPTY) {
                    cardNumber = Math.max(cardNumber, board[i][j]);
                }
            }
        }

        cardSize = cardNumber;
        targets = new int[cardSize+1];
        cardPositions = new Pair[cardSize+1][2];
        used = new boolean[cardSize];

        //각 카드 좌표 저장
        for (int i = 0; i < BOARD_SIZE; ++i) {
            for (int j = 0; j < BOARD_SIZE; ++j) {
                if (board[i][j] != EMPTY) {
                    int targetNumber = board[i][j];
                    int targetIdx = targets[targetNumber]++;
                    cardPositions[targetNumber][targetIdx] = new Pair(i,j);
                }
            }
        }

        permutation(board, r, c, 0);
        return answer;
    }
}
