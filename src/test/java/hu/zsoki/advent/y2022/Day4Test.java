package hu.zsoki.advent.y2022;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class Day4Test {

    @Test
    public void day4Part1TestExample() {
        final String path = ClassLoader.getSystemResource("y2022/day4_example.txt").getPath();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            Assertions.assertEquals(2, Day4.countFullyContainedAssignments(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void solveDay4Part1() {
        final String path = ClassLoader.getSystemResource("y2022/day4.txt").getPath();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            System.out.println(Day4.countFullyContainedAssignments(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void day4Part2TestExample() {
        final String path = ClassLoader.getSystemResource("y2022/day4_example.txt").getPath();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            Assertions.assertEquals(4, Day4.countOverlappingAssignments(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void solveDay4Part2() {
        final String path = ClassLoader.getSystemResource("y2022/day4.txt").getPath();
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            System.out.println(Day4.countOverlappingAssignments(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}