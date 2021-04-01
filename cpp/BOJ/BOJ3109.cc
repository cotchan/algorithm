#include <iostream>
#include <vector>
#include <string>
using namespace std;

int dy[] = {-1,0,1};
int dx[] = {1,1,1};

int R,C;
vector<string> graph;
vector<vector<bool>> isVisited;

bool isSafe(int y, int x) {
    return (0 <= y && y < R && 0 <= x && x < C);
}

int dfs(int y, int x) {

    if (x == C-1)
        return 1;

    for (int dir = 0; dir < 3; ++dir)
    {
        int ny = y + dy[dir];
        int nx = x + dx[dir];

        if (isSafe(ny,nx) && !isVisited[ny][nx] && graph[ny][nx] == '.')
        {
            isVisited[ny][nx] = true;
            int result = dfs(ny,nx);

            //하나라도 길을 찾은 경우 더 이상 탐색하지 않음
            if (result) 
            {
                return result;
            }
        }
    }

    return 0;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> R >> C;
    isVisited.resize(R,vector<bool>(C,false));

    for (int loop = 0; loop < R; ++loop)
    {
        string row;
        cin >> row;
        graph.push_back(row);
    }

    int ans = 0;

    for (int y = 0; y < R; ++y)
    {
        ans += dfs(y,0);
    }

    cout << ans << "\n";
    return 0;
}