from collections import OrderedDict

K, L = map(int, input().split())

od = OrderedDict()

for _ in range(L):
    number = str(input())

    if number in od:
        od.move_to_end(number)
    else:
        od[number] = 1

number = 0

answer = []

for name in od.keys():
    if number >= K:
        break
    answer.append(name)
    number += 1

print('\n'.join(answer))
