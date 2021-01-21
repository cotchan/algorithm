# https://www.acmicpc.net/problem/5567
# 결혼식

import sys
from collections import deque

def bfs():
    visited = [False for _ in range(N)]
    que = deque()
    que.append((0,0))
    visited[0] = True

    result = -1

    while len(que):
        id,dist = que.popleft()

        if dist > 2:
            break

        result += 1

        for nxt in graph[id]:
            if not(visited[nxt]):
                visited[nxt] = True
                que.append((nxt,dist+1))

    return result

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

graph = [[] for loop in range(N)]

for _ in range(M):
    p1, p2 = map(int,sys.stdin.readline().split())
    graph[p1-1].append(p2-1)
    graph[p2-1].append(p1-1)

print(bfs())
