# https://www.acmicpc.net/problem/11404
# 플로이드

import sys

IMPOSSIBLE = 987654321

n = int(sys.stdin.readline())
m = int(sys.stdin.readline())

dist = [[IMPOSSIBLE for loop in range(n) ] for loop in range(n)]
# graph = [[IMPOSSIBLE for loop in n] for loop in n]


for loop in range(n):
    dist[loop][loop] = 0

for loop in range(m):
    st, en, cost = map(int, sys.stdin.readline().split())
    st -= 1
    en -= 1
    dist[st][en] = min(cost, dist[st][en])

for k in range(n):
    for i in range(n):
        for j in range(n):
            if (dist[i][k] == IMPOSSIBLE) or (dist[k][j] == IMPOSSIBLE):
                continue
            if dist[i][j] > (dist[i][k] + dist[k][j]):
                dist[i][j] = dist[i][k] + dist[k][j]

# print
for i in range(n):
    for j in range(n):
        if dist[i][j] == IMPOSSIBLE:
            sys.stdout.write(str(0) + ' ')
        else:
            sys.stdout.write(str(dist[i][j]) + ' ')

    sys.stdout.write(str('\n'))

