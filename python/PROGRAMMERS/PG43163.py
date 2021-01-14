# https://programmers.co.kr/learn/courses/30/lessons/43163
# 단어 변환

def isPossible(src, dst):

    diff = 0
    for idx in range(len(src)):
        if src[idx] != dst[idx]:
            diff += 1

    if diff == 1:
        return True
    else:
        return False

def dfs(words, now, visited, target, depth, ans):

    for nxt in words:
        if isPossible(now,nxt) and not(visited[nxt]):
            if nxt == target:
                return depth
            else:
                visited[nxt] = 1
                ans = min(ans, dfs(words, nxt, visited, target, depth + 1, ans))
                visited[nxt] = 0

    return ans

def solution(begin, target, words):

    # init
    answer = 987654321

    visited = {}
    for word in words:
        visited[word] = 0

    visited[begin] = 1
    answer = dfs(words, begin, visited, target, 1, answer)

    if answer == 987654321:
        return 0
    else:
        return answer