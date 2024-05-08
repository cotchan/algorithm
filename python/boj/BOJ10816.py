from os import sys

N = int(input())
numbers = list(map(int, input().split()))

M = int(input())
targets = list(map(int, input().split()))

numbers.sort()


def getLowerBound(numbers, target, st, en):
    result = sys.maxsize

    while st < en:
        mid = (st + en) // 2
        candidate = numbers[mid]

        if candidate >= target:
            if candidate == target:
                result = min(result, mid)
            en = mid
        elif candidate < target:
            st = mid + 1

    return result


def getUpperBound(numbers, target, st, en):
    result = -sys.maxsize - 1

    while st < en:
        mid = (st + en) // 2
        candidate = numbers[mid]

        if candidate > target:
            en = mid
        elif candidate <= target:
            if candidate == target:
                result = max(result, mid)
            st = mid + 1

    return result


answers = []
numberSize = len(numbers)
for target in targets:
    lower = getLowerBound(numbers, target, 0, numberSize)
    upper = getUpperBound(numbers, target, 0, numberSize)

    if lower == sys.maxsize or upper == -sys.maxsize - 1:
        answers.append(0)
    else:
        answers.append(upper - lower + 1)

print(" ".join(list(map(str, answers))))
