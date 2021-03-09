#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int V, leapNode;
vector<vector<Pair>> graph;
vector<bool> isVisited;
vector<int> dists;

void dfs(int nowNode, int nowDist) 
{
    for (Pair nxtInfo : graph[nowNode])
    {
        int nxtNode = nxtInfo.first;
        int nxtDist = nxtInfo.second;

        if (!isVisited[nxtNode])
        {
            isVisited[nxtNode] = true;
            dists[nxtNode] = nowDist + nxtDist;
            dfs(nxtNode, nowDist + nxtDist);
        }
    }
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> V;
    graph.resize(V);
    isVisited.resize(V,false);
    dists.resize(V,0);

    for (int loop = 0; loop < V; ++loop)
    {
        int nowNode;
        cin >> nowNode;
        nowNode--;

        int adjNode, dist;
        int loopCnt = 0;

        while (true)
        {
            cin >> adjNode;

            if (adjNode == -1)
                break;

            cin >> dist;

            adjNode--;

            graph[nowNode].push_back({adjNode, dist});

            loopCnt += 1;
        }

        if (loopCnt == 1)
        {
            leapNode = nowNode;
        }
    }

    //임의의 정점 leapNode로부터 가장 먼 점 A 구하기
    dists[leapNode] = 0;
    isVisited[leapNode] = true;
    dfs(leapNode, 0);
    
    int maxDist = -1;
    int A = 0;

    for (int idx = 0; idx < V; ++idx)
    {
        if (dists[idx] > maxDist)
        {
            maxDist = dists[idx];
            A = idx;
        }
    }

    fill(isVisited.begin(), isVisited.end(), false);
    fill(dists.begin(), dists.end(), 0);

    //A에서 가장 먼 점 구하기
    dists[A] = 0;
    isVisited[A] = true;
    dfs(A, 0);

    maxDist = -1;
    int answer = 0;

    for (int idx = 0; idx < V; ++idx)
    {
        if (dists[idx] > maxDist)
        {
            maxDist = dists[idx];
            answer = idx;
        }
    }

    cout << maxDist << "\n";

    return 0;
}