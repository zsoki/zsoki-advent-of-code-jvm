package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput
import java.util.regex.Pattern

val cardPattern: Pattern = Pattern.compile("Card *(\\d+): ")

fun day4part1(inputFilename: String): Int {
    var sum = 0

    loadInput(inputFilename).forEachLine { line ->
        val winnerFlags = BooleanArray(100) { false }
        var score = 0

        val prefixRemoved = line.removePrefix(cardPattern.matcher(line).apply { find() }.group(0))
        val (winnerLine, tipsLine) = prefixRemoved.split("|")
        val winners = winnerLine.splitToSequence(' ').filter { it.isNotEmpty() }.map{ it.toInt() } .forEach { winnerFlags[it] = true }
        val tips = tipsLine.splitToSequence(' ').filter { it.isNotEmpty() }.map{ it.toInt() }.forEach { tip ->
            if (winnerFlags[tip]) {
                if (score == 0) score = 1 else score *= 2
            }
        }

        sum += score
    }

    return sum
}

fun day4part2(inputFilename: String): Int {
    var winningsBuffer = ArrayDeque<Int>()
    var scracthCardCopies = 0

    loadInput(inputFilename).forEachLine { line ->
        val winnerFlags = BooleanArray(100) { false }

        scracthCardCopies++
        var score = 0

        val prefixRemoved = line.removePrefix(cardPattern.matcher(line).apply { find() }.group(0))
        val (winnerLine, tipsLine) = prefixRemoved.split("|")
        val winners = winnerLine.splitToSequence(' ').filter { it.isNotEmpty() }.map{ it.toInt() } .forEach { winnerFlags[it] = true }
        val tips = tipsLine.splitToSequence(' ').filter { it.isNotEmpty() }.map{ it.toInt() }.forEach { tip ->
            if (winnerFlags[tip]) score++
        }

        // Process prevously won copies as well
        if (winningsBuffer.isNotEmpty()) {
            val prevouslyWon = winningsBuffer.first()
            winningsBuffer.removeFirst()

            for (index in 0..<score) {
                while (winningsBuffer.size < score) {
                    winningsBuffer.addLast(0)
                }
                winningsBuffer[index] += prevouslyWon + 1
            }

            scracthCardCopies += prevouslyWon
        } else {
            for (index in 0..<score) {
                while (winningsBuffer.size < score) {
                    winningsBuffer.addLast(0)
                }
                winningsBuffer[index]++
            }
        }
    }

    return scracthCardCopies
}