#define FALSE 0
#define TRUE 1
#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;

const int NMAX = 1000001;
int N;
vector<vector<int>> graph;
vector<vector<int>> originGraph;
vector<bool> isVisited;
int dp[2][NMAX];

void dfs(int node)
{
    for (int nxt : originGraph[node])
    {
        if (!isVisited[nxt])
        {
            isVisited[nxt] = true;
            graph[node].push_back(nxt);
            dfs(nxt);
        }
    }
}

int func(int nodeNumber, int isEarly)
{
    if (dp[isEarly][nodeNumber] != -1)
        return dp[isEarly][nodeNumber];

    int result;

    if (isEarly)
    {
        result = 1;
        for (int nxt : graph[nodeNumber])
        {
            result += min(func(nxt,TRUE), func(nxt,FALSE));
        }
    }
    else
    {
        result = 0;
        for (int nxt : graph[nodeNumber])
        {
            result += func(nxt,TRUE);
        }
    }

    return dp[isEarly][nodeNumber] = result;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    graph.resize(N);
    originGraph.resize(N);
    isVisited.resize(N,false);
    memset(dp,-1,sizeof(dp));

    for (int u,v,loop = 0; loop < N-1; ++loop)
    {
        cin >> u >> v;
        u--; v--;

        originGraph[u].push_back(v);
        originGraph[v].push_back(u);
    }

    isVisited[0] = true;
    dfs(0);

    cout << min(func(0,TRUE), func(0,FALSE)) << "\n";

    return 0;
}