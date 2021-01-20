# https://programmers.co.kr/learn/courses/30/lessons/42579
# 베스트앨범

from queue import PriorityQueue

def solution(genres, plays):
    answer = []

    pq = PriorityQueue()
    genresMap = {}
    addInfo = {}

    for idx in range(len(genres)):
        genry = genres[idx]
        playCount = plays[idx]

        if not(genresMap.get(genry)):
            genresMap[genry] = playCount
        else:
            genresMap[genry] += playCount

    # set addInfo
    for genry in genresMap.keys():
        addInfo[genry] = 0

    for idx in range(len(genres)):
        genry = genres[idx]
        pq.put((-genresMap[genry], -plays[idx], idx, genry))

    while not(pq.empty()):
        info = pq.get()

        if addInfo[info[3]] < 2:
            addInfo[info[3]] += 1
            answer.append(info[2])

    return answer