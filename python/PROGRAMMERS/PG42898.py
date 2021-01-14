# https://programmers.co.kr/learn/courses/30/lessons/42898
# 등굣길

def solution(m, n, puddles):

    dp = [[0 for loop in range(m)] for loop in range(n)]
    graph = [[0 for loop in range(m)] for loop in range(n)]

    # init
    for (x,y) in puddles:
        y -= 1
        x -= 1
        graph[y][x] = 1

    for idx in range(m):
        if graph[0][idx] != 1:
            dp[0][idx] = 1
        else:
            break

    for idx in range(n):
        if graph[idx][0] != 1:
            dp[idx][0] = 1
        else:
            break

    # solve
    for i in range(1,n):
        for j in range(1,m):
            if graph[i][j] == 1:
                dp[i][j] = 0
            else:
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000007

    return dp[n-1][m-1]