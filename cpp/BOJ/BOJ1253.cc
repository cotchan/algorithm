// https://www.acmicpc.net/problem/1253
// ¡¡¥Ÿ

#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int N, ans;
vector<int> numbers;
int main(void) {
    
    cin >> N;
    for (int x,i = 0; i < N; ++i)
    {
        cin >> x;
        numbers.push_back(x);
    }
    
    sort(numbers.begin(), numbers.end());

    for (int idx = 0; idx < N; ++idx)
    {
        vector<int> tmp;
        
        //makes tmp_vector
        for (int i = 0; i < N; ++i)
        {
            if (i == idx) 
                continue;

            tmp.push_back(numbers[i]);
        }

        int targetNumber = numbers[idx];
        bool isFined = false;

        //first number
        for (int i = 0; i < (int)tmp.size(); ++i)
        {
            if (isFined)
            {
                ans += 1;
                break;
            }

            //second number
            int l = i+1;
            int r = tmp.size();

            while (l < r)
            {
                int mid = (l + r) / 2;
                
                int value = tmp[i] + tmp[mid];
                
                if (targetNumber < value)
                {
                    r = mid;
                }
                else if (targetNumber > value)
                {
                    l = mid + 1;
                }
                else
                {
                    isFined = true;
                    break;
                }
            }
        }
    }

    cout << ans;    
    return 0;
}