# https://programmers.co.kr/learn/courses/30/lessons/49994

dirSet = {}
dy = [-1,0,0,1]
dx = [0,1,-1,0]

def calculateState(offset,y,x):
    return (offset*y +x)

def isSafe(nSize,y,x):
    return (0 <= y and y < nSize and 0 <= x and x < nSize)

def solution(dirs):

    MAP_SIZE = 11
    POSITION_OFFSET = 11
    OFFSET_MAX = 121

    visited = [[False for _ in range(OFFSET_MAX)] for _ in range(OFFSET_MAX)]
    dirSet['U'] = 0
    dirSet['R'] = 1
    dirSet['L'] = 2
    dirSet['D'] = 3

    now_y = 5
    now_x = 5
    answer = 0

    for dir in dirs:
        dir_idx = dirSet.get(dir)

        ny = now_y + dy[dir_idx]
        nx = now_x + dx[dir_idx]

        if isSafe(MAP_SIZE,ny,nx):

            st = calculateState(POSITION_OFFSET,now_y,now_x)
            en = calculateState(POSITION_OFFSET,ny,nx)
            if not(visited[st][en]) and not(visited[en][st]):
                visited[st][en] = True
                visited[en][st] = True
                answer += 1

            now_y = ny
            now_x = nx

    return answer