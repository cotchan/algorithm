import sys
from queue import PriorityQueue

def bfs(st,en):
    RANGE_MAX = 100000
    visited = [False for loop in range(RANGE_MAX+1)]
    pq = PriorityQueue()

    pq.put((0,st))
    visited[st] = True

    result = 987654321
    while not(pq.empty()):
        now = pq.get()

        if now[1] == en:
            result = min(result, now[0])
            continue

        nxt = 2 * now[1]
        if nxt <= RANGE_MAX and not(visited[nxt]):
            visited[nxt] = True
            pq.put((now[0], nxt))

        nxt = now[1] + 1
        if nxt <= RANGE_MAX and not(visited[nxt]):
            visited[nxt] = True
            pq.put((now[0] + 1, nxt))

        nxt = now[1] - 1
        if nxt >= 0 and not(visited[nxt]):
            visited[nxt] = True
            pq.put((now[0] + 1, nxt))

    return result

subin, brother = map(int, sys.stdin.readline().split())

answer = bfs(subin, brother)
sys.stdout.write(str(answer))
