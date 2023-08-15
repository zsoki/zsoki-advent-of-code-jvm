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

    public static int calculateBadgePrioritySum(List<String> lines) {
        int prioritiesSum = 0;

        for (int lineIndex = 0; lineIndex <= lines.size() - 3; lineIndex += 3) {
            int[] priorityFlags = new int[53];
            var rucksackIndex = 0;

            while (true) {
                final String firstLine = lines.get(lineIndex);
                if (rucksackIndex < firstLine.length()) {
                    int firstPriority = convertCharPriority(firstLine.charAt(rucksackIndex));
                    int flag = priorityFlags[firstPriority];
                    if (flag == 6) {
                        prioritiesSum += firstPriority;
                        break;
                    } else if (flag == 0 || flag == 2 || flag == 4) {
                        priorityFlags[firstPriority] += 1;
                    }
                }

                final String secondLine = lines.get(lineIndex + 1);
                if (rucksackIndex < secondLine.length()) {
                    int secondPriority = convertCharPriority(secondLine.charAt(rucksackIndex));
                    int flag = priorityFlags[secondPriority];
                    if (flag == 5) {
                        prioritiesSum += secondPriority;
                        break;
                    } else if (flag == 0 || flag == 1 || flag == 4) {
                        priorityFlags[secondPriority] += 2;
                    }
                }

                final String thirdLine = lines.get(lineIndex + 2);
                if (rucksackIndex < thirdLine.length()) {
                    int thirdPriority = convertCharPriority(thirdLine.charAt(rucksackIndex));
                    int flag = priorityFlags[thirdPriority];
                    if (flag == 3) {
                        prioritiesSum += thirdPriority;
                        break;
                    } else if (flag == 0 || flag == 1 || flag == 2) {
                        priorityFlags[thirdPriority] += 4;
                    }
                }

                rucksackIndex += 1;
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
