package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day6part1(inputFilename: String): Int {
    var resultProduct = 1

    val lines = loadInput(inputFilename).readLines()
    val records = lines[0].dropWhile { it !in '0'..'9' }.split(' ').filter { it.isNotBlank() }.map{ it.toInt() }
    val distances = lines[1].dropWhile { it !in '0'..'9' }.split(' ').filter { it.isNotBlank() }.map{ it.toInt() }

    var index = 0
    while (index < records.size) {
        val timeLimit = records[index]
        val distanceRecord = distances[index]
        var numWaysToWin = 0

        var pressTime = 1 // Also the speed, also maybe binary search to determine bounds
        var overshoot = false
        var foundLowerBound = false
        while (!overshoot) {
            val remainingTime = timeLimit - pressTime
            val chargedDistance = remainingTime * pressTime
            if (chargedDistance > distanceRecord) {
                numWaysToWin++
                if (!foundLowerBound) foundLowerBound = true
            } else {
                if (foundLowerBound) overshoot = true
            }
            pressTime++
        }

        resultProduct *= numWaysToWin
        index++
    }

    return resultProduct
}

fun day6part2(inputFilename: String): Long {
    val lines = loadInput(inputFilename).readLines()

    val timeLimit = lines[0].replace(Regex("[^0-9]"), "").toLong()
    val distanceRecord = lines[1].replace(Regex("[^0-9]"), "").toLong()

    // Find lower bound.
    var pressTime = 1L
    var lowerBound = -1L
    while (lowerBound < 0) {
        val remainingTime = timeLimit - pressTime
        val chargedDistance = remainingTime * pressTime
        if (chargedDistance > distanceRecord) {
            lowerBound = pressTime
        }
        pressTime++
    }

    pressTime = timeLimit
    var upperBound = -1L
    while (upperBound < 0) {
        val remainingTime = timeLimit - pressTime
        val chargedDistance = remainingTime * pressTime
        if (chargedDistance > distanceRecord) {
            upperBound = pressTime
        }
        pressTime--
    }

    return upperBound - lowerBound + 1
}