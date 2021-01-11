# https://www.acmicpc.net/problem/5427

import sys
from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def isSafe(y, x):
    return (0 <= y and y < h and 0 <= x and x < w)

def isAdj(y, x):
    for dir in range(4):
        ny = y + dy[dir]
        nx = x + dx[dir]
        if not(isSafe(ny,nx)):
            return True

    return False

def bfs():
    result = 1

    while que:
        for loop in range(len(que)):
            type,y,x = que.popleft()

            if type == SANGGEUN and isAdj(y,x):
                return result

            for dir in range(4):
                ny = y + dy[dir]
                nx = x + dx[dir]

                if isSafe(ny,nx) and (board[ny][nx] != '#') and not(visited[ny][nx]):
                    visited[ny][nx] = True
                    que.append((type,ny,nx))

        result += 1

    return -1

FIRE = 0
SANGGEUN = 1
sy, sx = 0,0    # sanggeun position
test_case = int(sys.stdin.readline())

for loop in range(test_case):
    w, h = tuple(map(int,sys.stdin.readline().split()))
    board = []
    visited = [[False for loop in range(w)] for loop in range(h)]
    que = deque()

    # init board
    for loop2 in range(h):
        input = sys.stdin.readline()
        board.append(input[:-1])

    # init fire pos, sanggeun pos
    for row in range(h):
        for col in range(w):
            if board[row][col] == '*':
                que.append((FIRE,row,col))
                visited[row][col] = True
            elif board[row][col] == '@':
                sy = row
                sx = col

    que.append((SANGGEUN, sy, sx))
    visited[row][col] = True
    answer = bfs()
    if answer != -1:
        sys.stdout.write(str(answer) + '\n')
    else:
        sys.stdout.write("IMPOSSIBLE\n")
