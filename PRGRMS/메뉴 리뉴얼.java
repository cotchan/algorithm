import java.util.*;

class Solution {

    public static Map<String, Integer> menuMap = new HashMap<>();
    public static boolean[] isValidSize = new boolean[11];

    public String[] solution(String[] orders, int[] course) {

        for (int courseSize : course) {
            isValidSize[courseSize] = true;
        }

        List<String> sortedOrders = new ArrayList<>();

        for (String order : orders) {
            char[] chars = order.toCharArray();
            Arrays.sort(chars);
            String str = new String(chars);
            sortedOrders.add(str);
        }

        Collections.sort(sortedOrders);

        for (int i = 0; i < sortedOrders.size() - 1; ++i) {
            for (int j = i + 1; j < sortedOrders.size(); ++j) {

                String shortOrder = sortedOrders.get(i);
                int shortOrderSize = sortedOrders.get(i).length();

                for (int state = 1; state < (1 << shortOrderSize); ++state) {

                    int nowState = state;
                    StringBuilder sb = new StringBuilder();

                    for (int k = 0; k < shortOrderSize; ++k) {

                        int isUsed = nowState % 2;

                        if (isUsed == 1) {
                            sb.append(shortOrder.charAt(k));
                        }

                        nowState /= 2;
                    }

                    String candidate = sb.toString();
                    String longOrder = sortedOrders.get(j);

                    if (isValidSize[candidate.length()] && isContain(candidate, longOrder)) {
                        if (!menuMap.containsKey(candidate)) {
                            menuMap.put(candidate, 1);
                        } else {
                            int before = menuMap.get(candidate);
                            menuMap.put(candidate, before + 1);
                        }
                    }
                }
            }
        }

        List<String>[] maxOrders = new List[11];

        for (int i = 0; i < 11; ++i) {
            maxOrders[i] = new ArrayList<>();
        }

        int[] maxOrderCnt = new int[11];

        for (String key : menuMap.keySet()) {
            int menuSize = key.length();
            int menuOrderCnt = menuMap.get(key);

            if (menuOrderCnt > maxOrderCnt[menuSize]) {
                maxOrders[menuSize].clear();
                maxOrders[menuSize].add(key);
                maxOrderCnt[menuSize] = menuOrderCnt;
            } else if (menuOrderCnt == maxOrderCnt[menuSize]) {
                maxOrders[menuSize].add(key);
            }
        }

        List<String> answerList = new ArrayList<>();

        for (int i = 0; i < 11; ++i) {
            for (String menu : maxOrders[i]) {
                answerList.add(menu);
            }
        }

        Collections.sort(answerList);

        String[] answer = answerList.toArray(new String[answerList.size()]);
        return answer;
    }

    public boolean isContain(String shortStr, String longStr) {

        List<Character> shortStrArr = new ArrayList<>();
        List<Character> longStrArr = new ArrayList<>();

        for (int i = 0; i < shortStr.length(); ++i) {
            char c = shortStr.charAt(i);
            shortStrArr.add(c);
        }

        for (int i = 0; i < longStr.length(); ++i) {
            char c = longStr.charAt(i);
            longStrArr.add(c);
        }

        Collections.sort(shortStrArr);
        Collections.sort(longStrArr);

        return longStrArr.containsAll(shortStrArr);
    }
}
