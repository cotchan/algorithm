# https://www.acmicpc.net/problem/2467
# 용액

import sys

N = int(sys.stdin.readline())
liquids = list(map(int, sys.stdin.readline().split()))
liquids = sorted(liquids)

answer = []
value = 2100000000

for idx in range(len(liquids)):
    left = idx + 1
    right = len(liquids)

    while left < right:
        mid = (left + right) // 2
        candidate = liquids[idx] + liquids[mid]

        if abs(candidate) < value:
            answer.clear()
            answer.append(liquids[idx])
            answer.append(liquids[mid])
            value = abs(candidate)

        if candidate >= 0:
            right = mid
        elif candidate < 0:
            left = mid + 1

answer = sorted(answer)
for num in answer:
    sys.stdout.write(str(num) + ' ')