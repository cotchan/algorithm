#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N;
vector<int> workTime, jobOrders, preJobCnts, realWorkTime;
vector<vector<int> > adjGraph, preAdjGraph;

int findNxtJob() {

    for (int i = 0; i < preJobCnts.size(); ++i)
    {
        if (preJobCnts[i] == 0)
            return i;
    }

    return -1;
}
int main(void) {

    ios::sync_with_stdio(0);
    cin >> N;
    workTime.resize(N,0);
    preJobCnts.resize(N,0);
    realWorkTime.resize(N,0);
    adjGraph.resize(N);
    preAdjGraph.resize(N);

    for (int i = 0; i < N; ++i)
    {
        int myWokrTime, preJobCnt;
        cin >> myWokrTime >> preJobCnt;
        workTime[i] = myWokrTime;

        for (int loop = 0; loop < preJobCnt; ++loop)
        {
            int preJob;
            cin >> preJob;
            preJob--;
            preJobCnts[i]++;
            adjGraph[preJob].push_back(i);
            preAdjGraph[i].push_back(preJob);
        }
    }

    int nowJob = 0;

    while ((nowJob = findNxtJob()) != -1)
    {
        jobOrders.push_back(nowJob);            

        for (int i = 0; i < adjGraph[nowJob].size(); ++i)
        {
            int nxtJob = adjGraph[nowJob][i];
            preJobCnts[nxtJob]--;
        }        

        preJobCnts[nowJob] = -1;
    }

    for (int i = 0; i < jobOrders.size(); ++i)
    {
        int nowJob = jobOrders[i];

        for (int j = 0; j < preAdjGraph[nowJob].size(); ++j)
        {
            int preJob = preAdjGraph[nowJob][j];
            realWorkTime[nowJob] = max(realWorkTime[nowJob], realWorkTime[preJob]);
        }

        realWorkTime[nowJob] += workTime[nowJob];
    }

    int maxv = -1;
    for (int i = 0; i < realWorkTime.size(); ++i)
        maxv = max(maxv, realWorkTime[i]);

    cout << maxv << "\n";

    return 0;
}