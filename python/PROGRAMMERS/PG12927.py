# https://programmers.co.kr/learn/courses/30/lessons/12927
# 야근지수

import heapq

def solution(n, works):
    for i in range(len(works)):
        works[i] *= -1
    heapq.heapify(works)

    for _ in range(n):
        top = heapq.heappop(works)
        if top >= 0:
            break

        top += 1
        heapq.heappush(works, top)

    answer = 0

    while len(works) > 0:
        val = heapq.heappop(works)
        answer += pow(val * -1, 2)
    return answer