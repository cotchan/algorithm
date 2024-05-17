import sys

N, S = map(int, input().split())

numbers = list(map(int,input().split()))

st = 0
en = 0
answer = sys.maxsize
sum = numbers[en]

while st <= en and en < N:
    if sum >= S:
        answer = min(answer, en-st+1)
        sum -= numbers[st]
        st += 1
    else: # sum < S
        en += 1
        if en == N: break

        sum += numbers[en]

result = 0 if answer == sys.maxsize else answer
print(result)
