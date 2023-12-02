package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class Day2Test {

    @Test
    fun day2_part1() {
        println(day2Part1("y2023/day2.txt"))
    }

    @Test
    fun day2_part2() {
        println(day2Part2("y2023/day2.txt"))
    }

    @Test
    fun day2_part1_examples() {
        assertEquals(8, day2Part1("y2023/day2_part1_example1.txt"))
    }

    @Test
    fun day2_part2_examples() {
        assertEquals(2286, day2Part2("y2023/day2_part2_example1.txt"))
    }

}