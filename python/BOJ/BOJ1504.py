# https://www.acmicpc.net/problem/1504
# 특정한 최단 경로

import sys
from queue import PriorityQueue

def djikstra(st, en):
    dist = [IMPOSSIBLE for loop in range(N)]
    dist[st] = 0

    pq = PriorityQueue()
    pq.put((0,st))

    while not(pq.empty()):
        now_dist, now_node = pq.get()

        if dist[now_node] < now_dist:
            continue

        for nxt in graph[now_node]:
            nxt_node, nxt_dist = nxt

            if dist[nxt_node] > now_dist + nxt_dist:
                dist[nxt_node] = now_dist + nxt_dist
                pq.put((dist[nxt_node],nxt_node))

    return dist[en]

# main
IMPOSSIBLE = 987654321
N, E = map(int, sys.stdin.readline().split())
graph = [[] for loop in range(N)]

for loop in range(E):
    src, dst, distance = map(int, sys.stdin.readline().split())
    src -= 1
    dst -= 1
    graph[src].append((dst,distance))
    graph[dst].append((src,distance))

v1, v2 = map(int, sys.stdin.readline().split())
v1 -= 1
v2 -= 1

candidate1 = djikstra(0, v1) + djikstra(v1, v2) + djikstra(v2, N-1)
candidate2 = djikstra(0, v2) + djikstra(v2, v1) + djikstra(v1, N-1)

answer = min(candidate1, candidate2)

if answer >= IMPOSSIBLE:
    print(-1)
else:
    print(answer)