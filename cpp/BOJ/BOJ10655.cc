#define Y first
#define X second
#define Pair pair<int,int>
#include <iostream>
#include <vector>
using namespace std;

int N;
vector<Pair> checkPoints;
vector<int> dists;   //dist[0] = 0-1 사이의 거리, dist[1] = 1-2 사이의 거리

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    dists.resize(N,0);

    for (int loop = 0; loop < N; ++loop)
    {
        int y,x;
        cin >> y >> x;
        checkPoints.push_back({y,x});
    }

    for (int idx = 1; idx < N; ++idx)
    {
        Pair dst = checkPoints[idx];
        Pair src = checkPoints[idx-1];
        int dist = abs(dst.Y - src.Y) + abs(dst.X - src.X);
        dists[idx] = dist;
    }

    int maxv, maxv_idx;
    maxv = -987654321;

    for (int idx = 1; idx < N-1; ++idx)
    {
        //이전 거리
        int before = dists[idx] + dists[idx+1];

        //감소 되는 거리
        Pair dst = checkPoints[idx+1];
        Pair src = checkPoints[idx-1];
        int after = abs(dst.Y - src.Y) + abs(dst.X - src.X);
        
        int diff = before - after;
        if (diff > maxv)
        {
            maxv = diff;
            maxv_idx = idx;
        }
    }

    int answer = 0;
    for (int dist : dists)
    {
        answer += dist;
    }

    answer -= maxv;

    cout << answer << "\n";
    

    return 0;
}