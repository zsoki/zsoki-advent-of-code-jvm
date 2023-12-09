package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day9part1(inputFilename: String): Int {
    var extrapolatedSum = 0

    loadInput(inputFilename).forEachLine { line ->
        val values = line.split(' ').map { it.toInt() }.toMutableList()
        val diffSequences = mutableListOf<MutableList<Int>>()

        var first = true
        while (diffSequences.isEmpty() || !diffSequences.last().all { it == 0 }) {
            val diffSequence = mutableListOf<Int>()

            val valueList = if (first) {
                first = false
                values
            } else diffSequences.last()

            for (valIndex in valueList.size - 1 downTo 1) {
                val first = valueList[valIndex]
                val toSubtract = valueList[valIndex - 1]
                diffSequence.add(0, first - toSubtract)
            }

            diffSequences.add(diffSequence)
        }

        for (diffSeqIndex in diffSequences.size - 1 downTo 0) {
            val extrapolateList: MutableList<Int> = if (diffSeqIndex == 0) values else diffSequences[diffSeqIndex - 1]
            extrapolateList.add(extrapolateList.last() + diffSequences[diffSeqIndex].last())
        }

        extrapolatedSum += values.last()
    }

    return extrapolatedSum
}

fun day9part2(inputFilename: String): Int {
    var extrapolatedSum = 0

    loadInput(inputFilename).forEachLine { line ->
        val values = line.split(' ').map { it.toInt() }.toMutableList()
        val diffSequences = mutableListOf<MutableList<Int>>()

        var first = true
        while (diffSequences.isEmpty() || !diffSequences.last().all { it == 0 }) {
            val diffSequence = mutableListOf<Int>()

            val valueList = if (first) {
                first = false
                values
            } else diffSequences.last()

            for (valIndex in valueList.size - 1 downTo 1) {
                val first = valueList[valIndex]
                val toSubtract = valueList[valIndex - 1]
                diffSequence.add(0, first - toSubtract)
            }

            diffSequences.add(diffSequence)
        }

        for (diffSeqIndex in diffSequences.size - 1 downTo 0) {
            val extrapolateList: MutableList<Int> = if (diffSeqIndex == 0) values else diffSequences[diffSeqIndex - 1]
            extrapolateList.add(0, extrapolateList.first() - diffSequences[diffSeqIndex].first())
        }

        extrapolatedSum += values.first()
    }

    return extrapolatedSum
}
