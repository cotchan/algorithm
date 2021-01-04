import sys

def binary_search(arr, key):
    st = 0
    en = len(arr)

    while st < en:
        mid = (st + en) // 2

        if arr[mid] == key:
            return 1
        elif arr[mid] < key:
            st = mid + 1
        else:
            en = mid

    return 0

n = int(sys.stdin.readline())
numbers = list(map(int,sys.stdin.readline().split()))

n = int(sys.stdin.readline())
querys = list(map(int,sys.stdin.readline().split()))

numbers.sort()

for key in querys:
    sys.stdout.write(str(binary_search(numbers,key)) + ' ')