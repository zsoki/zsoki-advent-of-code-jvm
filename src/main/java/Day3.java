import java.util.List;

public class Day3 {

    public static int calculatePrioritiesSum(List<String> lines) {
        int prioritiesSum = 0;

        for (String line : lines) {
            int[] priorityFlags = new int[53];

            for (int i = 0; i < line.length() / 2; i++) {
                int leftPriority = convertCharPriority(line.charAt(i));
                int flag = priorityFlags[leftPriority];

                if (flag == 0) {
                    priorityFlags[leftPriority] += 1;
                } else if (flag == 2) {
                    prioritiesSum += leftPriority;
                    break;
                }

                int rightPriority = convertCharPriority(line.charAt(line.length() - 1 - i));
                flag = priorityFlags[rightPriority];

                if (flag == 0) {
                    priorityFlags[rightPriority] += 2;
                } else if (flag == 1) {
                    prioritiesSum += rightPriority;
                    break;
                }
            }

        }

        return prioritiesSum;
    }

    public static int convertCharPriority(char character) {
        if (character >= 97 && character <= 122) {
            return character - 96;
        } else if (character >= 65 && character <= 90) {
            return character - 38;
        } else {
            throw new RuntimeException("Unexpected character in input.");
        }
    }

}
