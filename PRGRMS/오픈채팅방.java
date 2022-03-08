import java.util.*;

class Solution {

    static class Pair {
        String first, second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    public static final String ENTER = "Enter";
    public static final String LEAVE = "Leave";
    public static final String CHANGE = "Change";
    public static Map<String, String> userMap = new HashMap<>();
    public static Map<String, String> actionMap = new HashMap<>();
    public static List<Pair> actions = new ArrayList<>();

    public String[] solution(String[] record) {

        actionMap.put(ENTER, "들어왔습니다.");
        actionMap.put(LEAVE, "나갔습니다.");

        for (String actionString : record) {
            String[] actionInfo = actionString.split(" ");
            String action = actionInfo[0];
            String uuid = actionInfo[1];

            if (!action.equals(LEAVE)) {
                String nickname = actionInfo[2];
                userMap.put(uuid, nickname);
            }

            actions.add(new Pair(uuid, getActionState(action)));
        }

        List<String> answer = new ArrayList<>();

        for (Pair actionInfo : actions) {
            if (!actionInfo.second.equals(CHANGE)) {
                String message = getMessage(actionInfo.first, actionInfo.second);
                answer.add(message);
            }
        }

        return answer.toArray(new String[answer.size()]);
    }

    public String getMessage(String uuid, String action) {
        String nickname = userMap.get(uuid);
        String actionMessage = actionMap.get(action);

        return String.format("%s님이 %s", nickname, actionMessage);
    }

    public String getActionState(String action) {
        switch (action) {
            case ENTER:
                return ENTER;
            case CHANGE:
                return CHANGE;
            default:
                return LEAVE;
        }
    }
}
