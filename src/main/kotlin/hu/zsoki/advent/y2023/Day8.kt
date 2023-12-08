package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

fun day8part1(inputFilename: String): Int {
    var directions = CharArray(0)
    val nodeMap = hashMapOf<String, Node>()

    var first = true
    loadInput(inputFilename).forEachLine { line ->
        if (first) {
            directions = line.toCharArray()
            first = false
            return@forEachLine
        } else if (line.isBlank()) {
            return@forEachLine
        }

        val words = line.split(' ').filter { it.isNotBlank() && it != "=" }.map { it.replace(Regex("[(),]"), "") }

        val thisNode = nodeMap.getOrDefault(words[0], Node(words[0]))
        if (!nodeMap.containsKey(thisNode.label)) nodeMap[thisNode.label] = thisNode

        val left = nodeMap.getOrDefault(words[1], Node(words[1]))
        if (!nodeMap.containsKey(left.label)) nodeMap[left.label] = left

        val right = nodeMap.getOrDefault(words[2], Node(words[2]))
        if (!nodeMap.containsKey(right.label)) nodeMap[right.label] = right

        thisNode.left = left
        thisNode.right = right
    }

    var currentNode = nodeMap["AAA"]!!
    var directionIndex = 0
    var steps = 0
    while (currentNode.label != "ZZZ") {
        if (directionIndex >= directions.size) directionIndex = 0
        val direction = directions[directionIndex]
        currentNode = when (direction) {
            'L' -> currentNode.left!!
            'R' -> currentNode.right!!
            else -> error("not possible")
        }
        steps++
        directionIndex++
    }

    return steps
}

fun day8part2(inputFilename: String): Long {
    var directions = CharArray(0)
    val nodeMap = hashMapOf<String, Node>()

    var first = true
    loadInput(inputFilename).forEachLine { line ->
        if (first) {
            directions = line.toCharArray()
            first = false
            return@forEachLine
        } else if (line.isBlank()) {
            return@forEachLine
        }

        val words = line.split(' ').filter { it.isNotBlank() && it != "=" }.map { it.replace(Regex("[(),]"), "") }

        val thisNode = nodeMap.getOrDefault(words[0], Node(words[0], initSize = directions.size))
        if (!nodeMap.containsKey(thisNode.label)) nodeMap[thisNode.label] = thisNode

        val left = nodeMap.getOrDefault(words[1], Node(words[1], initSize = directions.size))
        if (!nodeMap.containsKey(left.label)) nodeMap[left.label] = left

        val right = nodeMap.getOrDefault(words[2], Node(words[2], initSize = directions.size))
        if (!nodeMap.containsKey(right.label)) nodeMap[right.label] = right

        thisNode.left = left
        thisNode.right = right
    }

    val currentNodes = nodeMap.entries.filter { it.key.labelEnds('A') }.map { it.value }.toTypedArray()

    // Find loops first
    var currentNodeIndex = 0
    while (currentNodeIndex < currentNodes.size) {

        var currentNode = currentNodes[currentNodeIndex]
        var directionIndex = 0
        var steps = 0

        while (!(currentNode.visits[directionIndex] != -1 && currentNode.label.labelEnds('Z'))) {
            currentNode.visits[directionIndex] = steps

            val direction = directions[directionIndex]
            currentNode = when (direction) {
                'L' -> currentNode.left!!
                'R' -> currentNode.right!!
                else -> error("not possible")
            }

            steps++
            directionIndex++
            if (directionIndex >= directions.size) directionIndex = 0
        }

        currentNode.directionIndexLoopStart = directionIndex
        currentNode.stepCountLoopStart = currentNode.visits[directionIndex]
        currentNode.loopLength = steps - currentNode.stepCountLoopStart
        currentNodeIndex++
    }

    // Lowest common multiplier
    val endNodes = nodeMap.filter { it.key.labelEnds('Z') }
    val loopLengths = endNodes.map { it.value.loopLength }.toIntArray()
    val accValues = endNodes.map { it.value.loopLength.toLong() }.toLongArray()

    var compare = accValues[0]
    var minIndex = -1
    var min = Long.MAX_VALUE
    while (!accValues.all { it == compare }) {
        for (accIndex in accValues.indices) {
            val accValue = accValues[accIndex]
            if (accValue < min) {
                min = accValue
                minIndex = accIndex
            }
        }

        accValues[minIndex] += loopLengths[minIndex].toLong()
        minIndex = -1
        min = Long.MAX_VALUE
        compare = accValues[0]
    }

    return compare
}

data class Node(val label: String, var left: Node? = null, var right: Node? = null, var initSize: Int = 0) {
    var visits: IntArray = IntArray(initSize)  { -1 }
    var directionIndexLoopStart = 0
    var stepCountLoopStart = 0
    var loopLength = 0

    override fun toString(): String {
        return "$label = (${left?.label}, ${right?.label})"
    }
}

fun String.labelEnds(with: Char): Boolean = with == this[2]