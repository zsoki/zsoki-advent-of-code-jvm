import java.util.List;

public class Day4 {

    public static int countFullyContainedAssignments(List<String> lines) {
        int fullyContainedAssignments = 0;

        for (var line : lines) {
            final String[] groups = line.replace(',', '-').split("-");
            final int g1Start = Integer.parseInt(groups[0]);
            final int g1End = Integer.parseInt(groups[1]);
            final int g2Start = Integer.parseInt(groups[2]);
            final int g2End = Integer.parseInt(groups[3]);

            // Group 1 contains Group 2 || Group 2 contains Group 1
            if (g2Start >= g1Start && g2End <= g1End || g1Start >= g2Start && g1End <= g2End) {
                fullyContainedAssignments += 1;
            }
        }

        return fullyContainedAssignments;
    }

    public static int countOverlappingAssignments(List<String> lines) {
        int overlappingAssignments = 0;

        for (var line : lines) {
            final String[] groups = line.replace(',', '-').split("-");
            final int g1Start = Integer.parseInt(groups[0]);
            final int g1End = Integer.parseInt(groups[1]);
            final int g2Start = Integer.parseInt(groups[2]);
            final int g2End = Integer.parseInt(groups[3]);

            if (g1Start >= g2Start && g1Start <= g2End      // Overlap
                    || g1End >= g2Start && g1End <= g2End   // Overlap
                    || g2Start >= g1Start && g2End <= g1End) {  // G1 Contains G2
                overlappingAssignments += 1;
            }
        }

        return overlappingAssignments;
    }
}
