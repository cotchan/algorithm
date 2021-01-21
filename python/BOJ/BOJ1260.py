# https://www.acmicpc.net/problem/1260
# DFS와 BFS

import sys
from collections import deque

# bfs
def bfs(start):
    result = []
    visited = [False for loop in range(N)]
    que = deque()
    que.append(start)
    visited[start] = True
    result.append(start+1)

    while len(que):
        now = que.popleft()

        for nxt in graph[now]:
            if not(visited[nxt]):
                visited[nxt] = True
                result.append(nxt+1)
                que.append(nxt)

    return result

# dfs
def dfs(result,trip, node):

    for nxt in graph[node]:
        if not(trip[nxt]):
            trip[nxt] = True
            result.append(nxt+1)
            dfs(result,trip,nxt)

# init
N,M,V = map(int,sys.stdin.readline().split())

graph = [[] for loop in range(N)]

for _ in range(M):
    src, dst = map(int,sys.stdin.readline().split())
    graph[src-1].append(dst-1)
    graph[dst-1].append(src-1)

for idx in range(N):
    graph[idx].sort()

# dfs
trip = [False for loop in range(N)]
trip[V-1] = True
dfs_result = []
dfs_result.append(V)

dfs(dfs_result,trip,V-1)
bfs_result = bfs(V-1)

# result
for node in dfs_result:
    sys.stdout.write(str(node) + " ")

print()

for node in bfs_result:
    sys.stdout.write(str(node) + " ")

