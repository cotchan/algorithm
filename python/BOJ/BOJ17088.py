# https://www.acmicpc.net/problem/17088

import sys
import copy

dy = [-1,0,1]
dx = [-1,0,1]

N = int(sys.stdin.readline())
arr = list(map(int,sys.stdin.readline().split()))

if N <= 2:
    print(0)
else:
    answer = sys.maxsize
    for i in range(3):
        for j in range(3):
            tmp = copy.deepcopy(arr)
            tmp[0] = arr[0] + dy[i]
            tmp[N-1] = arr[N-1] + dx[j]
            d = (tmp[N-1] - tmp[0]) // (N-1)

            dCnt = 1
            for idx in range(1,N-1):
                tmp[idx] = tmp[0] + (d * dCnt)
                dCnt += 1

            isCandidate = True

            for idx in range(N-1):
                if tmp[idx+1] - tmp[idx] != d:
                    isCandidate = False
                    break

            if not(isCandidate):
                continue

            changeSize = 0

            for idx in range(N):
                if abs(arr[idx] - tmp[idx]) >= 2:
                    isCandidate = False
                    break
                else:
                    changeSize += abs(arr[idx] - tmp[idx])

            if not(isCandidate):
                continue

            answer = min(answer, changeSize)

    if answer == sys.maxsize:
        print(-1)
    else:
        print(answer)