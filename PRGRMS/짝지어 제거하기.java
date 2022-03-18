import java.util.Stack;

class Solution {

    public int solution(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); ++i) {
            char alphabet = s.charAt(i);

            if (stack.isEmpty() || (stack.peek() != alphabet)) {
                stack.add(alphabet);
            } else if (stack.peek() == alphabet) {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }
}
