import sys

def binary_search(arr, key):
    st = 0
    en = len(arr)

    while st < en:
        mid = (st + en) // 2

        if arr[mid] == key:
            return True
        elif arr[mid] < key:
            st = mid + 1
        else:
            en = mid

    return False

A,B = tuple(sys.stdin.readline().split())

setA = list(map(int,sys.stdin.readline().split()))
setB = list(map(int,sys.stdin.readline().split()))

setB.sort()

ans = []

for number in setA:
    if binary_search(setB,number) == False:
        ans.append(number)

ans.sort()

sys.stdout.write(str(len(ans)) + '\n')
for number in ans:
    sys.stdout.write(str(number) + ' ')
