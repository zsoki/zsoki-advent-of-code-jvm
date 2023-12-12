package hu.zsoki.advent.y2023

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun day11_part1() {
        println(day11part1("y2023/day11.txt"))
    }

    @Test
    fun day11_part1_example1() {
        assertEquals(374, day11part1("y2023/day11_part1_example1.txt"))
    }

    @Test
    fun day11_part1_example2() {
        assertEquals(24, day11part1("y2023/day11_part1_example2.txt"))
    }

    @Test
    fun day11_part2() {
        println(day11part2("y2023/day11.txt", 1_000_000L))
    }

    @Test
    fun day11_part2_example1() {
        assertEquals(1030, day11part2("y2023/day11_part1_example1.txt", 10))
        assertEquals(8410, day11part2("y2023/day11_part1_example1.txt", 100))
    }
}