M, N = map(int,input().split())
snacks = list(map(int,input().split()))

def get_snack_count(length):
    result = 0

    for snack in snacks:
        result += (snack // length)

    return result


def binary_search(children_size):
    st = 1
    en = max(snacks)
    result = 0

    while st <= en:
        mid = (st + en) // 2

        snack_count = get_snack_count(mid)
        
        if snack_count >= children_size:
            result = max(result,mid)
            st = mid + 1
        else:
            en = mid - 1

    return result

print(binary_search(M))


