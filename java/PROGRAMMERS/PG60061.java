import java.util.*;

class Solution {

    public final int KIDOONG = 0;
    public final int BO = 1;
    public final int REMOVE = 0;
    public final int SET = 1;
    public final int EMPTY= 0;
    public final int NOT_EMPTY= 1;

    public int N;
    public int[][] kidoong, bo;

    public boolean enableSetKidoong(int y, int x) {
        if (y == 0) {
            return true;
        } else {
            if (kidoong[y-1][x] == NOT_EMPTY) return true;
            else if (bo[y][x] == NOT_EMPTY) return true;
            else if (x > 0 && bo[y][x-1] == NOT_EMPTY) return true;
            else return false;
        }
    }

    public boolean enableSetBo(int y, int x) {
        if (y > 0 && (kidoong[y-1][x] == NOT_EMPTY || kidoong[y-1][x+1] == NOT_EMPTY)) return true;
        else if (x > 0 && bo[y][x-1] == NOT_EMPTY && bo[y][x+1] == NOT_EMPTY) return true;
        else return false;
    }

    public boolean enableRemoveKidoong(int y, int x) {
        if (y < N && kidoong[y+1][x] == NOT_EMPTY && !enableSetKidoong(y+1,x)) return false;
        else if (y < N && bo[y+1][x] == NOT_EMPTY && !enableSetBo(y+1,x)) return false;
        else if (x > 0 && bo[y+1][x-1] == NOT_EMPTY && !enableSetBo(y+1,x-1)) return false;
        else return true;
    }

    public boolean enableRemoveBo(int y, int x) {
        if (kidoong[y][x] == NOT_EMPTY && !enableSetKidoong(y,x)) return false;
        else if (x < N && kidoong[y][x+1] == NOT_EMPTY && !enableSetKidoong(y,x+1)) return false;
        else if (x > 0 && bo[y][x-1] == NOT_EMPTY && !enableSetBo(y,x-1)) return false;
        else if (x < N && bo[y][x+1] == NOT_EMPTY && !enableSetBo(y,x+1)) return false;
        else return true;
    }

    public int[][] makeAnswer(int answerSize) {
        int[][] result = new int[answerSize][3];

        int idx = 0;
        for (int x = 0; x < N + 1; ++x) {
            for (int y = 0; y < N + 1; ++y) {
                if (kidoong[y][x] == NOT_EMPTY) {
                    int[] candidate = {x, y, KIDOONG};
                    result[idx++] = candidate;
                }
                if (bo[y][x] == NOT_EMPTY) {
                    int[] candidate = {x, y, BO};
                    result[idx++] = candidate;
                }
            }
        }

        return result;
    }

    public int[][] solution(int n, int[][] build_frame) {

        N = n;
        kidoong = new int[n + 1][n + 1];
        bo = new int[n + 1][n + 1];

        for (int[] info : build_frame) {
            int x,y,a,b;
            x = info[0];    //가로
            y = info[1];    //세로
            a = info[2];    //0: 기둥, 1:보
            b = info[3];    //0: 삭제, 1: 설치

            if (a == KIDOONG && b == REMOVE) {
                kidoong[y][x] = EMPTY;
                if (!enableRemoveKidoong(y, x)) {
                    kidoong[y][x] = NOT_EMPTY;
                }
            } else if (a == KIDOONG && b == SET) {
                if (enableSetKidoong(y, x)) {
                    kidoong[y][x] = NOT_EMPTY;
                }
            } else if (a == BO && b == REMOVE) {
                bo[y][x] = EMPTY;
                if (!enableRemoveBo(y, x)) {
                    bo[y][x] = NOT_EMPTY;
                }
            } else if (a == BO && b == SET) {
                if (enableSetBo(y, x)) {
                    bo[y][x] = NOT_EMPTY;
                }
            }
        }

        int kidoongCnt = 0;
        int boCnt = 0;
        for (int i = 0; i < N + 1; ++i) {
            for (int j = 0; j < N + 1; ++j) {
                if (kidoong[i][j] == NOT_EMPTY) kidoongCnt++;
                if (bo[i][j] == NOT_EMPTY) {
                    boCnt++;
                    System.out.println("(" + i + ", " + j + ")");
                }
            }
        }

        int[][] answer = makeAnswer(kidoongCnt + boCnt);
        return answer;
    }
}
