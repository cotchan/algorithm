class Solution {

    static int ans;

    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return ans;
    }

    public void dfs(int[] numbers, int target, int idx, int sum) {
        if (idx == numbers.length) {
            if (sum == target) ans++;
            return;
        }

        int nowNumber = numbers[idx];
        dfs(numbers, target, idx+1, sum + nowNumber);
        dfs(numbers, target, idx+1, sum - nowNumber);
    }
}
