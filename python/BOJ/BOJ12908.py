# https://www.acmicpc.net/problem/12908

import sys
import heapq

def getDistance(y1,x1,y2,x2):
    return (abs(y1-y2) + abs(x1-x2))

IMPOSSIBLE = sys.maxsize
st_y, st_x = map(int, sys.stdin.readline().split())
en_y, en_x = map(int, sys.stdin.readline().split())

graph = [[IMPOSSIBLE for _ in range(8)] for _ in range(8)]
node = {}

node[0] = (st_y,st_x)
node[1] = (en_y,en_x)

nodeNum = 2
for _ in range(3):
    y1,x1,y2,x2 = map(int, sys.stdin.readline().split())
    node[nodeNum] = (y1,x1)
    node[nodeNum+1] = (y2,x2)
    nodeNum += 2

for src in range(8):
    for dst in range(8):
        if src != dst:
            y1,x1 = node[src]
            y2,x2 = node[dst]
            distance = getDistance(y1,x1,y2,x2)
            graph[src][dst] = distance
            graph[dst][src] = distance

nodeNum = 2
for _ in range(3):
    graph[nodeNum][nodeNum+1] = 10
    graph[nodeNum+1][nodeNum] = 10
    nodeNum += 2

pq = []
dist = [IMPOSSIBLE for _ in range(8)]
dist[0] = 0
# dist, startNode
heapq.heappush(pq,(0,0))

while len(pq):
    nowDistance, nowNode = heapq.heappop(pq)

    if dist[nowNode] < nowDistance:
        continue

    for nxt in range(8):
        if nxt != nowNode:
            nxtDistance = nowDistance + graph[nowNode][nxt]
            if dist[nxt] > nxtDistance:
                dist[nxt] = nxtDistance
                heapq.heappush(pq,(nxtDistance, nxt))

print(dist[1])