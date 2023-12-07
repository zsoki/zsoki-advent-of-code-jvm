package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day7Test {

    @Test
    fun day7_part1() {
        println(day7part1("y2023/day7.txt"))
    }

    @Test
    fun day7_part2() {
        println(day7part2("y2023/day7.txt"))
    }

    @Test
    fun day7_part1_example1() {
        assertEquals(6440, day7part1("y2023/day7_part1_example1.txt"))
    }

    @Test
    fun day7_part1_example2() {
        assertEquals(5905, day7part2("y2023/day7_part1_example1.txt"))
    }

}