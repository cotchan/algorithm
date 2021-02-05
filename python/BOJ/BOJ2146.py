# https://www.acmicpc.net/problem/2146
# 다리 만들기

import sys
from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

def isSafe(bSize, y, x):
    return (0 <= y and y < bSize and 0 <= x and x < bSize)

def bfs(board, visited, st_y,st_x):

    result = []
    result.append((st_y,st_x))

    q = deque()
    q.append((st_y,st_x))

    boardSize = len(board)

    while len(q):
        y,x = q.popleft()

        for dir in range(4):
            ny = y + dy[dir]
            nx = x + dx[dir]

            if isSafe(boardSize,ny,nx) and board[ny][nx] == 1 and not(visited[ny][nx]):
                visited[ny][nx] = True
                result.append((ny,nx))
                q.append((ny,nx))

    return result


N = int(sys.stdin.readline())
board = []
visited = [[False for _ in range(N)] for _ in range(N)]

# init data
for _ in range(N):
    row = list(map(int,sys.stdin.readline().split()))
    board.append(row)

# 데이터 가공
# 컴포넌트 나누기
components = []

for i in range(N):
    for j in range(N):
        if board[i][j] == 1 and not(visited[i][j]):
            visited[i][j] = True
            # bfs(i,j)
            newComponent = bfs(board,visited,i,j)
            components.append(newComponent)

# newBoard = (componentNumber, 이 칸까지 오는데 걸린 거리)
newBoard = [[(0,0) for _ in range(N)] for _ in range(N)]
q = deque()

for idx in range(len(components)):
    compoNumber = idx + 1
    for y,x in components[idx]:
        newBoard[y][x] = (compoNumber,0)
        q.append((y,x,0,compoNumber))

# solution
answer = 987654321

while len(q):

    loopSize = len(q)

    for _ in range(loopSize):

        y,x, now_distance, now_compoNumber = q.popleft()

        for dir in range(4):
            ny = y + dy[dir]
            nx = x + dx[dir]

            if isSafe(N,ny,nx):
                nxtComponNumber, nxtDist = newBoard[ny][nx]

                if (nxtComponNumber != 0 and nxtComponNumber != now_compoNumber):
                    answer = min(answer,now_distance + nxtDist)
                elif nxtComponNumber == 0:
                    newBoard[ny][nx] = (now_compoNumber,now_distance+1)
                    q.append((ny, nx, now_distance + 1, now_compoNumber))

print(answer)