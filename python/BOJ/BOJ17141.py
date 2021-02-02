# https://www.acmicpc.net/problem/17141
# 연구소2

import sys
from collections import deque
from itertools import combinations
import copy

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def isSafe(boardSize, y, x):
    return (0 <= y and y < boardSize and 0 <= x and x < boardSize)

N,M = map(int,sys.stdin.readline().split())
board = []
zeroCnt = 0

for _ in range(N):
    row = list(map(int,sys.stdin.readline().split()))
    board.append(row)

virus_positions = []

for i in range(N):
    for j in range(N):
        if board[i][j] == 2:
            virus_positions.append((i,j))
            zeroCnt += 1
        elif board[i][j] == 0:
            zeroCnt += 1

candidate = [i for i in range(len(virus_positions))]

combi = combinations(candidate,M)
candidate_list = list(combi)

zeroCnt -= M
answer = 987654321

for now_candidate in candidate_list:

    # init
    tmp_board = copy.deepcopy(board)
    visited = [[False for _ in range(N)] for _ in range(N)]
    q = deque()

    for y,x in virus_positions:
        tmp_board[y][x] = 0

    for idx in now_candidate:
        y,x = virus_positions[idx]
        visited[y][x] = True
        q.append((y,x))

    spreadTime = 0
    filledZeroCnt = 0

    while True:
        loopSize = len(q)

        if (loopSize == 0) or (filledZeroCnt == zeroCnt):
            break

        for _ in range(loopSize):
            y,x = q.popleft()

            for dir in range(4):
                ny = y + dy[dir]
                nx = x + dx[dir]

                if isSafe(N,ny,nx) and not(visited[ny][nx]) and tmp_board[ny][nx] == 0:
                    visited[ny][nx] = True
                    q.append((ny,nx))
                    filledZeroCnt += 1

        spreadTime += 1

    if filledZeroCnt == zeroCnt:
        answer = min(answer, spreadTime)


if answer == 987654321:
    print(-1)
else:
    print(answer)