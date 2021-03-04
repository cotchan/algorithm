#include <iostream>
#include <vector>
#include <sstream>
#include <string>
using namespace std;

int N;
string emptyLine;
vector<int> buildTimes, preCnts, realBuildTimes;
vector<vector<int>> graph, reverseGraph;

int findNxt() {

    for (int idx = 0; idx < preCnts.size(); ++idx)
    {
        if (preCnts[idx] == 0)
            return idx;
    }

    return -1;
}
int main(void) {

    ios::sync_with_stdio(0);

    cin >> N;
    getline(cin, emptyLine);
    buildTimes.resize(N,0);
    realBuildTimes.resize(N,0);
    preCnts.resize(N,0);
    graph.resize(N);
    reverseGraph.resize(N);

    for (int idx = 0; idx < N; ++idx)
    {
        string line;
        getline(cin, line);
        stringstream ss(line);
        string token;
        vector<int> preInfoList;

        while (getline(ss, token, ' '))
        {
            int preNumber = atoi(token.c_str());
            preInfoList.push_back(preNumber);
        }

        for (int loop = 0; loop < preInfoList.size(); ++loop)
        {
            if (loop == 0)
            {
                buildTimes[idx] = preInfoList[loop];
            }
            else if (preInfoList[loop] != -1)
            {
                preCnts[idx]++;
                int preNumber = preInfoList[loop];
                preNumber--;
                graph[preNumber].push_back(idx);
                reverseGraph[idx].push_back(preNumber);
            }
        }
    }

    int candidate;
    vector<int> buildOrders;

    while ((candidate = findNxt()) != -1)
    {
        buildOrders.push_back(candidate);

        for (int nxtBuilding : graph[candidate])
        {
            preCnts[nxtBuilding]--;
        }

        preCnts[candidate] = -1;
    }

    for (int nowBuilding : buildOrders)
    {
        int myRealBuildTime = 0;
        
        for (int preBuilding : reverseGraph[nowBuilding])
        {
            myRealBuildTime = max(myRealBuildTime, realBuildTimes[preBuilding]);
        }

        myRealBuildTime += buildTimes[nowBuilding];
        realBuildTimes[nowBuilding] = myRealBuildTime;
    }

    for (int realBuildTime : realBuildTimes)
    {
        cout << realBuildTime << "\n";
    }
    

    return 0;
}