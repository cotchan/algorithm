import java.util.Stack;

class Solution {
    public String solution(String p) {
        String answer = toRightString(p);
        return answer;
    }

    boolean isEqualString(String s) {
        int leftCount = 0;
        int rightCount = 0;

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (c == '(') leftCount++;
            else rightCount++;
        }

        return leftCount == rightCount;
    }

    boolean isRightString(String target) {
        if (!isEqualString(target)) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < target.length(); ++i) {
            char c = target.charAt(i);

            if (c == '(') {
                stack.add('(');
            } else {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    String toRightString(String s) {

        //입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (s.equals("")) return "";

        //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        String[] strings = splitToUV(s);
        if (strings == null) return "";

        String u = strings[0];
        String v = strings[1];

        if (isRightString(u)) {
            return u + toRightString(v);
        } else {
            StringBuilder sb = new StringBuilder();

            sb.append('(');
            sb.append(toRightString(v));
            sb.append(')');
            String subU = u.substring(1, u.length() - 1);
            String reverseSubU = getReverseString(subU);
            sb.append(reverseSubU);
            return sb.toString();
        }
    }

    String[] splitToUV(String target) {
        //입력이 빈 문자열인 경우, 빈 문자열을 반환합니다.
        if (target.equals("")) return null;

        //2. 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리합니다. 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 될 수 있습니다.
        for (int i = 2; i <= target.length(); i += 2) {
            String u = target.substring(0, i);
            String v = "";

            if (i != target.length()) {
                v = target.substring(i);
            }

            if (isEqualString(u) && isEqualString(v)) {
                return new String[]{u, v};
            }
        }

        return new String[]{target, ""};
    }

    String getReverseString(String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); ++i) {
            char c = target.charAt(i);
            if (c == '(') sb.append(')');
            else sb.append('(');
        }
        return sb.toString();
    }


}
