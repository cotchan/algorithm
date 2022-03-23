class Solution {
    public String solution(int n) {

        StringBuilder sb = new StringBuilder();

        while (n > 0) {
            int r = (n - 1) % 3;

            if (r == 0) sb.append(1);
            if (r == 1) sb.append(2);
            if (r == 2) sb.append(4);

            n = (n - 1) / 3;
        }

        return sb.reverse().toString();
    }
}
