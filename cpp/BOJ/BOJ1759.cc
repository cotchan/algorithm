#include <iostream>
#include <map>
#include <vector>
#include <string>
#include <algorithm>
#include <set>
using namespace std;

int L,C;
map<char,int> isVowels;
vector<char> alphabets;
vector<char> candidates;
vector<bool> isUsed;
set<string> answer;

void combination(int idx, int pick)
{
    if (pick == L)
    {
        //isAnswer
        int vowelCnt = 0;
        int consonantCnt = 0;
        
        for (char c : candidates)
        {
            if (isVowels.find(c) == isVowels.end())
            {
                //자음
                consonantCnt += 1;
            }
            else
            {
                //모음
                vowelCnt += 1;
            }
        }

        if (vowelCnt >= 1 && consonantCnt >= 2)
        {
            string str = "";
            for (char c : candidates)
            {
                str += c;
            }

            sort(str.begin(), str.end());
            answer.insert(str);
        }

        return;
    }

    if (idx == C)
    {
        return;
    }

    if (!isUsed[idx])
    {
        isUsed[idx] = true;
        candidates.push_back(alphabets[idx]);
        combination(idx+1,pick+1);
        candidates.pop_back();
        isUsed[idx] = false;
    }
    
    combination(idx+1,pick);
    return;
}

int main(void) {

    isVowels.insert({'a',1});
    isVowels.insert({'e',1});
    isVowels.insert({'i',1});
    isVowels.insert({'o',1});
    isVowels.insert({'u',1});

    cin >> L >> C;

    isUsed.resize(C,false);

    for (int loop = 0; loop < C; ++loop)
    {
        char c;
        cin >> c;
        alphabets.push_back(c);
    }    

    combination(0,0);
    
    for (string ans : answer)
    {
        cout << ans << "\n";
    }

    return 0;
}