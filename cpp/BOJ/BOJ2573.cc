#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <queue>
#include <map>
using namespace std;

const int OFFSET = 1000;
int dy[] = {-1,1,0,0};
int dx[] = {0,0,-1,1};
int N,M;
vector<vector<int>> board;
vector<vector<bool>> visited;
map<int,int> ices;

bool isSafe(int y, int x) {
    return (0 <= y && y < N && 0 <= x && x < M);
}

void bfs(int y, int x)
{
    queue<Pair> q;
    q.push({y,x});

    while (!q.empty())
    {
        Pair now = q.front(); q.pop();

        for (int dir = 0; dir < 4; ++dir)
        {
            int ny = now.first + dy[dir];
            int nx = now.second + dx[dir];

            if (!visited[ny][nx] && board[ny][nx])
            {
                visited[ny][nx] = true;
                q.push({ny,nx});
            } 
        }
    }
}

int main()
{
    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N,vector<int>(M,0));
    visited.resize(N,vector<bool>(M,false));

    for (int i = 0; i < N; ++i)
    {
        for (int x,j = 0; j < M; ++j)
        {
            cin >> x;

            if (x > 0)
            {
                int iceKey = i * OFFSET + j;
                ices[iceKey] = x;
            }

            board[i][j] = x;
        }
    }

    bool isSeperated = false;
    int time = 0;

    while (!isSeperated) 
    {
        if (ices.size() == 0)
        {
            break;
        }
        
        for (int i = 0; i < N; ++i)
        {
            fill(visited[i].begin(), visited[i].end(), false);
        }

        vector<Pair> removeList;

        for (auto ice : ices)
        {
            int seaCount = 0;
            int y = ice.first / OFFSET;
            int x = ice.first % OFFSET;

            for (int dir = 0; dir < 4; ++dir)
            {
                int ny = y + dy[dir];
                int nx = x + dx[dir];
                
                if (board[ny][nx] == 0)
                {
                    seaCount += 1;
                }
            }

            if (seaCount >= board[y][x])
            {
                removeList.push_back({y,x});
            }
            else
            {
                board[y][x] -= seaCount;
            }
        }

        for (Pair p : removeList)
        {
            int y = p.first;
            int x = p.second;
            int key = y * OFFSET + x;
            ices.erase(key);
            board[y][x] = 0;
        }

        //bfs
        int componentSize = 0;

        for (auto ice : ices)
        {
            int y = ice.first / OFFSET;
            int x = ice.first % OFFSET;
            
            if (!visited[y][x])
            {
                visited[y][x] = true;
                bfs(y,x);
                componentSize += 1;
            }
        }

        if (componentSize > 1)
        {
            isSeperated = true;
        }

        time += 1;
    }
    
    if (isSeperated)
    {
        cout << time << "\n";
    }
    else
    {
        cout << 0 << "\n";
    }

    return 0;
}