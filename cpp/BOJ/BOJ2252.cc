#include <iostream>
#include <vector>
using namespace std;

vector<int> childCnt;
vector<vector<int> > parents;
int N,M;

int findTail() 
{
    for (int idx = 0; idx < childCnt.size(); ++idx)
    {
        if (childCnt[idx] == 0)
        {
            return idx;
        }
    }

    return -1;
}
int main(void) {
    
    ios::sync_with_stdio(0);
    
    cin >> N >> M;
    childCnt.resize(N,0);
    parents.resize(N);

    for (int a,b,i = 0; i < M; ++i)
    {
        cin >> a >> b;
        a--; b--;
        childCnt[b]++;
        parents[a].push_back(b);
    }

    int nxt = 0;
    vector<int> result;

    while ((nxt = findTail()) != -1)
    {
        result.push_back(nxt);

        for (int idx = 0; idx < parents[nxt].size(); ++idx)
        {
            int tallerStudent = parents[nxt][idx];
            childCnt[tallerStudent]--;
        }

        childCnt[nxt] = -1;
    }

    for (int idx = 0; idx < result.size(); ++idx)
    {
        cout << result[idx] + 1 << " ";
    }

    cout << "\n";
    
    return 0;
}