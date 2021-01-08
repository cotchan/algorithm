import sys
from collections import deque

WALL = 1
EMPTY = 0
JIHOON = 2
FIRE = 3

# jihoon_y, jihoon_x
jy = -1
jx = -1

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def isSafe(y,x):
    return (0 <= y and y < h and 0 <= x and x < w)

def isAdj(y,x):
    for dir in range(4):
        ny = y + dy[dir]
        nx = x + dx[dir]

        if not(isSafe(ny,nx)):
            return True

    return False

def bfs():
    q.append((JIHOON, jy, jx, 1))
    visited[jy][jx] = True

    while len(q) != 0:
        for loop in range(len(q)):
            state, y, x, time = q.popleft()

            if state == JIHOON and isAdj(y,x):
                return time

            for dir in range(4):
                ny = y + dy[dir]
                nx = x + dx[dir]

                if not(isSafe(ny,nx)) or visited[ny][nx] or (board[ny][nx] == WALL):
                    continue

                visited[ny][nx] = True
                q.append((state,ny,nx,time + 1))

    return -1

h,w = map(int,sys.stdin.readline().split())

# init
board = [[EMPTY for loop in range(w)] for loop in range(h)]
visited = [[False for loop in range(w)] for loop in range(h)]
q = deque()

for row in range(h):
    line = sys.stdin.readline()
    for col in range(len(line) - 1): # \n 제외

        if line[col] == '#':
            board[row][col] = WALL
        elif line[col] == 'J':
            board[row][col] = EMPTY
            jy = row
            jx = col
        elif line[col] == 'F':
            board[row][col] = EMPTY
            visited[row][col] = True
            q.append((FIRE, row, col, -1))
        else: # .
            board[row][col] = EMPTY

result = bfs()

if result == -1:
    print("IMPOSSIBLE")
else:
    print(result)