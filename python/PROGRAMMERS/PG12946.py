# https://programmers.co.kr/learn/courses/30/lessons/12946
# 하노이의 탑

def move(result, n, _from, _to):
    if n == 0:
        return

    middle = 6 - (_from + _to)
    move(result, n-1, _from, middle)
    # move  N
    result.append([_from,_to])
    move(result, n-1, middle, _to)
    return

def solution(n):
    answer = []
    move(answer,n,1,3)
    print(answer)
    return answer