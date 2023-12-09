package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day9Test {

    @Test
    fun day9_part1() {
        println(day9part1("y2023/day9.txt"))
    }

    @Test
    fun day9_part1_example1() {
        assertEquals(114, day9part1("y2023/day9_part1_example1.txt"))
    }

    @Test
    fun day9_part2() {
        println(day9part2("y2023/day9.txt"))
    }

    @Test
    fun day9_part2_example1() {
        assertEquals(2, day9part2("y2023/day9_part1_example1.txt"))
    }
}