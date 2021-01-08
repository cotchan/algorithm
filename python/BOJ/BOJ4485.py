import sys
from queue import PriorityQueue

INF = 987654321
dy = [-1,1,0,0]
dx = [0,0,-1,1]

def isSafe(y, x):
    return (0 <= y and y < N and 0 <= x and x < N)

def djikstra():
    dist = [[INF for loop in range(N)] for loop in range(N)]
    q = PriorityQueue()

    # cost, y, x
    dist[0][0] = board[0][0]
    q.put((dist[0][0],0,0))

    while not(q.empty()):
        nowCost,y,x = q.get()

        if nowCost > dist[y][x]:
            continue

        for dir in range(4):
            ny = y + dy[dir]
            nx = x + dx[dir]

            if not(isSafe(ny,nx)):
                continue

            if dist[ny][nx] > nowCost + board[ny][nx]:
                dist[ny][nx] = nowCost + board[ny][nx]
                q.put((dist[ny][nx],ny,nx))

    return dist[N-1][N-1]

# main
test_case = 0
N = int(sys.stdin.readline())

while N != 0:
    test_case += 1
    board = [[0 for loop in range(N)] for loop in range(N)]
    for i in range(N):
        input = list(map(int,sys.stdin.readline().split()))
        for j in range(len(input)):
            board[i][j] = input[j]

    result = djikstra()
    sys.stdout.write("Problem " + str(test_case) + ": " + str(result) + '\n')

    N = int(sys.stdin.readline())
