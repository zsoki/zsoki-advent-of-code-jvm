import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Day3Test {

    @Test
    public void testExample() {
        final String path = ClassLoader.getSystemResource("day3_example.txt").getPath();

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            Assertions.assertEquals(157, Day3.calculatePrioritiesSum(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void solveDay3() {
        final String path = ClassLoader.getSystemResource("day3.txt").getPath();

        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            System.out.println(Day3.calculatePrioritiesSum(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void convertCharPriority() {
        Assertions.assertEquals(1, Day3.convertCharPriority('a'));
        Assertions.assertEquals(26, Day3.convertCharPriority('z'));
        Assertions.assertEquals(27, Day3.convertCharPriority('A'));
        Assertions.assertEquals(52, Day3.convertCharPriority('Z'));

        Assertions.assertThrows(RuntimeException.class, () -> Day3.convertCharPriority('&'));
    }
}