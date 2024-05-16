N, M = map(int,input().split())

numbers = []

for _ in range(N):
    number = int(input())
    numbers.append(number)

numbers.sort()    

st = 0
en = 0
answer = 2_000_000_001

while st <= en and en < N:
    diff = numbers[en] - numbers[st]
    
    # 차이가 M 이상이면서 제일 작은 경우
    
    if diff >= M:
        answer = min(answer,diff)
        st += 1
    else:
        en += 1

print(answer)
