import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {

    public static int ansLength, ansStart, ansEnd;
    public static Set<String> gemSet = new HashSet<>();
    public static Map<String, Integer> myGems = new HashMap<>();

    public int[] solution(String[] gems) {

        for (String gem : gems) {
            gemSet.add(gem);
        }

        ansLength = Integer.MAX_VALUE;

        int st = 0;
        int en = 0;
        myGems.put(gems[0], 1);

        while (st <= en && en < gems.length) {

            while (st <= en && isDuplicate(gems[st])) {
                String targetGem = gems[st];
                remove(targetGem);
                st++;
            }

            if (hasAllGems()) {
                saveAnswer(en - st + 1, st, en);
                String targetGem = gems[st];
                remove(targetGem);
                st++;
            } else {
                en++;

                if (en == gems.length) {
                    break;
                }

                String targetGem = gems[en];
                add(targetGem);
            }
        }

        int[] answer = {ansStart + 1, ansEnd + 1};

        return answer;
    }

    public boolean hasAllGems() {
        return gemSet.size() == myGems.keySet().size();
    }

    public void saveAnswer(int length, int st, int en) {
        if (length >= ansLength) {
            return;
        }

        // length < ansLength
        ansLength = length;
        ansStart = st;
        ansEnd = en;
    }

    public void add(String gem) {
        if (myGems.containsKey(gem)) {
            Integer gemCnt = myGems.get(gem);
            myGems.put(gem, gemCnt + 1);
        } else {
            myGems.put(gem, 1);
        }
    }

    public void remove(String gem) {
        if (myGems.containsKey(gem)) {
            Integer gemCnt = myGems.get(gem);

            if (gemCnt == 1) {
                myGems.remove(gem);
            } else {
                myGems.put(gem, gemCnt - 1);
            }
        }
    }

    public boolean isDuplicate(String gem) {
        return myGems.containsKey(gem) && myGems.get(gem) >= 2;
    }
}
