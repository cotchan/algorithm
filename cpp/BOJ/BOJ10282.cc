#define TIME first
#define COMPUTER second
#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

const int INF = 987654321;
int n,d,c;
priority_queue<Pair> pq;
vector<vector<Pair>> graph;

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int t;
    cin >> t;

    for (int test_case = 0; test_case < t; ++test_case)
    {
        cin >> n >> d >> c;
        c--;

        for (int i = 0 ; i < graph.size(); ++i)
            graph[i].clear();
        
        graph.clear();
        graph.resize(n);

        for (int a,b,s,loop = 0; loop < d; ++loop)
        {   
            cin >> a >> b >> s;
            a--; b--;
            graph[b].push_back({a,s});
        }

        vector<int> dist(n,INF);
        dist[c] = 0;
        pq.push({0,c});
        
        while (!pq.empty())
        {
            Pair now = pq.top(); pq.pop();
            now.TIME = -now.TIME;

            if (now.TIME < dist[now.COMPUTER])
                continue;

            for (Pair nxt : graph[now.COMPUTER])
            {
                int nxtCom = nxt.first;
                int nxtTime = nxt.second;

                if (dist[nxtCom] > now.TIME + nxtTime)
                {
                    dist[nxtCom] = now.TIME + nxtTime;
                    pq.push({-dist[nxtCom],nxtCom});
                }
            }
        }
        
        int cnt = 1;
        int maxv = 0;
        
        for (int i = 0 ; i < n; ++i)
        {
            if (i != c && dist[i] != INF)
            {
                maxv = max(maxv,dist[i]);
                cnt += 1;
            }
        }

        cout << cnt << " " << maxv << "\n";
    }

    return 0;
}