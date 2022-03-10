import java.util.*;

class Solution {

    public static final int OPERATOR_SIZE = 3;
    public static final String[] OPERATORS = {"+","-","*"};
    public static boolean[] selected = new boolean[OPERATOR_SIZE];
    public static List<String[]> candidates = new LinkedList<>();
    public static LinkedList<String> candidate = new LinkedList<>();
    public static Map<String, Integer> operatorCntMap = new HashMap<>();

    public long solution(String expression) {
        long answer = 0;

        String[] numberInput = expression.split("[-*+]");
        String[] operatorInput = expression.split("[0-9]");

        //[100,200,300,500,20]
        String[] originNumbers = Arrays.stream(numberInput).filter(number -> !number.isEmpty()).toArray(String[]::new);
        //[-,*,-,+]
        String[] originOperators = Arrays.stream(operatorInput).filter(operator -> !operator.isEmpty()).toArray(String[]::new);

        // 우선 순위 정하기
        permutation(0);

        int addCnt = 0;
        int minusCnt = 0;
        int multipleCnt = 0;

        for (String operator : originOperators) {
            switch (operator) {
                case "+":
                    addCnt++;
                    break;
                case "-":
                    minusCnt++;
                    break;
                default:
                    multipleCnt++;
            }
        }

        operatorCntMap.put("+", addCnt);
        operatorCntMap.put("-", minusCnt);
        operatorCntMap.put("*", multipleCnt);

        for (String[] priority : candidates) {

            Queue<Long> operandQueue = new LinkedList<>();
            Queue<String> operatorQueue = new LinkedList<>();
            Queue<Long> tempOperandQueue = new LinkedList<>();
            Queue<String> tempOperatorQueue = new LinkedList<>();

            for (String operand : originNumbers) {
                operandQueue.add(Long.parseLong(operand));
            }

            for (String operator : originOperators) {
                operatorQueue.add(operator);
            }

            //해당 우선순위에 있는 계산 전부하기
            for (String operator : priority) {

                int cnt = 0;
                int operatorCnt = operatorCntMap.get(operator);

                while (cnt < operatorCnt) {

                    if (operatorQueue.isEmpty()) {

                        while (!operandQueue.isEmpty()) {
                            tempOperandQueue.add(operandQueue.poll());
                        }

                        operandQueue.addAll(tempOperandQueue);
                        operatorQueue.addAll(tempOperatorQueue);

                        tempOperandQueue.clear();
                        tempOperatorQueue.clear();
                    }

                    long n1 = operandQueue.poll();
                    String nowOperator = operatorQueue.poll();

                    if (nowOperator.equals(operator)){

                        cnt++;
                        long n2 = operandQueue.poll();
                        long calculateNumber = getCalculateNumber(n1, nowOperator, n2);

                        tempOperandQueue.add(calculateNumber);

                        while (!operandQueue.isEmpty()) {
                            tempOperandQueue.add(operandQueue.poll());
                        }

                        while (!operatorQueue.isEmpty()) {
                            tempOperatorQueue.add(operatorQueue.poll());
                        }

                    } else {
                        tempOperandQueue.add(n1);
                        tempOperatorQueue.add(nowOperator);
                    }
                }
            }

            long sum = tempOperandQueue.poll();
            sum = sum < 0 ? Math.abs(sum) : sum;
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    public void permutation(int pickCnt) {

        if (pickCnt == OPERATOR_SIZE) {
            String[] candidateArr = candidate.toArray(new String[candidate.size()]);
            candidates.add(candidateArr);
        }

        for (int i = 0; i < OPERATOR_SIZE; ++i) {
            if (!selected[i]) {
                selected[i] = true;
                candidate.addLast(OPERATORS[i]);
                permutation(pickCnt + 1);
                candidate.removeLast();
                selected[i] = false;
            }
        }
    }

    public long getCalculateNumber(long number1, String operator, long number2) {
        switch (operator) {
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            default:
                return number1 * number2;
        }
    }
}
