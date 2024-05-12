K, N = map(int, input().split())

lines = []

for _ in range(K):
    line = int(input())
    lines.append(line)


def get_line_count(width):
    result = 0

    for line in lines:
        result += (line // width)

    return True if result >= N else False


def binary_search():
    st = 1
    en = max(lines)
    answer = 0

    while st <= en:
        mid = (st + en) // 2

        if get_line_count(mid):
            answer = max(answer, mid)
            st = mid + 1
        else:
            en = mid - 1

    return answer


print(binary_search())
