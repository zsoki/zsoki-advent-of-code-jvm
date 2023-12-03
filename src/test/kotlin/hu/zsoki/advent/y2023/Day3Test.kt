package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day3Test {

    @Test
    fun day3_part1() {
        println(day3part1("y2023/day3.txt"))
    }

    @Test
    fun day3_part2() {
        println(day3part2("y2023/day3.txt"))
    }

    @Test
    fun day3_part1_example1() {
        assertEquals(4361, day3part1("y2023/day3_part1_example1.txt"))
    }

    @Test
    fun day3_part2_example1() {
        assertEquals(467835, day3part2("y2023/day3_part1_example1.txt"))
    }

}