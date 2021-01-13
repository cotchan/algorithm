# https://programmers.co.kr/learn/courses/30/lessons/42861
# 0113_섬 연결하기

from queue import PriorityQueue

def find(parent, node):
    if parent[node] == node:
        return node
    else:
        parent[node] = find(parent,parent[node])
        return parent[node]

def union(parent, land1, land2):
    parent[land2] = land1
    return

# cost
# island1, island2, cost
def solution(n, costs):
    answer = 0
    parent = [0 for loop in range(n)]

    for num in range(n):
        parent[num] = num

    pq = PriorityQueue()

    for cost_info in costs:
        src, dst, cost = cost_info
        pq.put((cost, src, dst))

    lineCnt = 0

    while lineCnt != n-1:
        cost, src, dst = pq.get()

        component1 = find(parent,src)
        component2 = find(parent,dst)

        if component1 != component2:
            union(parent,component1, component2)
            answer += cost
            lineCnt += 1

    return answer