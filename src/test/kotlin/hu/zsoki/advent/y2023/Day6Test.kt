package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun day6_part1() {
        println(day6part1("y2023/day6.txt"))
    }

    @Test
    fun day6_part2() {
        println(day6part2("y2023/day6.txt"))
    }

    @Test
    fun day6_part1_example1() {
        assertEquals(288, day6part1("y2023/day6_part1_example1.txt"))
    }

    @Test
    fun day6_part1_example2() {
        assertEquals(71503, day6part2("y2023/day6_part1_example1.txt"))
    }

}