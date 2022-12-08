import java.util.*
import kotlin.math.max

/**
 * https://adventofcode.com/2022/day/1
 * --- Day 1: Calorie Counting ---
 */

fun day1Part1(inputFileName: String): Int {
    var currentElfCalories = 0
    var maxCalories = 0

    loadInput(inputFileName).forEachLine {
        if (it.isBlank()) {
            maxCalories = max(currentElfCalories, maxCalories)
            currentElfCalories = 0
        } else {
            currentElfCalories += Integer.parseInt(it)
        }
    }

    return maxCalories
}

fun day1Part2(inputFileName: String): Int {
    var currentElfCalories = 0
    val top3Calories = LinkedList(listOf(0, 0, 0))

    loadInput(inputFileName).forEachLine { line ->
        if (line.isBlank()) {
            for(idx in 0 until 3) {
                if (currentElfCalories > top3Calories[idx]) {
                    top3Calories.add(idx, currentElfCalories)
                    break
                }
            }
            currentElfCalories = 0
        } else {
            currentElfCalories += Integer.parseInt(line)
        }
    }

    return top3Calories.take(3).toIntArray().sum()
}