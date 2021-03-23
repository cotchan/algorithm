#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N,M;
vector<int> parents, childs;
vector<vector<int>> parentGraph, childGraph;

int findNxt() {
    
    for (int idx = 0; idx < N; ++idx)
    {
        if (parents[idx] == 0)
            return idx;
    }

    return -1;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    parentGraph.resize(N);
    childGraph.resize(N);
    parents.resize(N,0);

    for (int pickCnt,loop = 0; loop < M; ++loop)
    {
        cin >> pickCnt;
        vector<int> orders;

        for (int singer,i = 0; i < pickCnt; ++i)
        {
            cin >> singer;
            singer--;
            orders.push_back(singer);
        }

        int singerSize = orders.size();
        
        for (int i = 0; i < singerSize-1; ++i)
        {
            int parent = orders[i];

            for (int j = i + 1; j < singerSize; ++j)
            {
                int child = orders[j];

                parents[child]++;
                parentGraph[child].push_back(parent);
                childGraph[parent].push_back(child);
            }
        }

    }

    int pickCnt = 0;
    int currentNode = 0;
    vector<int> result;

    while ((currentNode = findNxt()) != -1)
    {
        if (pickCnt == N)
            break;

        pickCnt += 1;

        parents[currentNode] = -1;

        for (int child : childGraph[currentNode])
        {
            parents[child]--;
        }

        result.push_back(currentNode);
    }

    if (pickCnt == N)
    {
        for (int singer : result)
        {
            cout << singer + 1<< "\n";
        }
    }
    else
    {
        cout << 0;
    }

    return 0;
}