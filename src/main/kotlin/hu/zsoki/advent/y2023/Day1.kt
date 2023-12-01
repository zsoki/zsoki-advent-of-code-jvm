package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day1Part1(inputFilename: String): Int {
    var sum = 0

    loadInput(inputFilename).forEachLine { line ->
        var tens = -1
        var ones = -1

        // Iterate from left
        val charArray = line.chars().toArray()

        var leftIndex = 0
        while (tens <= 0 && leftIndex < charArray.size) {
            val char = charArray[leftIndex]
            if (char in 49..57) {
                tens = (char - 48) * 10
            }
            leftIndex++
        }

        var rightIndex = charArray.size - 1
        while (ones < 0 && rightIndex >= 0) {
            val char = charArray[rightIndex]
            if (char in 48..57) {
                ones = (char - 48)
            }
            rightIndex--
        }

        sum += ones + tens
    }

    return sum
}

// Forward
const val oCode = 'o'.code
val oneArray = "one".chars().toArray()!!

const val tCode = 't'.code
val twoArray = "two".chars().toArray()!!
val threeArray = "three".chars().toArray()!!

const val fCode = 'f'.code
val fourArray = "four".chars().toArray()!!
val fiveArray = "five".chars().toArray()!!

const val sCode = 's'.code
val sixArray = "six".chars().toArray()!!
val sevenArray = "seven".chars().toArray()!!

const val eCode = 'e'.code
val eightArray = "eight".chars().toArray()!!

const val nCode = 'n'.code
val nineArray = "nine".chars().toArray()!!

// Reverse
const val rCode = 'r'.code
const val xCode = 'x'.code

fun day1Part2(inputFilename: String): Int {
    var sum = 0

    loadInput(inputFilename).forEachLine { line ->
        var tens = -1
        var ones = -1

        // Iterate from left
        val charArray = line.chars().toArray()

        var leftIndex = 0
        while (tens <= 0 && leftIndex < charArray.size) {
            when (val char = charArray[leftIndex]) {
                in '1'.code..'9'.code -> {
                    tens = (char - '0'.code) * 10
                }
                oCode -> if (sliceMatchForward(charArray, oneArray, leftIndex)) tens = 10
                tCode -> {
                    if (sliceMatchForward(charArray, twoArray, leftIndex)) tens = 20
                    else if (sliceMatchForward(charArray, threeArray, leftIndex)) tens = 30
                }
                fCode -> {
                    if (sliceMatchForward(charArray, fourArray, leftIndex)) tens = 40
                    else if (sliceMatchForward(charArray, fiveArray, leftIndex)) tens = 50
                }
                sCode -> {
                    if (sliceMatchForward(charArray, sixArray, leftIndex)) tens = 60
                    else if (sliceMatchForward(charArray, sevenArray, leftIndex)) tens = 70
                }
                eCode -> if (sliceMatchForward(charArray, eightArray, leftIndex)) tens = 80
                nCode -> if (sliceMatchForward(charArray, nineArray, leftIndex)) tens = 90
            }
            leftIndex++
        }

        var rightIndex = charArray.size - 1
        while (ones < 0 && rightIndex >= 0) {
            when (val char = charArray[rightIndex]) {
                in '0'.code..'9'.code -> {
                    ones = (char - '0'.code)
                }
                eCode -> {
                    if (sliceMatchReverse(charArray, oneArray, rightIndex)) ones = 1
                    else if (sliceMatchReverse(charArray, threeArray, rightIndex)) ones = 3
                    else if (sliceMatchReverse(charArray, fiveArray, rightIndex)) ones = 5
                    else if (sliceMatchReverse(charArray, nineArray, rightIndex)) ones = 9
                }
                oCode -> if (sliceMatchReverse(charArray, twoArray, rightIndex)) ones = 2
                rCode -> if (sliceMatchReverse(charArray, fourArray, rightIndex)) ones = 4
                xCode -> if (sliceMatchReverse(charArray, sixArray, rightIndex)) ones = 6
                nCode -> if (sliceMatchReverse(charArray, sevenArray, rightIndex)) ones = 7
                tCode -> if (sliceMatchReverse(charArray, eightArray, rightIndex)) ones = 8
            }
            rightIndex--
        }

        sum += ones + tens
    }

    return sum
}

private fun sliceMatchForward(lineCharCodeArray: IntArray, numWordCharArray: IntArray, leftIndex: Int) =
    try {
        numWordCharArray.contentEquals(lineCharCodeArray.sliceArray(leftIndex..<leftIndex + numWordCharArray.size))
    } catch (e: Exception) {
        false
    }

private fun sliceMatchReverse(lineCharCodeArray: IntArray, numWordCharArray: IntArray, rightIndex: Int) =
    try {
        numWordCharArray.contentEquals(lineCharCodeArray.sliceArray(rightIndex - numWordCharArray.size + 1..rightIndex))
    } catch (e: Exception) {
        false
    }