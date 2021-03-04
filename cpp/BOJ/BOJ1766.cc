#define Pair pair<int,int>
#include <iostream>
#include <vector>
#include <queue>
using namespace std;

int N,M;
priority_queue<Pair> pq;
vector<bool> isSolved;
vector<int> precedeCnt;
vector<vector<int> > graph;
vector<int> answer;
int main(void) {

    ios::sync_with_stdio(0);
    
    cin >> N >> M;
    isSolved.resize(N,false);
    graph.resize(N);
    precedeCnt.resize(N,0);

    for (int loop = 0; loop < M; ++loop)
    {
        int A, B;
        cin >> A >> B;
        A--; B--;
        precedeCnt[B]++;
        graph[A].push_back(B);
    }

    for (int i = 0; i < N; ++i)
    {
        pq.push(make_pair(-precedeCnt[i], -i));
    }

    while (!pq.empty())
    {
        Pair now = pq.top(); pq.pop();
        int nowProblem = -now.second;

        if (isSolved[nowProblem])
        {
            continue;
        }

        isSolved[nowProblem] = true;
        answer.push_back(nowProblem);
        
        for (int i = 0; i < graph[nowProblem].size(); ++i)
        {
            int nxtProblem = graph[nowProblem][i];
            precedeCnt[nxtProblem]--;
            if (!isSolved[nxtProblem])
            {
                pq.push(make_pair(-precedeCnt[nxtProblem], -nxtProblem));
            }
        }
    }
    
    for (int i = 0; i < answer.size(); ++i)
    {
        cout << answer[i] + 1 << " ";
    }
    
    cout << "\n";

    return 0;
}