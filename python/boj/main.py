onePanSize = int(input())

TOTAL_SIZE = 3
MOVE_CNT = 0
answer = []


def func(size, from_, to_):
    global MOVE_CNT

    if size == 1:
        answer.append((from_, to_))
        MOVE_CNT += 1
        return None

    rest = TOTAL_SIZE - (from_ + to_)

    func(size - 1, from_, rest)
    answer.append((from_, to_))
    MOVE_CNT += 1
    func(size - 1, rest, to_)


func(onePanSize, 0, 2)
print(MOVE_CNT)
for from_, to_ in answer:
    print('%d %d' % (from_+1, to_+1))
