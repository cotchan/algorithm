#define IMPOSSIBLE 987654321
#define Tuple tuple<int,int,int,int>
#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
#include <cstring>
#include <tuple>
using namespace std;

int my[] = {-1,1,0,0};  //monkey_move
int mx[] = {0,0,-1,1};  //monkey_move
int hy[] = {-2,-2,-1,-1,2,2,1,1};  //horse_move
int hx[] = {1,-1,2,-2,1,-1,2,-2};  //horse_move
int K,W,H;
int visited[31][201][201];
vector<vector<int>> board;

bool isSafe(int y, int x) 
{
    return (0 <= y && y < H && 0 <= x && x < W);
}

int solution() 
{
    int ans = IMPOSSIBLE;
    queue<Tuple> q;
    q.push(make_tuple(0,0,0,K));
    visited[K][0][0] = 1;
    
    while (!q.empty())
    {
        Tuple now = q.front(); q.pop();

        int y,x,time,rest;
        tie(y,x,time,rest) = now;
        
        if (y == H-1 && x == W-1)
        {
            ans = min(ans,time);
            continue;
        }

        for (int dir = 0; dir < 4; ++dir)
        {
            int ny = y + my[dir];
            int nx = x + mx[dir];

            if (!isSafe(ny,nx)) continue;
            else if (board[ny][nx]) continue;
            else if (visited[rest][ny][nx]) continue;

            visited[rest][ny][nx] = time + 1;
            q.push(make_tuple(ny,nx,time+1,rest));
        }

        if (rest >= 1)
        {
            for (int dir = 0; dir < 8; ++dir)
            {
                int ny = y + hy[dir];
                int nx = x + hx[dir];

                if (!isSafe(ny,nx)) continue;
                else if (board[ny][nx]) continue;
                else if (visited[rest-1][ny][nx]) continue;

                visited[rest-1][ny][nx] = time + 1;
                q.push(make_tuple(ny,nx,time+1,rest-1));
            }
        }
    }

    return ans;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> K;
    cin >> W >> H;  //W: 가로, H: 세로
    board.resize(H,vector<int>(W,0));
    
    for (int i = 0; i < H; ++i)
    {
        for (int j = 0; j < W; ++j)
        {
            cin >> board[i][j];
        }
    }

    int answer = solution();

    if (answer == IMPOSSIBLE)
    {
        cout << -1;
    }
    else
    {
        cout << answer;
    }

    return 0;
}