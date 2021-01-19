# https://www.acmicpc.net/problem/1922
# 네트워크 연결

import sys
from queue import PriorityQueue

def find(parent, node):
    if parent[node] == node:
        return node
    else:
        parent[node] = find(parent,parent[node])
        return parent[node]

def union(parent, c1, c2):
    parent[c2] = c1

def kruskal():
    parent = [i for i in range(N)]

    result = 0
    pick = 0

    while pick != N-1 and not(pq.empty()):
        cost,n1,n2 = pq.get()

        component1 = find(parent,n1)
        component2 = find(parent,n2)

        if component1 != component2:
            union(parent,component1,component2)
            pick += 1
            result += cost

    return result

N = int(sys.stdin.readline())
M = int(sys.stdin.readline())

pq = PriorityQueue()

for _ in range(M):
    a,b,c = map(int,sys.stdin.readline().split())
    pq.put((c,a-1,b-1))

sys.stdout.write(str(kruskal()))
