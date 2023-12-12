package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day11part1(inputFileName: String): Int {
    val lineList = loadInput(inputFileName).readLines().toMutableList()
    val emptyRows = BooleanArray(lineList.size) { true }
    val emptyCols = BooleanArray(lineList[0].length) { true }
    val galaxies = mutableListOf<Galaxy>()
    var shortestDistancesSum = 0

    for (rowInd in lineList.indices) {
        for (colInd in lineList[rowInd].indices) {
            if (lineList[rowInd][colInd] == '#') {
                galaxies.add(Galaxy(row = rowInd, col = colInd))
                emptyRows[rowInd] = false
                emptyCols[colInd] = false
            }
        }
    }

    val emptyRowString = ".".repeat(lineList[0].length)

    // Expand rows
    var emptyRowsCount = 0
    var emptyRowsStartIndex = -1
    var emptyRowOffset = 0
    for (emptyRowInd in emptyRows.indices) {
        val emptyRow = emptyRows[emptyRowInd]
        if (emptyRow) {
            if (emptyRowsCount == 0) {
                emptyRowsStartIndex = emptyRowInd
            }
            emptyRowsCount++
        } else {
            if (emptyRowsCount > 0) {
                repeat(emptyRowsCount) {
                    lineList.add(emptyRowsStartIndex + emptyRowOffset, emptyRowString)
                }
                galaxies.filter { it.row >= emptyRowInd + emptyRowOffset }.forEach { it.row += emptyRowsCount }
                emptyRowOffset += emptyRowsCount
            }
            emptyRowsCount = 0
            emptyRowsStartIndex = -1
        }
    }

    // Expand cols
    var emptyColsCount = 0
    var emptyColsStartIndex = -1
    var emptyColOffset = 0
    for (emptyColInd in emptyCols.indices) {
        val emptyCol = emptyCols[emptyColInd]
        if (emptyCol) {
            if (emptyColsCount == 0) {
                emptyColsStartIndex = emptyColInd
            }
            emptyColsCount++
        } else {
            if (emptyColsCount > 0) {
                repeat(emptyColsCount) {
                    lineList.replaceAll { line ->
                        "${
                            line.subSequence(
                                0,
                                emptyColsStartIndex + emptyColOffset
                            )
                        }${".".repeat(emptyColsCount)}${
                            line.subSequence(
                                emptyColsStartIndex + emptyColOffset,
                                line.length
                            )
                        }"
                    }
                }
                galaxies.filter { it.col >= emptyColInd + emptyColOffset }.forEach { it.col += emptyColsCount }
                emptyColOffset += emptyColsCount
            }
            emptyColsCount = 0
            emptyColsStartIndex = -1
        }
    }

    println(lineList)

    for (galaxyInd in galaxies.indices) {
        val galaxy = galaxies[galaxyInd]
        for (otherInd in (galaxyInd + 1)..<galaxies.size) {
            val other = galaxies[otherInd]
            val distance = galaxy.distance(other)
            shortestDistancesSum += distance
        }
    }

    return shortestDistancesSum
}

fun day11part2(inputFileName: String, factor: Long): Long {
    val lineList = loadInput(inputFileName).readLines().toMutableList()
    val emptyRows = BooleanArray(lineList.size) { true }
    val emptyCols = BooleanArray(lineList[0].length) { true }
    val galaxies = mutableListOf<GalaxyLong>()
    var shortestDistancesSum = 0L

    for (rowInd in lineList.indices) {
        for (colInd in lineList[rowInd].indices) {
            if (lineList[rowInd][colInd] == '#') {
                galaxies.add(GalaxyLong(row = rowInd.toLong(), col = colInd.toLong()))
                emptyRows[rowInd] = false
                emptyCols[colInd] = false
            }
        }
    }

    // Expand rows
    var emptyRowsCount = 0L
    var emptyRowOffset = 0L
    for (emptyRowInd in emptyRows.indices) {
        val emptyRow = emptyRows[emptyRowInd]
        if (emptyRow) {
            emptyRowsCount++
        } else {
            if (emptyRowsCount > 0) {
                val expanse = (emptyRowsCount * factor) - 1 // No idea about the -1?
                galaxies.filter { it.row >= emptyRowInd + emptyRowOffset }.forEach { it.row += expanse }
                emptyRowOffset += expanse
            }
            emptyRowsCount = 0
        }
    }

    // Expand cols
    var emptyColsCount = 0L
    var emptyColOffset = 0L
    for (emptyColInd in emptyCols.indices) {
        val emptyCol = emptyCols[emptyColInd]
        if (emptyCol) {
            emptyColsCount++
        } else {
            if (emptyColsCount > 0) {
                val expanse = (emptyColsCount * factor) - 1
                galaxies.filter { it.col >= emptyColInd + emptyColOffset }.forEach { it.col += expanse }
                emptyColOffset += expanse
            }
            emptyColsCount = 0
        }
    }

    for (galaxyInd in galaxies.indices) {
        val galaxy = galaxies[galaxyInd]
        for (otherInd in (galaxyInd + 1)..<galaxies.size) {
            shortestDistancesSum += galaxy.distance(galaxies[otherInd])
        }
    }

    return shortestDistancesSum
}

data class Galaxy(var row: Int, var col: Int) {
    fun distance(other: Galaxy): Int {
        val verticalDist = when {
            this.row > other.row -> this.row - other.row
            this.row < other.row -> other.row - this.row
            else -> 0
        }
        val horizontalDist = when {
            this.col > other.col -> this.col - other.col
            this.col < other.col -> other.col - this.col
            else -> 0
        }
        return verticalDist + horizontalDist
    }
}

data class GalaxyLong(var row: Long, var col: Long) {
    fun distance(other: GalaxyLong): Long {
        val verticalDist = when {
            this.row > other.row -> this.row - other.row
            this.row < other.row -> other.row - this.row
            else -> 0
        }
        val horizontalDist = when {
            this.col > other.col -> this.col - other.col
            this.col < other.col -> other.col - this.col
            else -> 0
        }
        return verticalDist + horizontalDist
    }
}