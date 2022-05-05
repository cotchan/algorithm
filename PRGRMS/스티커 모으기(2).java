class Solution {

    public int[] usedFirst, unusedFirst;

    public int solution(int sticker[]) {

        if (sticker.length == 1) {
            return sticker[0];
        }

        usedFirst = new int[sticker.length];
        unusedFirst = new int[sticker.length];

        usedFirst[0] = sticker[0];
        usedFirst[1] = usedFirst[0];

        unusedFirst[0] = 0;
        unusedFirst[1] = sticker[1];

        for (int i = 2; i < sticker.length - 1; ++i) {
            usedFirst[i] = Math.max(usedFirst[i - 2] + sticker[i], usedFirst[i - 1]);
        }

        for (int i = 2; i < sticker.length; ++i) {
            unusedFirst[i] = Math.max(unusedFirst[i - 2] + sticker[i], unusedFirst[i - 1]);
        }

        int maxv1 = 0;
        int maxv2 = 0;

        for (int i = 0; i < usedFirst.length; ++i) {
            maxv1 = Math.max(maxv1, usedFirst[i]);
        }

        for (int i = 0; i < unusedFirst.length; ++i) {
            maxv2 = Math.max(maxv2, unusedFirst[i]);
        }

        return Math.max(maxv1, maxv2);
    }
}
