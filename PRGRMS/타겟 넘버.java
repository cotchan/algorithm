class Solution {

    public static int NUMBER;
    public static int targetNumber;

    public int solution(int[] numbers, int target) {

        targetNumber = target;
        
        int answer = dfs(numbers, 0, 0);
        return answer;
    }

    public int dfs(int[] numbers, int idx, int sum) {

        if (idx == numbers.length) {
            return sum == targetNumber ? 1 : 0;
        }

        int targetNumberCnt = dfs(numbers, idx + 1, sum + numbers[idx]);
        targetNumberCnt += dfs(numbers, idx + 1, sum - numbers[idx]);

        return targetNumberCnt;
    }
}
