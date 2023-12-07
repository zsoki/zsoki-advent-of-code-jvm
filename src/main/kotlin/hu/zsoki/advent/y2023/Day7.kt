package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput

const val labelsDescending = "AKQJT98765432"

fun day7part1(inputFilename: String): Int {
    var labelCounters: IntArray

    val fiveOfAKinds = mutableListOf<HandToBid>()
    val fourOfAKinds = mutableListOf<HandToBid>()
    val fullHouses = mutableListOf<HandToBid>()
    val threeOfAKinds = mutableListOf<HandToBid>()
    val twoPairs = mutableListOf<HandToBid>()
    val onePairs = mutableListOf<HandToBid>()
    val highCards = mutableListOf<HandToBid>()

    loadInput(inputFilename).forEachLine { line ->
        val (handString, bidString) = line.split(' ')
        val handChars = handString.toCharArray()
        labelCounters = IntArray(13) { 0 }

        for (char in handChars) {
            labelCounters[modifiedLabelsDescending.indexOf(char)]++
        }

        var fiveOfAKind = false
        var fourOfAKind = false
        var threeOfAKind = false
        var twoOfAKinds = 0

        for (count in labelCounters) {
            when (count) {
                5 -> {
                    fiveOfAKind = true
                    break
                }
                4 -> {
                    fourOfAKind = true
                    break
                }
                3 -> threeOfAKind = true
                2 -> twoOfAKinds++
            }
        }

        val handToBid = HandToBid(handString, bidString.toInt())
        when {
            fiveOfAKind -> fiveOfAKinds.add(handToBid)
            fourOfAKind -> fourOfAKinds.add(handToBid)
            threeOfAKind && twoOfAKinds == 1 -> fullHouses.add(handToBid)
            threeOfAKind && twoOfAKinds == 0 -> threeOfAKinds.add(handToBid)
            twoOfAKinds == 2 -> twoPairs.add(handToBid)
            !threeOfAKind && twoOfAKinds == 1 -> onePairs.add(handToBid)
            else -> highCards.add(handToBid)
        }
    }

    val appendedList = highCards.sorted() +
            onePairs.sorted() +
            twoPairs.sorted() +
            threeOfAKinds.sorted() +
            fullHouses.sorted() +
            fourOfAKinds.sorted() +
            fiveOfAKinds.sorted()

    return appendedList.mapIndexed { index, handToBid -> handToBid.bid * (index + 1) }.sum()
}

data class HandToBid(val hand: String, val bid: Int) : Comparable<HandToBid> {
    override fun compareTo(other: HandToBid): Int {
        for (cardIndex in hand.indices) {
            val thisChar = hand[cardIndex]
            val otherChar = other.hand[cardIndex]

            if (thisChar == otherChar) continue

            // Lower is better
            val thisPower = labelsDescending.indexOf(thisChar)
            val otherPower = labelsDescending.indexOf(otherChar)

            when {
                thisPower < otherPower -> return 1
                thisPower > otherPower -> return -1
            }
        }
        return 0
    }
}


const val modifiedLabelsDescending = "AKQT98765432J"

fun day7part2(inputFilename: String): Int {
    var labelCounters: IntArray

    val fiveOfAKinds = mutableListOf<HandToBidMod>()
    val fourOfAKinds = mutableListOf<HandToBidMod>()
    val fullHouses = mutableListOf<HandToBidMod>()
    val threeOfAKinds = mutableListOf<HandToBidMod>()
    val twoPairs = mutableListOf<HandToBidMod>()
    val onePairs = mutableListOf<HandToBidMod>()
    val highCards = mutableListOf<HandToBidMod>()

    loadInput(inputFilename).forEachLine { line ->
        val (handString, bidString) = line.split(' ')
        val handChars = handString.toCharArray()
        labelCounters = IntArray(13) { 0 }

        for (char in handChars) {
            labelCounters[modifiedLabelsDescending.indexOf(char)]++
        }

        var fiveOfAKind = false
        var fourOfAKind = false
        var threeOfAKind = false
        var twoOfAKinds = 0

        val jokerExcludedSize = labelCounters.size - 2
        for (index in 0..jokerExcludedSize) {
            val count = labelCounters[index]
            when (count) {
                5 -> {
                    fiveOfAKind = true
                    break
                }
                4 -> {
                    fourOfAKind = true
                    break
                }
                3 -> threeOfAKind = true
                2 -> twoOfAKinds++
            }
        }

        val handToBid = HandToBidMod(handString, bidString.toInt())
        val jokerCount = labelCounters.last()
        when {
            fiveOfAKind -> fiveOfAKinds.add(handToBid)
            fourOfAKind -> if (jokerCount > 0) fiveOfAKinds.add(handToBid) else fourOfAKinds.add(handToBid)
            threeOfAKind && twoOfAKinds == 1 -> fullHouses.add(handToBid)
            threeOfAKind && twoOfAKinds == 0 -> when (jokerCount) {
                1 -> fourOfAKinds.add(handToBid)
                2 -> fiveOfAKinds.add(handToBid)
                else -> threeOfAKinds.add(handToBid)
            }
            twoOfAKinds == 2 -> if (jokerCount > 0) fullHouses.add(handToBid) else twoPairs.add(handToBid)
            !threeOfAKind && twoOfAKinds == 1 -> when (jokerCount) {
                1 -> threeOfAKinds.add(handToBid)
                2 -> fourOfAKinds.add(handToBid)
                3 -> fiveOfAKinds.add(handToBid)
                else -> onePairs.add(handToBid)
            }
            else -> when (jokerCount) {
                1 -> onePairs.add(handToBid)
                2 -> threeOfAKinds.add(handToBid)
                3 -> fourOfAKinds.add(handToBid)
                in 4..5 -> fiveOfAKinds.add(handToBid)
                else -> highCards.add(handToBid)
            }
        }
    }

    val appendedList = highCards.sorted() +
            onePairs.sorted() +
            twoPairs.sorted() +
            threeOfAKinds.sorted() +
            fullHouses.sorted() +
            fourOfAKinds.sorted() +
            fiveOfAKinds.sorted()

    return appendedList.mapIndexed { index, handToBid -> handToBid.bid * (index + 1) }.sum()
}

data class HandToBidMod(val hand: String, val bid: Int) : Comparable<HandToBidMod> {
    override fun compareTo(other: HandToBidMod): Int {
        for (cardIndex in hand.indices) {
            val thisChar = hand[cardIndex]
            val otherChar = other.hand[cardIndex]

            if (thisChar == otherChar) continue

            // Lower is better
            val thisPower = modifiedLabelsDescending.indexOf(thisChar)
            val otherPower = modifiedLabelsDescending.indexOf(otherChar)

            when {
                thisPower < otherPower -> return 1
                thisPower > otherPower -> return -1
            }
        }
        return 0
    }
}
