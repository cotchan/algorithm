#define COST first
#define NODE second
#define Pair pair<int,int>
#define Tuple tuple<int,int,int>
#include <iostream>
#include <vector>
#include <tuple>
#include <algorithm>
#include <queue>
#include <map>
using namespace std;

const int INF = 987654321;
int T;
int n,m,t;
int s,g,h;
vector<int> candidates;
vector<vector<Pair>> graph;
map<int,int> answers;

vector<int> djikstra(int st)
{
    vector<int> dist(n,INF);

    priority_queue<Pair> pq;
    dist[st] = 0;
    pq.push({0,st});
    
    while (!pq.empty())
    {
        Pair now = pq.top(); pq.pop();

        if (now.COST < dist[now.NODE])
            continue;

        for (Pair nxt : graph[now.NODE])
        {
            int nxtNode = nxt.first;
            int nxtCost = nxt.second;

            if (dist[nxtNode] > now.COST + nxtCost)
            {
                dist[nxtNode] = now.COST + nxtCost;
                pq.push({dist[nxtNode],nxtNode});
            }
        }
    }

    return dist;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> T;

    for (int test_case = 0; test_case < T; ++test_case)
    {
        for (int i = 0; i < graph.size(); ++i)
            graph[i].clear();

        graph.clear();
        candidates.clear();
        answers.clear();

        //교차로, 도로, 목적지 후보의 갯수
        cin >> n >> m >> t;
        //예술가들 출발지, src, dst
        cin >> s >> g >> h;
        s--; g--; h--;

        graph.resize(n);

        for (int a,b,d,loop = 0; loop < m; ++loop)
        {
            cin >> a >> b >> d;
            a--; b--;
            graph[a].push_back({b,d});
            graph[b].push_back({a,d});
        }   

        for (int candidate,loop = 0; loop < t; ++loop)
        {
            cin >> candidate;
            candidate--;
            candidates.push_back(candidate);
        }     

        vector<int> distFromSrc = djikstra(s);
        vector<int> distFromG = djikstra(g);
        vector<int> distFromH = djikstra(h);
        
        for (int candidate : candidates)
        {
            if (distFromSrc[candidate] == distFromG[s] + distFromG[h] + distFromH[candidate])
            {
                answers[candidate] = distFromSrc[candidate];
            }
            else if (distFromSrc[candidate] == distFromH[s] + distFromH[g] + distFromG[candidate])
            {
                answers[candidate] = distFromSrc[candidate];
            }
        }        
        
        for (auto candidate : answers)
        {
            cout << candidate.first + 1 << " ";
        }

        cout << "\n";
    }

    return 0;
}