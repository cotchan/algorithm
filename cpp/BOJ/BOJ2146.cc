#define Y first
#define X second
#define NUMBER first
#define TIME second
#define Tuple tuple<int,int,int>
#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <tuple>
#include <queue>
#include <algorithm>
using namespace std;

int dy[] = {-1,1,0,0};
int dx[] = {0,0,-1,1};

int N;
vector<vector<int>> board;
vector<vector<bool>> visited;
vector<vector<Pair>> newBoard;
queue<Tuple> gQueue;    //global variable Queue

bool isSafe(int y, int x) {
    return (0 <= y && y < N && 0 <= x && x < N);
}

vector<Pair> bfs(int y, int x)
{
    vector<Pair> result;
    queue<Pair> q;
    q.push({y,x});
    result.push_back({y,x});

    while (!q.empty())
    {
        Pair now = q.front(); q.pop();

        for (int dir = 0; dir < 4; ++dir)
        {
            int ny = now.Y + dy[dir];
            int nx = now.X + dx[dir];

            if (isSafe(ny,nx) && board[ny][nx] && !visited[ny][nx])
            {
                visited[ny][nx] = true;
                result.push_back({ny,nx});
                q.push({ny,nx});
            }
        }
    }

    return result;
}

int solution()
{
    int _time = 1;
    int result = 987654321;

    while (gQueue.size())
    {
        int loop = gQueue.size();

        while (loop--)
        {
            Tuple now = gQueue.front(); gQueue.pop();

            int y,x,componentNumber;
            tie(y,x,componentNumber) = now;
            
            for (int dir = 0; dir < 4; ++dir)
            {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                if (!isSafe(ny,nx)) continue;
                else if (newBoard[ny][nx].NUMBER == componentNumber) continue;
                
                //0이거나, 다른 숫자이거나
                if (newBoard[ny][nx].NUMBER == 0)
                {
                    newBoard[ny][nx] = {componentNumber, _time};
                    gQueue.push({ny,nx,componentNumber});
                }
                else 
                {
                    int myComponentTime = newBoard[y][x].TIME;
                    int otherComponentTime = newBoard[ny][nx].TIME;
                    result = min(result, myComponentTime + otherComponentTime);
                }
            }
        }

        _time += 1;
    }

    return result;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    board.resize(N,vector<int>(N,0));
    newBoard.resize(N,vector<Pair>(N, {0,0}));
    visited.resize(N,vector<bool>(N,false));

    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
        {
            cin >> board[i][j];
        }
    }

    //bfs (for init)
    int componentNumber = 1;

    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < N; ++j)
        {
            if (board[i][j] && !visited[i][j])
            {
                visited[i][j] = true;
                vector<Pair> component = bfs(i,j);

                for (Pair p : component)
                {
                    bool isAdj = false;
                    newBoard[p.Y][p.X] = {componentNumber, 0};

                    //가장 자리에 있는 애들만 구하기 
                    for (int dir = 0; dir < 4; ++dir)
                    {
                        int ny = p.Y + dy[dir];
                        int nx = p.X + dx[dir];
                        
                        if (isSafe(ny,nx) && board[ny][nx] == 0)
                        {
                            isAdj = true;
                        }
                    }

                    if (isAdj)
                    {
                        gQueue.push(make_tuple(p.Y,p.X,componentNumber));
                    }
                }

                componentNumber += 1;
            }
        }
    }

    //Solved
    cout << solution() << "\n";
    
    return 0;
}