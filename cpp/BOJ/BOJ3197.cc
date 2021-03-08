#define Y first
#define X second
#define Pair pair<int,int>
#define Tuple tuple<int,int,int>
#include <iostream>
#include <queue>
#include <string>
#include <vector>
#include <tuple>
using namespace std;

const int EMPTY = 0;
const int WALL = 1;
const int SWAN = 2;

int dy[] = {-1,1,0,0};
int dx[] = {0,0,-1,1};

int N,M;
vector<string> board;
vector<vector<int>> meltCntBoard;
vector<vector<bool>> visited;
vector<Pair> swanPos;
queue<Pair> q;

bool isSafe(int y, int x) {
    return (0 <= y && y < N && 0 <= x && x < M);
}

bool getMeetingDay(int afterDayCnt) {

    //visited 초기화
    vector<vector<bool>> isVisited;
    isVisited.resize(N,vector<bool>(M,false));

    vector<vector<int>> tmpBoard;
    tmpBoard.resize(N,vector<int>(M,WALL));

    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < M; ++j)
        {
            if (meltCntBoard[i][j] > afterDayCnt)
            {
                tmpBoard[i][j] = WALL;
            }
            else
            {
                //이동 가능:: meltCntBoard[i][j] <= afterDayCnt
                tmpBoard[i][j] = EMPTY; 
            }
        }
    }

    int stY = swanPos[0].Y;
    int stX = swanPos[0].X;
    int enY = swanPos[1].Y;
    int enX = swanPos[1].X;

    queue<Pair> que;
    
    que.push({stY,stX});
    isVisited[stY][stX] = true;
    bool isFindSwan = false;

    while (!que.empty())
    {
        Pair now = que.front(); que.pop();
        
        if (now.Y == enY && now.X == enX)
        {
            isFindSwan = true;
            break;
        }
        
        for (int dir = 0; dir < 4; ++dir)
        {
            int ny = now.Y + dy[dir];
            int nx = now.X + dx[dir];

            if (isSafe(ny,nx) && !isVisited[ny][nx] && tmpBoard[ny][nx] == EMPTY)
            {
                isVisited[ny][nx] = true;
                que.push({ny,nx});
            }
        }
    }

    return isFindSwan;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    meltCntBoard.resize(N, vector<int>(M, 0));
    visited.resize(N, vector<bool>(M,false));

    for (int i = 0; i < N; ++i)
    {
        string line;
        cin >> line;
        board.push_back(line);
        for (int j = 0; j < line.size(); ++j)
        {
            if (line[j] == 'L')
            {
                swanPos.push_back({i,j});
            }
        }
    }

    for (int i = 0; i < N; ++i)
    {
        for (int j = 0; j < M; ++j)
        {
            if (board[i][j] != 'X')
            {
                for (int dir = 0; dir < 4; ++dir)
                {
                    int ny = i + dy[dir];
                    int nx = j + dx[dir];

                    if (isSafe(ny,nx) && board[ny][nx] == 'X' && !visited[ny][nx])
                    {
                        visited[ny][nx] = true;
                        q.push({ny,nx});
                        meltCntBoard[ny][nx] = 1;
                    }
                }
            }
        }
    }

    int maxDay = -1;

    while (!q.empty())
    {
        int loop = q.size();

        while (loop--)
        {
            Pair now = q.front(); q.pop();

            for (int dir = 0; dir < 4; ++dir)
            {
                int ny = now.Y + dy[dir];
                int nx = now.X + dx[dir];
                
                if (isSafe(ny,nx) && board[ny][nx] == 'X' && !visited[ny][nx])
                {
                    visited[ny][nx] = true;
                    q.push({ny,nx});
                    meltCntBoard[ny][nx] = meltCntBoard[now.Y][now.X] + 1;
                    maxDay = max(maxDay, meltCntBoard[ny][nx]);
                }
            }
        }
    }

    int answer = 987654321;
    int st,en;
    st = 0;
    en = maxDay + 1;

    while (st <= en)
    {
        int mid = (st + en) / 2;

        if (getMeetingDay(mid))
        {
            //만날 수 있는 경우
            answer = mid;
            en = mid - 1;
        }
        else
        {
            //못 만나는 경우 => 얼음 더 녹아야함
            st = mid + 1;
        }
    }

    cout << answer << "\n";
    

    return 0;
}