class Solution {
    public int[] solution(int n, int s) {
        int minValue = s / n;
        int rest = s % n;
        boolean impossible = minValue == 0 ? true : false;

        if (impossible) {
            return new int[]{-1};
        }

        int[] answer = new int[n];

        for (int loop = 0; loop < n - rest; ++loop) {
            answer[loop] = minValue;
        }

        for (int loop = n - rest; loop < n; ++loop) {
            answer[loop] = minValue + 1;
        }

        return answer;
    }
}
