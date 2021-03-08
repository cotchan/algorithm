#include <iostream>
#include <queue>
#include <vector>
#include <stack>
using namespace std;

int N, answer;
int target1, target2;
vector<int> parent;
stack<int> target1_parentList, target2_parentList;
int main(void) {

    ios::sync_with_stdio(0);
    cin.tie(nullptr);

    int t;
    cin >> t;

    for (int test_case = 0; test_case < t; ++test_case)
    {
        parent.clear();

        while (!target1_parentList.empty())
            target1_parentList.pop();

        while (!target2_parentList.empty())
            target2_parentList.pop();       

        cin >> N;   //³ëµåÀÇ °¹¼ö

        parent.resize(N);
        for (int i = 0; i < parent.size(); ++i)
            parent[i] = i;
        
        for (int loop = 0; loop < N-1; ++loop)
        {
            int a,b;
            cin >> a >> b;
            a--; b--;
            parent[b] = a;
        }

        cin >> target1 >> target2;
        target1--; target2--;

        int target = target1;

        while (parent[target] != target)
        {
            target1_parentList.push(target);
            target = parent[target];
        }

        target1_parentList.push(target);

        target = target2;

        while (parent[target] != target)
        {
            target2_parentList.push(target);
            target = parent[target];
        }

        target2_parentList.push(target);
        
        while (!target1_parentList.empty() && !target2_parentList.empty())
        {
            int t1_top = target1_parentList.top(); target1_parentList.pop();
            int t2_top = target2_parentList.top(); target2_parentList.pop();

            if (t1_top != t2_top)
                break;

            answer = t1_top;
        }

        cout << answer + 1 << "\n";
    }

    return 0;
}