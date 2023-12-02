package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput
import java.util.regex.Pattern

val RED_MAX = 12
val GREEN_MAX = 13
val BLUE_MAX = 14

val gamePattern: Pattern = Pattern.compile("Game (\\d+): ")
val colorPattern: Pattern = Pattern.compile("(\\d+) (red|blue|green)")

fun day2Part1(inputFilename: String): Int {

    var possibleGames = 0

    loadInput(inputFilename).forEachLine { game ->
        val gameMatcher = gamePattern.matcher(game)
        gameMatcher.find()

        val gameNum = gameMatcher.group(1).toInt()

        val prefixRemoved = game.removePrefix(gameMatcher.group(0))
        val sets = prefixRemoved.trim().split(";")

        for (set in sets) {
            val pulls = set.trim().split(",")
            for (pull in pulls) {
                val colorMatcher = colorPattern.matcher(pull)
                colorMatcher.find()
                val colorNum = colorMatcher.group(1).toInt()
                val colorName = colorMatcher.group(2)
                when (colorName) {
                    "red" -> if (colorNum > RED_MAX) return@forEachLine
                    "green" -> if (colorNum > GREEN_MAX) return@forEachLine
                    "blue" -> if (colorNum > BLUE_MAX) return@forEachLine
                }
            }
        }
        possibleGames += gameNum
    }

    return possibleGames
}

fun day2Part2(inputFilename: String): Int {

    var sum = 0

    loadInput(inputFilename).forEachLine { game ->
        val gameMatcher = gamePattern.matcher(game)
        gameMatcher.find()
        val prefixRemoved = game.removePrefix(gameMatcher.group(0))
        val sets = prefixRemoved.trim().split(";")

        var redGameMax = 0
        var greenGameMax = 0
        var blueGameMax = 0

        for (set in sets) {
            val pulls = set.trim().split(",")
            for (pull in pulls) {
                val colorMatcher = colorPattern.matcher(pull)
                colorMatcher.find()
                val colorNum = colorMatcher.group(1).toInt()
                val colorName = colorMatcher.group(2)
                when (colorName) {
                    "red" -> if (colorNum > redGameMax) redGameMax = colorNum
                    "green" -> if (colorNum > greenGameMax) greenGameMax = colorNum
                    "blue" -> if (colorNum > blueGameMax) blueGameMax = colorNum
                }
            }
        }

        sum += redGameMax * greenGameMax * blueGameMax
    }

    return sum
}