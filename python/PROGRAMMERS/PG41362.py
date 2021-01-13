# https://programmers.co.kr/learn/courses/30/lessons/43162
# 0113_네트워크

def dfs(n, computers, now, visited):

    for nxt in range(n):
        if nxt != now and (computers[now][nxt] == 1) and not(visited[nxt]):
            visited[nxt] = True
            dfs(n, computers, nxt, visited)

    return

def solution(n, computers):
    answer = 0
    visited = [False for loop in range(n)]

    for computer in range(n):
        if not(visited[computer]):
            answer += 1
            visited[computer] = True
            dfs(n, computers, computer, visited)

    return answer