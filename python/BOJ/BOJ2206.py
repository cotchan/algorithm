import sys
from collections import deque

def isSafe(y, x):
    return 0 <= y and y < h and 0 <= x and x < w

def solve():
    result = 987654321
    visited = [[[False for loop in range(w)] for loop in range(h)] for loop in range(2)]
    que = deque()

    # y, x, time, isBroken
    que.append((0,0,1,1))
    visited[1][0][0] = True

    while que:
        now = que.popleft()
        (y,x,nowTime,brokenCnt) = now

        if y == h-1 and x == w-1:
            result = nowTime
            break

        for dir in range(4):
            ny = y + dy[dir]
            nx = x + dx[dir]

            if not(isSafe(ny,nx)):
                continue

            if board[ny][nx] == 0 and not(visited[brokenCnt][ny][nx]):
                visited[brokenCnt][ny][nx] = True
                que.append((ny,nx,nowTime + 1, brokenCnt))
            elif board[ny][nx] == 1 and brokenCnt == 1 and not(visited[0][ny][nx]):
                visited[0][ny][nx] = True
                que.append((ny,nx,nowTime + 1, 0))

    return result

dy = [-1,1,0,0]
dx = [0,0,-1,1]

h,w = map(int,sys.stdin.readline().split())
board = [[0 for loop in range(w)] for loop in range(h)]

for i in range(h):
    row = sys.stdin.readline()
    for j in range(len(row) - 1):
        board[i][j] = int(row[j])

answer = solve()
if answer == 987654321:
    sys.stdout.write(str(-1))
else:
    sys.stdout.write(str(answer))
