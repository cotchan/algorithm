# https://www.acmicpc.net/problem/1197
# 최소 스패닝 트리

import sys
from queue import PriorityQueue

def find(parent, node):
    if parent[node] == node:
        return node
    else:
        parent[node] = find(parent, parent[node])
        return parent[node]

def union(parent, node1, node2):
    parent[node2] = node1

def kruskal():
    parent = [i for i in range(V)]

    result = 0
    pick = 0

    while (pick != V-1) and not(pq.empty()):
        weight, src, dst = pq.get()

        component1 = find(parent,src)
        component2 = find(parent,dst)

        if component1 != component2:
            union(parent,component1, component2)
            pick += 1
            result += weight

    return result

V, E = map(int,sys.stdin.readline().split())

pq = PriorityQueue()

for _ in range(E):
    src, dst, weight = map(int,sys.stdin.readline().split())
    pq.put((weight, src-1, dst-1))

sys.stdout.write(str(kruskal()))