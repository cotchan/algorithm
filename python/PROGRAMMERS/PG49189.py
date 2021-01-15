# https://programmers.co.kr/learn/courses/30/lessons/49189
# 가장 먼 노드

from collections import deque

def bfs(n, graph):

    candidate = []
    answer = 0
    visited = [False for loop in range(n)]
    visited[0] = True
    q = deque()
    q.append(0)

    while len(q) != 0:
        loop = len(q)

        answer = loop
        candidate.clear()

        for i in range(loop):
            now_node = q.popleft()

            for nxt in graph[now_node]:
                if not(visited[nxt]):
                    visited[nxt] = True
                    q.append(nxt)
                    candidate.append(nxt)

    return answer

def solution(n, edge):

    # 2차원 리스트 선언
    graph = [[] for loop in range(n)]

    # init
    for edgeInfo in edge:
        src, dst = edgeInfo
        src -= 1
        dst -= 1
        graph[src].append(dst)
        graph[dst].append(src)

    return bfs(n,graph)