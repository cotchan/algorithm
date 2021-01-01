#define COST first
#define NODE second
#define INF 987654321
#define Pair pair<int,int>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int V,E, stNode;
vector<vector<Pair>> graph;
priority_queue<Pair> pq;

vector<int> djikstra()
{
    vector<int> dist(V,INF);
    pq.push({0,stNode});
    dist[stNode] = 0;

    while (!pq.empty())
    {
        Pair now = pq.top(); pq.pop();
        now.COST = -now.COST;

        if (now.COST > dist[now.NODE])
            continue;

        for (int i = 0; i < graph[now.NODE].size(); ++i)
        {
            int nxtNode = graph[now.NODE][i].first;
            int nxtWeight = graph[now.NODE][i].second;

            if (dist[nxtNode] > now.COST + nxtWeight)
            {
                dist[nxtNode] = now.COST + nxtWeight;
                pq.push({-dist[nxtNode], nxtNode});
            }
        }
    }

    return dist;
}

int main(void) {

    ios::sync_with_stdio(0);

    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> V >> E >> stNode;
    graph.resize(V);
    stNode--;

    for (int loop = 0; loop < E; ++loop)
    {
        int u,v,w;
        cin >> u >> v >> w;
        u--; v--;
        graph[u].push_back({v,w});
    }

    vector<int> result = djikstra();
    
    for (int dist : result)
    {
        if (dist == INF)
            cout << "INF\n";
        else 
            cout << dist << "\n";
    }

    return 0;
}