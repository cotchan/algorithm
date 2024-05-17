N = int(input())

numbers = list(map(int,input().split()))

numbers.sort()

M = int(input())

targets = list(map(int,input().split()))

def binary_search(value):
    st = 0
    en = N

    while st < en:
        mid = (st + en) // 2
        target = numbers[mid]

        if target == value: return True
        elif target > value: en = mid
        else: st = mid + 1
    
    return False

answers = []

for target in targets:
    if binary_search(target):
        answers.append('1')
    else:
        answers.append('0')

print(' '.join(answers))
