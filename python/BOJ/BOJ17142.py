# https://www.acmicpc.net/problem/17142
# 연구소 3

import sys
from collections import deque
from itertools import combinations

def isSafe(boardSize, y, x):
    return (0 <= y and y < boardSize and 0 <= x and x < boardSize)

dy = [-1,1,0,0]
dx = [0,0,-1,1]

N,M = map(int,sys.stdin.readline().split())

board = []
virusPos = []
zeroCnt = 0

for i in range(N):
    row = list(map(int,sys.stdin.readline().split()))
    board.append(row)

    for j in range(N):
        if board[i][j] == 0:
            zeroCnt += 1
        if board[i][j] == 2:
            virusPos.append((i,j))

orders = [i for i in range(len(virusPos))]

combi = combinations(orders, M)
combinationCandidate = list(combi)

answer = 987654321

for candidate in combinationCandidate:
    # 0 1 2
    visited = [[False for _ in range(N)] for _ in range(N)]
    q = deque()

    for idx in candidate:
        # 0
        # 1
        # 2
        y,x = virusPos[idx]
        visited[y][x] = True
        q.append((y,x))

    # bfs
    spreadCnt = 0
    _time = 0

    while True:

        if len(q) == 0 or spreadCnt == zeroCnt:
            break

        loop = len(q)
        for _ in range(loop):
            y,x = q.popleft()

            for dir in range(4):
                ny = y + dy[dir]
                nx = x + dx[dir]

                if isSafe(N,ny,nx) and not(visited[ny][nx]) and (board[ny][nx] == 0 or board[ny][nx] == 2):
                    visited[ny][nx] = True
                    q.append((ny,nx))

                    if board[ny][nx] == 0:
                        spreadCnt += 1

        _time += 1

    if spreadCnt == zeroCnt:
        answer = min(answer, _time)

if answer == 987654321:
    print(-1)
else:
    print(answer)


