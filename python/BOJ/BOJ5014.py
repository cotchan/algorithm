import sys
from queue import Queue

def bfs(st, en):

    q = Queue()
    q.put((S, 0))
    visited[S] = True

    isFindAnswer = False
    answer = 0
    while q.empty() == False:
        now = q.get()

        if now[0] == G:
            isFindAnswer = True
            answer = now[1]
            break

        nxt = now[0] + U
        if nxt <= F and not(visited[nxt]):
            visited[nxt] = True
            q.put((nxt, now[1] + 1))

        nxt = now[0] - D
        if nxt >= 1 and not(visited[nxt]):
            visited[nxt] = True
            q.put((nxt, now[1] + 1))

    if isFindAnswer == True:
        return str(answer)
    else:
        return "use the stairs"

F,S,G,U,D = tuple(map(int,sys.stdin.readline().split()))
visited = [False for loop in range(F+1)]
answer = bfs(S,G)
sys.stdout.write(answer)