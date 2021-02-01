import sys
import copy
from collections import deque

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

def isSafe(boardSize,y,x):
    return (0 <= y and y < boardSize and 0 <= x and x < boardSize)

def bfs(board,visited,N,L,R,y,x):

    visited[y][x] = True

    result = []
    result.append((y,x))

    q = deque()
    q.append((y, x, board[y][x]))  # y,x,count

    while len(q):
        now_y, now_x, now_population = q.popleft()

        for dir in range(4):
            ny = now_y + dy[dir]
            nx = now_x + dx[dir]

            if isSafe(N, ny, nx) and not(visited[ny][nx]) \
               and L <= abs(board[ny][nx] - now_population) \
               and abs(board[ny][nx] - now_population) <= R:
                visited[ny][nx] = True
                q.append((ny,nx,board[ny][nx]))
                result.append((ny,nx))

    return result

def main():

    N,L,R = map(int,sys.stdin.readline().split())
    board = []

    for _ in range(N):
        row = list(map(int,sys.stdin.readline().split()))
        board.append(row)

    # bfs
    isUnioned = True

    answer = -1

    while isUnioned:

        answer += 1

        isUnioned = False
        nxt_board = [[0 for _ in range(N)] for _ in range(N)]
        visited = [[False for _ in range(N)] for _ in range(N)]
        components = []

        for i in range(N):
            for j in range(N):
                if visited[i][j] == False:
                    component = bfs(board,visited,N,L,R,i,j)

                    if len(component) >= 2:
                        isUnioned = True

                    components.append(component)

        for compo in components:

            componentSize = len(compo)
            componentPopulSum = 0

            for y,x in compo:
                componentPopulSum += board[y][x]

            newPopulation = componentPopulSum // componentSize

            for y, x in compo:
                nxt_board[y][x] = newPopulation

        board = copy.deepcopy(nxt_board)

    print(answer)

main()