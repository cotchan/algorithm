class Solution {
    public int solution(String s) {
        int answer = Integer.MAX_VALUE;

        if (s.length() == 1) {
            return 1;
        }

        for (int slideSize = 1; slideSize <= s.length() / 2; ++slideSize) {
            StringBuilder sb = new StringBuilder();

            int idx = 0;
            int sameCnt = 0;
            String candidateString = s.substring(0, slideSize);

            while (idx < s.length()) {

                if (idx + slideSize > s.length()) {
                    String restString = s.substring(idx);
                    sb.append(restString);
                    break;
                }

                String nowString = s.substring(idx, idx + slideSize);

                if (nowString.equals(candidateString)) {
                    sameCnt++;
                } else {

                    if (sameCnt > 1) {
                        sb.append(sameCnt + candidateString);
                    } else {
                        sb.append(candidateString);
                    }

                    sameCnt = 1;
                    candidateString = nowString;
                }

                idx += slideSize;
            }

            if (sameCnt > 1) {
                sb.append(sameCnt + candidateString);
            } else {
                sb.append(candidateString);
            }

            answer = Math.min(answer, sb.toString().length());
        }

        return answer;
    }
}
