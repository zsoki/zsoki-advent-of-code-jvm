package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    @Test
    fun part1() {
        println(day1Part1("y2023/day1.txt"))
    }

    @Test
    fun part2() {
        println(day1Part2("y2023/day1.txt"))
    }

    @Test
    fun part1_examples() {
        assertEquals(46, day1Part1("y2023/day1_part1_example1.txt"))
        assertEquals(77, day1Part1("y2023/day1_part1_example2.txt"))
    }

    @Test
    fun part2_examples() {
        assertEquals(13, day1Part2("y2023/day1_part2_example1.txt"))
        assertEquals(13, day1Part2("y2023/day1_part2_example2.txt"))
        assertEquals(281, day1Part2("y2023/day1_part2_example3.txt"))
    }

}