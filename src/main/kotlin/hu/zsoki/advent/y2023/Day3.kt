package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day3part1(inputFilename: String): Int {
    var sumOfMatchedNumbers = 0

    val lineList = loadInput(inputFilename).readLines()

    val lines = lineList.map {
        ("$it.").toCharArray() // Appending each line with a . to correctly check line ending with number
    }.toTypedArray()

    var row = 0
    while (row < lines.size) {
        val line = lines[row]

        var col = 0
        var startCol = -1
        var endCol = -1
        val numCharacters = mutableListOf<Char>()
        var number = -1
        while (col < line.size) {

            val char = line[col]
            when {
                isNum(char) -> {
                    if (startCol == -1) startCol = col
                    numCharacters.add(char)
                }

                else -> {
                    if (startCol != -1) {
                        endCol = col - 1
                        number = String(numCharacters.toCharArray()).toInt()

                        // Check neighbours
                        var neighbourRow = row - 1
                        neighbourCheck@ while (neighbourRow <= row + 1) {

                            if (neighbourRow < 0 || neighbourRow >= lines.size) {
                                neighbourRow++
                                continue // out of bounds on top or at bottom
                            }

                            var neighbourCol = startCol - 1
                            while (neighbourCol <= endCol + 1) {

                                if (neighbourCol < 0 || neighbourCol >= line.size) {
                                    neighbourCol++
                                    continue // out of bounds on left or right
                                }

                                // Skip self
                                if (neighbourRow == row && neighbourCol >= startCol && neighbourCol <= endCol) {
                                    neighbourCol++
                                    continue
                                }

                                val neighbourChar = lines[neighbourRow][neighbourCol]
                                if (!isNum(neighbourChar) && neighbourChar != '.') {
                                    // NUMBER FOUND
                                    sumOfMatchedNumbers += number
                                    break@neighbourCheck
                                }

                                neighbourCol++
                            }

                            neighbourRow++
                        }

                        // Neighbour checking finished, reset state
                        startCol = -1
                        endCol = -1
                        numCharacters.clear()
                        number = -1
                    }
                }
            }

            col++
        }
        row++
    }

    return sumOfMatchedNumbers
}

fun day3part2(inputFilename: String): Int {
    val gearMap = hashMapOf<String, GearData>()

    val lineList = loadInput(inputFilename).readLines()

    val lines = lineList.map {
        ("$it.").toCharArray() // Appending each line with a . to correctly check line ending with number
    }.toTypedArray()

    var row = 0
    while (row < lines.size) {
        val line = lines[row]

        var col = 0
        var startCol = -1
        var endCol = -1
        val numCharacters = mutableListOf<Char>()
        var number = -1
        while (col < line.size) {

            val char = line[col]
            when {
                isNum(char) -> {
                    if (startCol == -1) startCol = col
                    numCharacters.add(char)
                }

                else -> {
                    if (startCol != -1) {
                        endCol = col - 1
                        number = String(numCharacters.toCharArray()).toInt()

                        // Check neighbours
                        var neighbourRow = row - 1
                        neighbourCheck@ while (neighbourRow <= row + 1) {

                            if (neighbourRow < 0 || neighbourRow >= lines.size) {
                                neighbourRow++
                                continue // out of bounds on top or at bottom
                            }

                            var neighbourCol = startCol - 1
                            while (neighbourCol <= endCol + 1) {

                                if (neighbourCol < 0 || neighbourCol >= line.size) {
                                    neighbourCol++
                                    continue // out of bounds on left or right
                                }

                                // Skip self
                                if (neighbourRow == row && neighbourCol >= startCol && neighbourCol <= endCol) {
                                    neighbourCol++
                                    continue
                                }

                                val neighbourChar = lines[neighbourRow][neighbourCol]
                                if (neighbourChar == '*') {
                                    // ADJACENT GEAR FOUND
                                    val key = "[$neighbourRow, $neighbourCol]"
                                    val gearData = gearMap.getOrDefault(key, GearData(0, 1))
                                    gearData.adjacentParts++
                                    gearData.ratio *= number
                                    gearMap[key] = gearData
                                    break@neighbourCheck
                                }

                                neighbourCol++
                            }

                            neighbourRow++
                        }

                        // Neighbour checking finished, reset state
                        startCol = -1
                        endCol = -1
                        numCharacters.clear()
                        number = -1
                    }
                }
            }

            col++
        }
        row++
    }

    return gearMap.filter { it.value.adjacentParts == 2 }.map { it.value.ratio }.sum()
}

data class GearData(var adjacentParts: Int, var ratio: Int)

private fun isNum(char: Char) = char in '0'..'9'