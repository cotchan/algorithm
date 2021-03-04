#include <iostream>
#include <vector>
using namespace std;

int n, carrySize;
vector<int> prefSum;
vector<vector<int> > dp;

int solve(int trainIdx, int passengerIdx) {

    if (trainIdx == 3 || passengerIdx > n)
        return 0;

    if (dp[trainIdx][passengerIdx] != -1)
    {
        return dp[trainIdx][passengerIdx];
    }

    int result = 0;
    if (passengerIdx + carrySize-1 <= n)
        result = max(solve(trainIdx, passengerIdx + 1), (prefSum[passengerIdx + carrySize-1] - prefSum[passengerIdx-1]) + solve(trainIdx+1,passengerIdx+carrySize));
    
    return dp[trainIdx][passengerIdx] = result;
}

int main(void) {

    ios::sync_with_stdio(0);
    cin >> n;
    prefSum.resize(n+1,0);
    dp.resize(3, vector<int>(n+1,-1));    

    for (int x,i = 1; i <= n; ++i)
    {
        cin >> x;
        prefSum[i] = prefSum[i-1] + x;
    }

    cin >> carrySize;

    cout << solve(0,1) << "\n";
}