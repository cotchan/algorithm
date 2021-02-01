import sys
from itertools import combinations

N, M = map(int,sys.stdin.readline().split())

IMPOSSIBLE = 987654321
graph = []
houses = []
chickenShop_postion = {}

for _ in range(N):
    row = list(map(int,sys.stdin.readline().split()))
    graph.append(row)

cnt = 0
for i in range(N):
    for j in range(N):
        if graph[i][j] == 2:
            chickenShop_postion[cnt] = (i,j)
            cnt += 1
        elif graph[i][j] == 1:
            houses.append((i,j))

chickenShop = [i for i in range(cnt)]
combi = combinations(chickenShop, M)
combination_list = list(combi)

answer = IMPOSSIBLE

# 0,1,2
# 0,1,3
# 1,3,4
for candidate in combination_list:
    distance = [IMPOSSIBLE for _ in range(len(houses))]

    # 0,1,2
    for shop_idx in candidate:
        sy,sx = chickenShop_postion[shop_idx]

        for house_idx in range(len(houses)):
            hy,hx = houses[house_idx]
            distance[house_idx] = min(distance[house_idx], (abs(sy-hy) + abs(sx-hx)))

    answer = min(answer, sum(distance))

print(answer)








