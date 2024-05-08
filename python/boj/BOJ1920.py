N = int(input())
numbers = list(map(int, input().split()))
M = int(input())
targetNumbers = list(map(int, input().split()))

numbers.sort()


def binarySearch(numbers, val, st, en):

    while st < en:
        mid = (st + en) // 2
        candidate = numbers[mid]

        if candidate > val:
            en = mid
        elif candidate < val:
            st = mid + 1
        else:
            return 1

    return 0

answers = []

for target in targetNumbers:
    result = binarySearch(numbers, target, 0, len(numbers))
    answers.append(result)

answer = '\n'.join(list(map(str, answers)))
print(answer)
