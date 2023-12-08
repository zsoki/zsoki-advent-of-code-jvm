package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day8Test {

    @Test
    fun day8_part1() {
        println(day8part1("y2023/day8.txt"))
    }

    @Test
    fun day8_part1_example1() {
        assertEquals(2, day8part1("y2023/day8_part1_example1.txt"))
    }

    @Test
    fun day8_part1_example2() {
        assertEquals(6, day8part1("y2023/day8_part1_example2.txt"))
    }

    @Test
    fun day8_part2() {
        println(day8part2("y2023/day8.txt"))
    }

    @Test
    fun day8_part2_example1() {
        assertEquals(6, day8part2("y2023/day8_part2_example1.txt"))
    }
}