# https://www.acmicpc.net/problem/12931

import sys

N = int(sys.stdin.readline())
numbers = list(map(int, sys.stdin.readline().split()))

result = 0

while True:
    isFinished = True
    requiredIncrement = False

    for idx in range(N):
        if numbers[idx] != 0:
            isFinished = False

        if numbers[idx] % 2 == 1:
            requiredIncrement = True
            result += 1
            numbers[idx] -= 1

    if isFinished:
        break

    if not requiredIncrement:
        for idx in range(N):
            numbers[idx] //= 2

        result += 1

print(result)