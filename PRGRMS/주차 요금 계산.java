import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

class Solution {

    //CarNumber, 들어온 시간
    Map<String, Integer> carMap = new HashMap<>();

    //CarNumber, 주차정산 대상 시간
    Map<String, Integer> carFeeMap = new HashMap<>();

    //결과 정렬용
    TreeMap<String, Integer> treeMap = new TreeMap<>();

    public int[] solution(int[] fees, String[] records) {

        for (String record : records) {
            String[] recordInfoList = record.split(" ");

            String timeInfo = recordInfoList[0];
            String carNumber = recordInfoList[1];
            String state = recordInfoList[2];

            int time = timeToMinute(timeInfo);

            if (state.equals("IN")) {
                carMap.put(carNumber, time);
            } else {
                int inTime = carMap.get(carNumber);
                carMap.remove(carNumber);

                int outTime = timeToMinute(timeInfo);

                int sum = carFeeMap.get(carNumber) == null ? 0 : carFeeMap.get(carNumber);
                sum += (outTime - inTime);

                carFeeMap.put(carNumber, sum);
            }
        }

        String endTimeInfo = "23:59";
        int endTime = timeToMinute(endTimeInfo);

        for (String carNumber : carMap.keySet()) {
            int inTime = carMap.get(carNumber);

            int sum = carFeeMap.get(carNumber) == null ? 0 : carFeeMap.get(carNumber);
            sum += (endTime - inTime);

            carFeeMap.put(carNumber, sum);
        }

        for (Map.Entry<String, Integer> car : carFeeMap.entrySet()) {
            String carNumber = car.getKey();
            Integer time = car.getValue();

            int fee = getFee(fees, time);

            treeMap.put(carNumber, fee);
        }

        int[] answer = Arrays.stream(treeMap.values()
                                .toArray(new Integer[treeMap.size()]))
                                .mapToInt(Integer::intValue)
                                .toArray();

        return answer;
    }

    public int timeToMinute(String time) {

        String[] timeInfo = time.split(":");

        int hour = Integer.parseInt(timeInfo[0]);
        int min = Integer.parseInt(timeInfo[1]);

        return 60 * hour + min;
    }

    public int getFee(int[] fees, int time) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int perTime = fees[2];
        int perFee = fees[3];

        if (time <= baseTime) {
            return baseFee;
        }

        int overTime = time - baseTime;
        int calculatedTime = overTime % perTime == 0 ? (overTime / perTime) : (overTime / perTime) + 1;

        int overFee = calculatedTime * perFee;
        return baseFee + overFee;
    }
}
