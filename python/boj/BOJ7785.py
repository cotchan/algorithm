n = int(input())

dict_ = {}

for _ in range(n):
    name, action = map(str, input().split())

    if dict_.get(name):
        dict_.pop(name)
    else:
        dict_[name] = 1

answer = []

for name in dict_.keys():
    answer.append(name)

answer.sort(reverse=True)

ans = '\n'.join(answer)
print(ans)
