# https://www.acmicpc.net/problem/14588
# Line Friends (Small)

import sys


def isAdj(node1, node2):
    lo1 = dic[node1][0]
    hi1 = dic[node1][1]

    lo2 = dic[node2][0]
    hi2 = dic[node2][1]

    if hi1 < lo2:
        return False
    elif hi2 < lo1:
        return False
    else:
        return True

# main

# init
IMPOSSBILE = 987654321

N = int(sys.stdin.readline())
dic = {}
dist = [[IMPOSSBILE for _ in range(N)] for _ in range(N)]

for number in range(N):
    lo,hi = map(int,sys.stdin.readline().split())
    dic[number] = (lo,hi)

for i in range(N-1):
    for j in range(i+1,N):
        if isAdj(i,j):
            dist[i][j] = 1
            dist[j][i] = 1

for k in range(N):
    for i in range(N):
        for j in range(N):
            if dist[i][k] + dist[k][j] < dist[i][j]:
                dist[i][j] = dist[i][k] + dist[k][j]

# solve
results = []
queryCnt = int(sys.stdin.readline())

for _ in range(queryCnt):
    n1, n2 = map(int,sys.stdin.readline().split())
    result = dist[n1-1][n2-1]
    if result == IMPOSSBILE:
        results.append(-1)
    else:
        results.append(result)

for num in results:
    sys.stdout.write(str(num) + '\n')
