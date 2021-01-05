import sys
from queue import Queue

def bfs(st,en):
    que = Queue()
    que.put((st,0))
    visited[st] = True

    while not(que.empty()):
        now = que.get()

        if now[0] == en:
            return now[1]

        # nxt pos
        nxt = now[0] + 1
        if nxt <= 100000 and not(visited[nxt]):
            visited[nxt] = True
            before[nxt] = now[0]
            que.put((nxt,now[1] + 1))

        nxt = now[0] - 1
        if nxt >= 0 and not(visited[nxt]):
            visited[nxt] = True
            before[nxt] = now[0]
            que.put((nxt,now[1] + 1))

        nxt = 2 * now[0]
        if nxt <= 100000 and not(visited[nxt]):
            visited[nxt] = True
            before[nxt] = now[0]
            que.put((nxt,now[1] + 1))

subin, brother = map(int,sys.stdin.readline().split())
visited = [False for loop in range(100001)]
before = [0 for loop in range(100001)]

answer = bfs(subin,brother)
sys.stdout.write(str(answer) + '\n')

posList = []
pos = brother
while pos != subin:
    posList.append(pos)
    pos = before[pos]

posList.append(subin)
posList.reverse()

for pos in posList:
    sys.stdout.write(str(pos) + " ")