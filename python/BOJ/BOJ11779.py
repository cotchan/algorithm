import sys
from queue import PriorityQueue

def djikstra(st, en):
    dist = [999999999 for loop in range(n)]
    parent = [-1 for loop in range(n)]
    dist[st] = 0
    pq = PriorityQueue()
    pq.put((0,st))

    while not(pq.empty()):
        (now_cost, now_node) = pq.get()

        if now_cost > dist[now_node]:
            continue

        for nxt in graph[now_node]:
            (nxtNode, nxtCost) = nxt

            if dist[nxtNode] > now_cost + nxtCost:
                dist[nxtNode] = now_cost + nxtCost
                parent[nxtNode] = now_node
                pq.put((dist[nxtNode],nxtNode))

    # get path
    result = []

    path = en
    while path != st:
        result.append(path)
        path = parent[path]

    result.append(path)
    result.reverse()

    return (dist[en],result)

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

graph = [[] for loop in range(n)]

for loop in range(m):
    src, dst, cost = map(int, sys.stdin.readline().split())
    graph[src-1].append((dst-1,cost))

st, en = map(int, sys.stdin.readline().split())
st -= 1
en -= 1

(shortestPath, paths) = djikstra(st,en)

# print answer
sys.stdout.write(str(shortestPath) + "\n" + str(len(paths)) + '\n')
for node in paths:
    sys.stdout.write(str(node + 1) + ' ')