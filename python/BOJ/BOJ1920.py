import sys

n = int(sys.stdin.readline())
numbers = list(map(int,sys.stdin.readline().split()))

queryCnt = int(sys.stdin.readline())
querys = list(map(int,sys.stdin.readline().split()))

numbers.sort()

for number in querys:
    # 이분탐색
    st = 0
    en = len(numbers) - 1

    isFind = False

    while st <= en:
        mid = (st+en) // 2

        if numbers[mid] == number:
            isFind = True
            break
        elif numbers[mid] < number:
            st = mid + 1
        else:
            en = mid - 1

    if isFind == True:
        sys.stdout.write(str(1) + '\n')
    else:
        sys.stdout.write(str(0) + '\n')