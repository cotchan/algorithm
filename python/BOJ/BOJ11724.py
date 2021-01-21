# https://www.acmicpc.net/problem/11724
# 연결 요소의 갯수

import sys

def find(parent, node):
    if parent[node] == node:
        return node
    else:
        parent[node] = find(parent, parent[node])
        return parent[node]

def union(parent, compo1, compo2):
    parent[compo2] = compo1
    return

N, M = map(int, sys.stdin.readline().split())

parent = [i for i in range(N)]

for _ in range(M):
    src, dst = map(int, sys.stdin.readline().split())
    if src > dst:
        tmp = src
        src = dst
        dst = tmp

    component1 = find(parent, src - 1)
    component2 = find(parent, dst - 1)

    if component1 != component2:
        union(parent, component1,component2)

set = {}
for idx in range(N):
    key = find(parent, idx)
    set[key] = 1

print(len(set))
