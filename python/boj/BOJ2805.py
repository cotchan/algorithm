N, M = map(int, input().split())

woods = list(map(int,input().split()))

def cut(length):
    result = 0

    for wood in woods:
        if wood <= length: continue

        result += (wood - length)

    return result

def binary_search():
    st = 1
    en = max(woods)
    answer = 0

    while st <= en:
        mid = (st + en) // 2

        result = cut(mid)

        if result >= M:
            st = mid + 1
            answer = max(answer,mid)
        else:
            en = mid - 1
    
    return answer

print(binary_search())
