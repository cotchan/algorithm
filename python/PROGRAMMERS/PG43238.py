# 입국심사
# https://programmers.co.kr/learn/courses/30/lessons/43238

def isPossible(n, times, candidateTime):
    result = 0

    for time in times:
        result += (candidateTime // time)

    return result >= n

def solution(n, times):
    answer = 987654321
    maxv = max(times)

    low = 0
    high = n * maxv

    while low < high:
        mid = (low + high) // 2
        if isPossible(n, times, mid) == True:
            answer = mid
            high = mid
        else:
            low = mid + 1

    return answer