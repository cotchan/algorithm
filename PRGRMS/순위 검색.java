import java.util.*;

class Solution {

    public static final int TOTAL_STATE_SIZE = 5000;
    public static ArrayList<Integer>[] userStates = new ArrayList[TOTAL_STATE_SIZE];

    public int[] solution(String[] info, String[] query) {

        for (int i = 0; i < TOTAL_STATE_SIZE; ++i) {
            userStates[i] = new ArrayList<>();
        }

        for (String infoString : info) {
            String[] userInfoString = infoString.split(" ");

            String lang = userInfoString[0];
            String pos = userInfoString[1];
            String career = userInfoString[2];
            String food = userInfoString[3];
            int score = Integer.parseInt(userInfoString[4]);

            //0
            int userState = getUserState(lang, pos, career, food);
            userStates[userState].add(score);

            //1
            userState = getUserState("-", pos, career, food);
            userStates[userState].add(score);
            userState = getUserState(lang, "-", career, food);
            userStates[userState].add(score);
            userState = getUserState(lang, pos, "-", food);
            userStates[userState].add(score);
            userState = getUserState(lang, pos, career, "-");
            userStates[userState].add(score);

            //2
            userState = getUserState("-", "-", career, food);
            userStates[userState].add(score);
            userState = getUserState("-", pos, "-", food);
            userStates[userState].add(score);
            userState = getUserState("-", pos, career, "-");
            userStates[userState].add(score);
            userState = getUserState(lang, "-", "-", food);
            userStates[userState].add(score);
            userState = getUserState(lang, "-", career, "-");
            userStates[userState].add(score);
            userState = getUserState(lang, pos, "-", "-");
            userStates[userState].add(score);

            //3
            userState = getUserState("-", "-", "-", food);
            userStates[userState].add(score);
            userState = getUserState("-", "-", career, "-");
            userStates[userState].add(score);
            userState = getUserState("-", pos, "-", "-");
            userStates[userState].add(score);
            userState = getUserState(lang, "-", "-", "-");
            userStates[userState].add(score);

            //4
            userState = getUserState("-", "-", "-", "-");
            userStates[userState].add(score);
        }

        for (int i = 0; i < TOTAL_STATE_SIZE; ++i) {
            Collections.sort(userStates[i]);
        }

        List<Integer> ans = new ArrayList<>();
        //query
        for (String queryInfoString : query) {
            String[] tempQueryInfo = queryInfoString.split(" ");
            String[] queryInfo = Arrays.stream(tempQueryInfo).filter(value -> !value.equals("and")).toArray(String[]::new);

            String lang = queryInfo[0];
            String pos = queryInfo[1];
            String career = queryInfo[2];
            String food = queryInfo[3];
            int score = Integer.parseInt(queryInfo[4]);

            int userState = getUserState(lang, pos, career, food);

            ans.add(getUserCount(userState, score));
        }

        int[] answer = Arrays.stream(ans.toArray(new Integer[ans.size()])).mapToInt(Integer::intValue).toArray();
        return answer;
    }

    public int getUserCount(int userState, int score) {
        ArrayList<Integer> users = userStates[userState];

        int st = 0;
        int en = users.size() - 1;
        int answer = 0;

        while (st <= en) {
            int mid = (st + en) / 2;
            int midScore = users.get(mid);

            if (midScore >= score) {
                en = mid - 1;
                int result = users.size() - mid;
                answer = Math.max(answer, result);
            } else {
                st = mid + 1;
            }
        }

        return answer;
    }

    public int getUserState(String lang, String pos, String career, String food) {
        return getLangState(lang) + getPositionState(pos) + getCareerState(career) + getFoodState(food);
    }

    public int getLangState(String lang) {
        switch (lang) {
            case "cpp":
                return 0;
            case "java":
                return 1000;
            case "python":
                return 2000;
            default:
                return 3000;
        }
    }

    public int getPositionState(String pos) {
        switch (pos) {
            case "backend":
                return 0;
            case "frontend":
                return 100;
            default:
                return 200;
        }
    }

    public int getCareerState(String career) {
        switch (career) {
            case "junior":
                return 0;
            case "senior":
                return 10;
            default:
                return 20;
        }
    }

    public int getFoodState(String food) {
        switch (food) {
            case "chicken":
                return 0;
            case "pizza":
                return 1;
            default:
                return 2;
        }
    }
}
