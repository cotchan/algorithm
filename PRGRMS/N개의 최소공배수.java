import java.util.*;

class Solution {
    public int solution(int[] arr) {
        int lcm = arr[0];

        for (int i = 1; i < arr.length; ++i) {
            lcm = lcm(lcm, arr[i]);
        }

        return lcm;
    }

    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
