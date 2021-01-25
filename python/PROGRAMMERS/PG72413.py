# https://programmers.co.kr/learn/courses/30/lessons/72413
# 합승 택시요금

# fares [c,d,f]
IMPOSSIBLE = 987654321

def solution(n, s, a, b, fares):

    dist = [[IMPOSSIBLE for _ in range(n)] for _ in range(n)]

    for pair in fares:
        src, dst, cost = pair
        dist[src-1][dst-1] = cost
        dist[dst-1][src-1] = cost

    for k in range(n):
        for i in range(n):
            for j in range(n):
                if (dist[i][k] + dist[k][j]) < dist[i][j]:
                    dist[i][j] = dist[i][k] + dist[k][j]

    s -= 1
    a -= 1
    b -= 1

    # case1. 중간 지점을 경유하지 않는 경우
    answer = dist[s][a] + dist[s][b]

    # case2. 중간 지점을 경유하는 경우
    for mid in range(n):
        if mid == s:
            continue
        elif mid == a:
            continue
        elif mid == b:
            continue
        else:
            answer = min(answer, dist[s][mid] + dist[mid][a] + dist[mid][b])

    # case3. 한 경로가 다른 경로에 포함되는 경우
    answer = min(answer, dist[s][a] + dist[a][b])
    answer = min(answer, dist[s][b] + dist[b][a])

    return answer