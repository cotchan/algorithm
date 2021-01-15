# https://programmers.co.kr/learn/courses/30/lessons/49191
# https://includestdio.tistory.com/32
# 순위

def solution(n, results):
    answer = 0
    graph = [[0 for loop in range(n)] for loop in range(n)]

    for (winner, loser) in results:
        winner -= 1
        loser -= 1
        graph[winner][loser] = 1

    for mid in range(n):
        for i in range(n):
            for j in range(n):
                if graph[i][mid] == 1 and graph[mid][j] == 1:
                    graph[i][j] = 1

    for player in range(n):
        cnt = 0

        for idx in range(n):
            cnt += graph[idx][player]

        for idx in range(n):
            cnt += graph[player][idx]

        if cnt == n-1:
            answer += 1

    return answer