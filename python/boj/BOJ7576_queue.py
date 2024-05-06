import queue

# n, m
m, n = map(int, input().split())

board = [[0 for _ in range(m)] for _ in range(n)]
visited = [[False for _ in range(m)] for _ in range(n)]

dy = [-1, 1, 0, 0]
dx = [0, 0, -1, 1]

for i in range(n):
    arr = list(map(int, input().split()))
    board[i] = arr


def safe(board, y, x):
    ySize = len(board)
    xSize = len(board[0])
    return 0 <= y and y < ySize and 0 <= x and x < xSize


def bfs(board, n, m):
    result = 0
    tomatoCnt = 0
    searchCnt = 0
    que = queue.Queue()
    for y in range(n):
        for x in range(m):
            if board[y][x] == 1:
                que.put((y, x, 0))
                visited[y][x] = True
            if board[y][x] != -1:
                tomatoCnt += 1

    while que.qsize() > 0:
        loopSize = que.qsize()

        for _ in range(loopSize):
            searchCnt += 1
            y, x, cnt = que.get()

            result = max(result, cnt)

            for dir in range(4):
                ny = y + dy[dir]
                nx = x + dx[dir]

                if not safe(board, ny, nx):
                    continue
                if visited[ny][nx] == True:
                    continue
                if board[ny][nx] != 0:
                    continue

                visited[ny][nx] = True
                que.put((ny, nx, cnt+1))

    if searchCnt == tomatoCnt:
        return result
    else:
        return -1


print(bfs(board, n, m))
