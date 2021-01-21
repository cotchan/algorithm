# https://www.acmicpc.net/problem/6118
# 숨바꼭질

import sys
from collections import deque

def bfs(start):
    visited = [False for _ in range(N)]
    que = deque()

    que.append(start)
    visited[start] = True

    maxv = -1
    result = []

    while len(que):
        loop = len(que)

        result.clear()
        maxv += 1
        for _ in range(loop):
            now = que.popleft()

            result.append(now)

            for nxt in graph[now]:
                if not(visited[nxt]):
                    visited[nxt] = True
                    que.append(nxt)

    result = sorted(result)
    return (maxv,result)



N, M = map(int, sys.stdin.readline().split())

graph = [[] for _ in range(N)]

for _ in range(M):
    src, dst = map(int, sys.stdin.readline().split())
    graph[src-1].append(dst-1)
    graph[dst-1].append(src-1)

result = bfs(0)
sys.stdout.write(str(result[1][0] + 1) + " " + str(result[0]) + " " + str(len(result[1])))