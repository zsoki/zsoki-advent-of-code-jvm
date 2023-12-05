package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day5Test {

    @Test
    fun day5_part1() {
        println(day5part1("y2023/day5.txt"))
    }

    @Test
    fun day5_part2() {
        println(day5part2("y2023/day5.txt"))
    }

    @Test
    fun day5_part1_example1() {
        assertEquals(35, day5part1("y2023/day5_part1_example1.txt"))
    }

    @Test
    fun day5_part2_example1() {
        assertEquals(46, day5part2("y2023/day5_part1_example1.txt"))
    }

}