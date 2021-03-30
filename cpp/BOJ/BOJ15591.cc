#define ll long long 
#define Pair pair<int,ll>
#include <iostream>
#include <tuple>
#include <vector>
#include <queue>
using namespace std;

int N,Q;
vector<vector<Pair>> graph;

int solve(int videoIdx, ll k)
{
    int result = 0;
    vector<bool> isVisited(N,false);
    queue<int> q;
    q.push(videoIdx);
    isVisited[videoIdx] = true;

    while (!q.empty())
    {
        int now = q.front(); q.pop();

        for (Pair adj : graph[now])
        {
            int nxtVideo = adj.first;
            ll weight = adj.second;
            
            if (!isVisited[nxtVideo] && weight >= k)
            {
                isVisited[nxtVideo] = true;
                q.push({nxtVideo});
                result += 1;
            }
        }
    }

    return result;
}
int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> Q;
    graph.resize(N);

    for (int loop = 0; loop < N-1; ++loop)
    {
        int p,q;
        ll r;
        cin >> p >> q >> r;
        p--; q--;
        
        graph[p].push_back({q,r});
        graph[q].push_back({p,r});
    }

    for (int loop = 0; loop < Q; ++loop)
    {
        ll k;
        int v;
        cin >> k >> v;
        cout << solve(v-1,k) << "\n";
    }

    return 0;
}