import sys

def binary_search_1(arr, key):
    idx = -1
    st = 0
    en = len(arr) - 1

    while st <= en:
        mid = (st+en) // 2

        if arr[mid] == key:
            idx = mid
            st = mid + 1
        elif arr[mid] < key:
            st = mid + 1
        else:
            en = mid - 1

    return idx

def binary_search_2(arr, key):
    idx = -1
    st = 0
    en = len(arr) - 1

    while st <= en:
        mid = (st+en) // 2

        if arr[mid] == key:
            idx = mid
            en = mid - 1
        elif arr[mid] < key:
            st = mid + 1
        else:
            en = mid - 1

    return idx


n = int(sys.stdin.readline())
cards = list(map(int,sys.stdin.readline().split()))

n = int(sys.stdin.readline())
querys = list(map(int,sys.stdin.readline().split()))

cards.sort()

for number in querys:
    upper_bound = binary_search_1(cards,number)
    lower_bound = binary_search_2(cards,number)

    result = upper_bound - lower_bound

    if upper_bound == -1 or lower_bound == -1:
        sys.stdout.write(str(0) + ' ')
    elif result > 0:
        sys.stdout.write(str(result + 1) + ' ')
    else:
        sys.stdout.write(str(1) + ' ')