/**
 * https://adventofcode.com/2022/day/1
 * --- Day 2: Rock Paper Scissors ---
 */

fun day2Part1(): Int {
    return loadInput("day2.txt").useLines { lines: Sequence<String> ->
        return lines.fold(0) { acc: Int, line: String ->
            acc + when (line) {
                "A X" -> 1 + 3 // Rock     vs Rock     (1) = Draw (3)
                "A Y" -> 2 + 6 // Rock     vs Paper    (2) = Win  (6)
                "A Z" -> 3 + 0 // Rock     vs Scissors (3) = Loss (0)
                "B X" -> 1 + 0 // Paper    vs Rock     (1) = Loss (0)
                "B Y" -> 2 + 3 // Paper    vs Paper    (2) = Draw (3)
                "B Z" -> 3 + 6 // Paper    vs Scissors (3) = Win  (6)
                "C X" -> 1 + 6 // Scissors vs Rock     (1) = Win  (6)
                "C Y" -> 2 + 0 // Scissors vs Paper    (2) = Loss (0)
                "C Z" -> 3 + 3 // Scissors vs Scissors (3) = Draw (3)
                else -> 0
            }
        }
    }
}

fun day2Part2(): Int {
    return loadInput("day2.txt").useLines { lines: Sequence<String> ->
        return lines.fold(0) { acc: Int, line: String ->
            acc + when (line) {
                "A X" -> 3 + 0 // Rock     vs Scissors (3) = Loss (0)
                "A Y" -> 1 + 3 // Rock     vs Rock     (1) = Draw (3)
                "A Z" -> 2 + 6 // Rock     vs Paper    (2) = Win  (6)
                "B X" -> 1 + 0 // Paper    vs Rock     (1) = Loss (0)
                "B Y" -> 2 + 3 // Paper    vs Paper    (2) = Draw (3)
                "B Z" -> 3 + 6 // Paper    vs Scissors (3) = Win  (6)
                "C X" -> 2 + 0 // Scissors vs Paper    (2) = Loss (0)
                "C Y" -> 3 + 3 // Scissors vs Scissors (3) = Draw (3)
                "C Z" -> 1 + 6 // Scissors vs Rock     (1) = Win  (6)
                else -> 0
            }
        }
    }
}