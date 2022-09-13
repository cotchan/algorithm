class Solution {
    public int solution(int num) {
        return doCollatzCalculate(num, 0);
    }

    int doCollatzCalculate(long num, int cnt) {
        if (num == 1) return cnt;
        if (cnt == 500) return -1;
        if (num % 2 == 0) return doCollatzCalculate(num / 2, cnt + 1);
        else return doCollatzCalculate(num * 3 + 1, cnt + 1);
    }
}
