# https://www.acmicpc.net/problem/17471
# 게리맨더링

import sys

def dfs(graph, visited, candidate, node, number):

    for nxt in graph[node]:
        if not(visited[nxt]) and (candidate[nxt] == number):
            visited[nxt] = True
            dfs(graph, visited, candidate, nxt, number)

    return

N = int(sys.stdin.readline())
graph = [[] for _ in range(N)]
population = list(map(int,sys.stdin.readline().split()))

# make graph
for src in range(N):
    info = list(map(int,sys.stdin.readline().split()))

    if info[0] == 0:
        continue
    else:
        for i in range(1, len(info)):
            dst = info[i] - 1
            graph[src].append(dst)
            graph[dst].append(src)

# make candidate
answer = 987654321
for state in range(pow(2,N)):
    now = state
    candidate_state = []

    zero_state = []
    one_state = []

    for idx in range(N):
        node_state = now % 2
        candidate_state.append(node_state)
        now //= 2

        if node_state == 0:
            zero_state.append(idx)
        else:
            one_state.append(idx)

    # 그래프 탐색
    result = sum(candidate_state)
    if len(zero_state) == 0 or len(one_state) == 0:
        continue

    # dfs
    visited = [False for _ in range(N)]

    # start from zero
    st = zero_state[0]
    visited[st] = True
    dfs(graph, visited, candidate_state, st, 0)

    # start from one
    st = one_state[0]
    visited[st] = True
    dfs(graph, visited, candidate_state, st, 1)

    visitedCnt = 0
    for visit_state in visited:
        if visit_state == True:
            visitedCnt += 1

    if visitedCnt != N:
        continue

    zero_population = 0
    for idx in zero_state:
        zero_population += population[idx]

    one_population = 0
    for idx in one_state:
        one_population += population[idx]

    answer = min(answer, abs(zero_population - one_population))

if answer == 987654321:
    print(-1)
else:
    print(answer)