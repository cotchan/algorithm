class Solution {
    public int[] solution(int brown, int yellow) {
        for (int x = 1; x <= Math.sqrt(yellow); ++x) {
            if (yellow % x != 0) continue;
            int y = yellow / x;

            int candidate = 2*x + 2*y + 4;
            if (candidate == brown) {
                return new int[]{y+2, x+2};
            }
        }

        return null;
    }
}
