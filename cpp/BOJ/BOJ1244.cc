#include <iostream>
#include <vector>
using namespace std;

const int BOY = 1;
const int GIRL = 2;
const int ON = 1;
const int OFF = -1;
const int NEW_LINE_OFFSET = 20;

int N, studentCnt;
vector<int> switchs;

bool isSafe(int idx) {
    return (0 <= idx && idx < N);
}

void transSwitches(int gender, int number) {

    if (gender == BOY)
    {
        //number는 1부터 시작함
        int idx = number;

        while (idx <= N)
        {
            switchs[idx-1] = -switchs[idx-1];
            idx += number;
        }
    }
    //gender == GIRL
    else
    {
        int ref = number-1;
        int offset = 1;
        int range = 1;
        int lower_bound = ref;

        //1씩 늘리거나 줄이면서 범위 확인
        while (isSafe(ref + offset) && isSafe(ref - offset))
        {
            if (switchs[ref - offset] == switchs[ref + offset])
            {
                lower_bound = ref - offset;
                range += 2;
            }
            else
            {
                break;
            }
        
            offset += 1;
        }

        //범위 내의 모든 스위치 체인지
        for (int idx = lower_bound; idx < lower_bound + range; ++idx)
        {
            switchs[idx] = -switchs[idx];
        }
    }
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    for (int state,loop = 0; loop < N; ++loop)
    {
        cin >> state;
        if (state == 0)
        {
            switchs.push_back(OFF);
        }
        else
        {
            switchs.push_back(ON);
        }
        
    }

    cin >> studentCnt;

    for (int gender, number,loop = 0; loop < studentCnt; ++loop)
    {
        cin >> gender >> number;
        transSwitches(gender,number);
    }

    int loopCnt = 0;

    for (int state : switchs)
    {
        if (state == ON)
            cout << state << " ";
        else
            cout << 0 << " ";

        loopCnt += 1;

        if (loopCnt % NEW_LINE_OFFSET == 0)
            cout << "\n";
    }

    return 0;
}