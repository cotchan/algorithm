# https://www.acmicpc.net/problem/2468
# 안전 영역

import sys
from collections import deque

dy = [-1,1,0,0]
dx = [0,0,-1,1]

IMPOSSIBLE = 0
POSSIBLE = 1

def isSafe(y,x):
    return 0 <= y and y < N and 0 <= x and x < N

def bfs(board, visited, y, x):

    q = deque()
    q.append((y,x))

    while len(q):
        now_y,now_x = q.popleft()

        for dir in range(4):
            ny = now_y + dy[dir]
            nx = now_x + dx[dir]

            if isSafe(ny,nx) and board[ny][nx] == POSSIBLE and not(visited[ny][nx]):
                q.append((ny,nx))
                visited[ny][nx] = True

    return

def solve(graph, height):
    board = [[POSSIBLE for loop in range(N)] for loop in range(N)]
    visited = [[False for loop in range(N)] for loop in range(N)]

    for i in range(N):
        for j in range(N):
            if graph[i][j] <= height:
                board[i][j] = IMPOSSIBLE

    componentCnt = 0

    for i in range(N):
        for j in range(N):
            if (board[i][j] == POSSIBLE) and not(visited[i][j]):
                visited[i][j] = True
                bfs(board,visited,i,j)
                componentCnt += 1

    return componentCnt

N = int(sys.stdin.readline())
graph = []

maxHeight = -1

for _ in range(N):
    column = list(map(int, sys.stdin.readline().split()))
    candidate = max(column)
    maxHeight = max(maxHeight, candidate)
    graph.append(column)

answer = -1
for height in range(0, maxHeight + 1):
    answer = max(answer, solve(graph,height))

print(answer)
