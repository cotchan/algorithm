#define EVEN 0
#define ODD 1
#define IMPOSSIBLE 987654321
#define POS first
#define TIME second
#define Pair pair<int,int>
#define MIN_POS 0
#define MAX_POS 500000
#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

int N,K;
queue<Pair> q;
vector<Pair> brotherPostions;
vector<int> visited[2]; //EVEN, ODD check

int main(void) {

    cin >> N >> K;

    for (int i = 0; i < 2; ++i)
    {
        visited[i].resize(MAX_POS + 1, -1);
    }

    //init
    int _time = 0;

    while (K <= MAX_POS)
    {
        brotherPostions.push_back({K, _time});
        _time += 1;
        K += _time;
    }

    q.push({N,0});
    visited[EVEN][N] = 0;

    while (!q.empty())
    {
        Pair now = q.front(); q.pop();

        //now time == even number, next time == odd number
        if (now.TIME % 2 == EVEN)
        {
            int nxtPos = now.POS + 1;

            if (nxtPos <= MAX_POS && ((visited[ODD][nxtPos] == -1) || now.TIME + 1 < visited[ODD][nxtPos]))
            {
                visited[ODD][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }

            nxtPos = now.POS - 1;
            
            if (nxtPos >= MIN_POS && ((visited[ODD][nxtPos] == -1) || now.TIME + 1 < visited[ODD][nxtPos]))
            {
                visited[ODD][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }

            nxtPos = now.POS * 2;

            if (nxtPos <= MAX_POS && ((visited[ODD][nxtPos] == -1) || now.TIME + 1 < visited[ODD][nxtPos]))
            {
                visited[ODD][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }
        }
        //now time == odd number, next time == even number
        else
        {
            int nxtPos = now.POS + 1;

            if (nxtPos <= MAX_POS && ((visited[EVEN][nxtPos] == -1) || now.TIME + 1 < visited[EVEN][nxtPos]))
            {
                visited[EVEN][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }

            nxtPos = now.POS - 1;
            
            if (nxtPos >= MIN_POS && ((visited[EVEN][nxtPos] == -1) || now.TIME + 1 < visited[EVEN][nxtPos]))
            {
                visited[EVEN][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }

            nxtPos = now.POS * 2;

            if (nxtPos <= MAX_POS && ((visited[EVEN][nxtPos] == -1) || now.TIME + 1 < visited[EVEN][nxtPos]))
            {
                visited[EVEN][nxtPos] = now.TIME + 1;
                q.push({nxtPos,now.TIME + 1});
            }
        }
    }
    
    int ans = IMPOSSIBLE;

    for (Pair p : brotherPostions)
    {
        int pos = p.first;
        int time = p.second;

        if (time % 2 == EVEN)
        {
            if ((visited[EVEN][pos] != -1) && visited[EVEN][pos] <= time && (abs(visited[EVEN][pos] - time) % 2 == 0))
            {
                //정답후보
                ans = min(ans,time);
            }
        }   
        else
        {
            if ((visited[ODD][pos] != -1) && visited[ODD][pos] <= time && (abs(visited[ODD][pos] - time) % 2 == 0))
            {
                //정답후보
                ans = min(ans,time);
            }
        }
    }

    if (ans == IMPOSSIBLE)
    {
        cout << -1;
    }
    else 
    {
        cout << ans;    
    }

    return 0;
}