n, m = map(int, input().split())

board = [[0 for _ in range(m)] for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)]

for i in range(n):
    inputString = input()
    arr = []
    for s in inputString:
        arr.append(int(s))
    board[i] = arr

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]


def isSafe(board, y, x):
    ySize = len(board)
    xSize = len(board[0])
    return 0 <= y and y < ySize and 0 <= x and x < xSize


def bfs(board, stY, stX, enY, enX):
    q = []

    q.append((stY, stX, 1))

    while len(q) > 0:
        y, x, cnt = q.pop(0)

        if y+1 == enY and x+1 == enX:
            return cnt

        for dir in range(4):
            ny = y + dy[dir]
            nx = x + dx[dir]

            if not isSafe(board, ny, nx):
                continue
            if board[ny][nx] == 0:
                continue
            if visited[ny][nx] == True:
                continue

            visited[ny][nx] = True
            q.append((ny, nx, cnt+1))

    return -1

print(bfs(board, 0, 0, n, m))
