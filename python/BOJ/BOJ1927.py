# https://www.acmicpc.net/problem/1927
# 최소 힙

import sys
from queue import PriorityQueue

loop = int(sys.stdin.readline())

pq = PriorityQueue()

for _ in range(loop):
    num = int(sys.stdin.readline())
    if num > 0:
        pq.put(num)
    else:
        if pq.empty():
            sys.stdout.write("0\n")
        else:
            sys.stdout.write(str(pq.get()) + "\n")