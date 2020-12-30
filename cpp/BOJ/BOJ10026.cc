#define Y first
#define X second
#define Pair pair<int,int>
#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;

int dy[] = {-1,1,0,0};
int dx[] = {0,0,-1,1};
int N;
vector<string> board;
vector<vector<bool>> visited;

bool isSafe(int y, int x)
{
    return (0 <= y && y < N && 0 <= x && x < N);
}

void bfs(int y, int x, bool isRGBYac)
{
    queue<Pair> q;
    q.push({y,x});

    while (!q.empty())
    {
        Pair now = q.front(); q.pop();
        char nowColor = board[now.Y][now.X];

        for (int dir = 0; dir < 4; ++dir)
        {
            int ny = now.Y + dy[dir];
            int nx = now.X + dx[dir];

            if (!isSafe(ny,nx)) continue;
            else if (visited[ny][nx]) continue;

            if (isRGBYac)
            {
                if (nowColor == 'B' && board[ny][nx] != 'B') continue;
                else if (nowColor != 'B' && board[ny][nx] == 'B') continue;
            }
            else
            {
                if (nowColor != board[ny][nx]) continue;
            }

            visited[ny][nx] = true;
            q.push({ny,nx});   
        }
    }
}
int main(void)
{
    ios::sync_with_stdio(0);
    cin >> N;
    visited.resize(N, vector<bool>(N,false));

    for (int i = 0; i < N; ++i)
    {
        string str;
        cin >> str;
        board.push_back(str);
    }

    int normalCompoSize = 0;
    int rgbYacCompoSize = 0;

    //1st bfs
    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
        {
            if (!visited[i][j])
            {
                visited[i][j] = true;
                bfs(i,j,false);
                normalCompoSize += 1;
            }
        }
    }

    //reset visited array
    for (int i = 0; i < N; ++i)
    {
        fill(visited[i].begin(), visited[i].end(), false);
    }
    
    //2nd bfs
    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
        {
            if (!visited[i][j])
            {
                visited[i][j] = true;
                bfs(i,j,true);
                rgbYacCompoSize += 1;
            }
        }
    }

    cout << normalCompoSize << " " << rgbYacCompoSize << "\n";

    return 0;
}