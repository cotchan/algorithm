#define TIME first
#define NODE second
#define INF 987654321
#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N,M,X;
vector<vector<Pair>> graph, reverseGraph;

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M >> X;
    X--;

    graph.resize(N);
    reverseGraph.resize(N);

    for (int loop = 0; loop < M; ++loop)
    {
        int st,en,time;
        cin >> st >> en >> time;
        st--; en--;
        graph[st].push_back({en,time});
        reverseGraph[en].push_back({st,time});
    }

    //djikstra_1
    priority_queue<Pair> pq;
    vector<int> dist(N,INF);
    dist[X] = 0;
    pq.push({0,X});

    while (!pq.empty())
    {
        Pair now = pq.top(); pq.pop();
        now.TIME = -now.TIME;

        if (now.TIME > dist[now.NODE])
            continue;

        for (Pair nxt : graph[now.NODE])
        {
            int nxtNode = nxt.first;
            int nxtTime = nxt.second;

            if (dist[nxtNode] > now.TIME + nxtTime)
            {
                dist[nxtNode] = now.TIME + nxtTime;
                pq.push({-dist[nxtNode],nxtNode});
            }
        }
    }

    //djikstra_2
    vector<int> reverseDist(N,INF);
    reverseDist[X] = 0;
    pq.push({0,X});

    while (!pq.empty())
    {
        Pair now = pq.top(); pq.pop();
        now.TIME = -now.TIME;

        if (now.TIME > reverseDist[now.NODE])
            continue;

        for (Pair nxt : reverseGraph[now.NODE])
        {
            int nxtNode = nxt.first;
            int nxtTime = nxt.second;

            if (reverseDist[nxtNode] > now.TIME + nxtTime)
            {
                reverseDist[nxtNode] = now.TIME + nxtTime;
                pq.push({-reverseDist[nxtNode],nxtNode});
            }
        }
    }

    int maxv = -1;
    for (int idx = 0; idx < N; ++idx)
    {
        maxv = max(maxv,dist[idx] + reverseDist[idx]);
    }

    cout << maxv;

    return 0;
}