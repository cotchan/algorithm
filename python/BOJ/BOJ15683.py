# https://www.acmicpc.net/problem/15683
# 감시

import sys
import copy

VISITED = -1
BLOCK = 6
dy = [-1,0,1,0]
dx = [0,-1,0,1]

board = []
cctvs = []

def isSafe(ySize,xSize,y,x):
    return (0 <= y and y < ySize and 0 <= x and x < xSize)

def beam(_board, y,x,dir):

    dir %= 4
    ySize = len(_board)
    xSize = len(_board[0])

    result = 0
    ny = y
    nx = x

    while isSafe(ySize,xSize,ny,nx) and _board[ny][nx] != BLOCK:
        if _board[ny][nx] == 0:
            _board[ny][nx] = VISITED # 이미 카메라가 감시한 공간
            result += 1

        ny += dy[dir]
        nx += dx[dir]

    return result


def shoot_type1(_board, y,x, dir):
    return beam(_board,y,x,dir)

def shoot_type2(_board, y,x, dir):
    return beam(_board,y,x,dir) + beam(_board,y,x,dir+2)

def shoot_type3(_board, y,x, dir):
    return beam(_board, y, x, dir) + beam(_board, y, x, dir+1)

def shoot_type4(_board, y,x, dir):
    return beam(_board, y, x, dir) + beam(_board, y, x, dir+1) + beam(_board, y, x, dir+2)

def shoot_type5(_board, y,x, dir):
    return beam(_board, y, x, dir) + beam(_board, y, x, dir+1) + beam(_board, y, x, dir+2) + beam(_board, y, x, dir+3)


def solve(directions):

    tmp_board = copy.deepcopy(board)
    result = 0

    for idx in range(len(cctvs)):
        cctvType, y, x = cctvs[idx]

        if cctvType == 1:
            result += shoot_type1(tmp_board, y, x, directions[idx])
        elif cctvType == 2:
            result += shoot_type2(tmp_board, y, x, directions[idx])
        elif cctvType == 3:
            result += shoot_type3(tmp_board, y, x, directions[idx])
        elif cctvType == 4:
            result += shoot_type4(tmp_board, y, x, directions[idx])
        else:
            result += shoot_type5(tmp_board, y, x, directions[idx])

    return result

# main function
def main():
    N, M = map(int, sys.stdin.readline().split())

    emptyBlockCount = 0
    answer = 987654321

    for i in range(N):
        row = list(map(int, sys.stdin.readline().split()))
        for j in range(M):

            # cctv이면
            if 1 <= row[j] and row[j] <= 5:
                cctvs.append((row[j],i,j))  # type, y, x
            elif row[j] == 0:
                emptyBlockCount += 1

        board.append(row)

    # 경우의 수 생성
    cctvSize = len(cctvs)
    for state in range(pow(4, cctvSize)):
        directions = []
        now = state

        for _ in range(cctvSize):
            dir = now % 4
            directions.append(dir)
            now //= 4

        removedBlockCount = solve(directions)
        answer = min(answer, emptyBlockCount - removedBlockCount)

    print(answer)


main()

