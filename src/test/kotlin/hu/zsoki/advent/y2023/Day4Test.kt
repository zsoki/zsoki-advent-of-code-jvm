package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun day4_part1() {
        println(day4part1("y2023/day4.txt"))
    }

    @Test
    fun day4_part2() {
        println(day4part2("y2023/day4.txt"))
    }

    @Test
    fun day4_part1_example1() {
        assertEquals(13, day4part1("y2023/day4_part1_example1.txt"))
    }

    @Test
    fun day4_part2_example1() {
        assertEquals(30, day4part2("y2023/day4_part1_example1.txt"))
    }

}