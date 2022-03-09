import java.util.HashSet;
import java.util.Set;

class Solution {

    public static Set<Integer> candidateKeys = new HashSet<>();

    public int solution(String[][] relation) {
        int columnSize = relation[0].length;

        for (int state = 0; state < (1 << columnSize); ++state) {

            boolean superKey = false;
            Set<String> rows = new HashSet<>();

            for (String[] row : relation) {

                int nowState = state;
                StringBuilder nowRow = new StringBuilder();

                for (int i = 0; i < columnSize; ++i) {
                    int used = nowState % 2;

                    if (used == 1) {
                        nowRow.append(row[i]);
                    }

                    nowState /= 2;
                }

                String target = nowRow.toString();

                if (target.isEmpty() || rows.contains(target)) {
                    break;
                }

                rows.add(target);
            }

            if (rows.size() == relation.length) {
                for (Integer candidateKey : candidateKeys) {
                    if (isSuperKey(state,candidateKey)) {
                        superKey = true;
                        break;
                    }
                }

                if (!superKey) {
                    candidateKeys.add(state);
                }
            }
        }

        return candidateKeys.size();
    }

    public boolean isSuperKey(Integer key, Integer candidateKey) {
        int commonValue = key & candidateKey;
        return commonValue > 0 && (commonValue == candidateKey);
    }
}
