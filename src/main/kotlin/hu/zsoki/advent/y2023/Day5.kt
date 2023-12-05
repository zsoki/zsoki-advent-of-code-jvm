package hu.zsoki.advent.y2023

import hu.zsoki.advent.loadInput


val sectionStarts = listOf(
    "seed-to-soil map:",
    "soil-to-fertilizer map:",
    "fertilizer-to-water map:",
    "water-to-light map:",
    "light-to-temperature map:",
    "temperature-to-humidity map:",
    "humidity-to-location map:",
)

fun day5part1(inputFilename: String): Long {
    var inputPhase = 0

    val seeds = mutableListOf<Long>()
    val seedtosoil = JumpingMap()
    val soiltofertilizer = JumpingMap()
    val fertilizertowater = JumpingMap()
    val watertolight = JumpingMap()
    val lighttotemperature = JumpingMap()
    val temperaturetohumidity = JumpingMap()
    val humiditytolocation = JumpingMap()

    loadInput(inputFilename).forEachLine { line ->
        if (line.isBlank()) {
            inputPhase++
            return@forEachLine
        } else if (line in sectionStarts) {
            return@forEachLine
        }

        var currentJumpingMap: JumpingMap = seedtosoil // Will change, just need init

        when (inputPhase) {
            0 -> {
                line.removePrefix("seeds: ").split(' ').map { it.toLong() }.forEach { seeds.add(it) }
                return@forEachLine
            }

            1 -> currentJumpingMap = seedtosoil
            2 -> currentJumpingMap = soiltofertilizer
            3 -> currentJumpingMap = fertilizertowater
            4 -> currentJumpingMap = watertolight
            5 -> currentJumpingMap = lighttotemperature
            6 -> currentJumpingMap = temperaturetohumidity
            7 -> currentJumpingMap = humiditytolocation
        }

        val (target, source, length) = line.split(' ').map { it.toLong() }
        currentJumpingMap.add(target, source, length)

    }

    val locations = seeds.map { seed ->
        val soil = seedtosoil.get(seed)
        val fertilizer = soiltofertilizer.get(soil)
        val water = fertilizertowater.get(fertilizer)
        val light = watertolight.get(water)
        val temperature = lighttotemperature.get(light)
        val humidity = temperaturetohumidity.get(temperature)
        val location = humiditytolocation.get(humidity)

        location
    }

    return locations.min()
}

fun day5part2(inputFilename: String): Long {
    var inputPhase = 0

    val seedRanges = mutableListOf<LongRange>()
    val seeds = mutableListOf<Long>()
    val seedtosoil = JumpingMap()
    val soiltofertilizer = JumpingMap()
    val fertilizertowater = JumpingMap()
    val watertolight = JumpingMap()
    val lighttotemperature = JumpingMap()
    val temperaturetohumidity = JumpingMap()
    val humiditytolocation = JumpingMap()

    loadInput(inputFilename).forEachLine { line ->
        if (line.isBlank()) {
            inputPhase++
            return@forEachLine
        } else if (line in sectionStarts) {
            return@forEachLine
        }

        var currentJumpingMap: JumpingMap = seedtosoil // Will change, just need init

        when (inputPhase) {
            0 -> {
                val splitSeeds = line.removePrefix("seeds: ").split(' ').iterator()
                while (splitSeeds.hasNext()) {
                    val seedRangeStart = splitSeeds.next().toLong()
                    val seedRangeLength = splitSeeds.next().toLong()
                    val seedRange = seedRangeStart..<(seedRangeStart + seedRangeLength)
                    seedRanges.add(seedRange)
                }
                return@forEachLine
            }

            1 -> currentJumpingMap = seedtosoil
            2 -> currentJumpingMap = soiltofertilizer
            3 -> currentJumpingMap = fertilizertowater
            4 -> currentJumpingMap = watertolight
            5 -> currentJumpingMap = lighttotemperature
            6 -> currentJumpingMap = temperaturetohumidity
            7 -> currentJumpingMap = humiditytolocation
        }

        val (target, source, length) = line.split(' ').map { it.toLong() }
        currentJumpingMap.add(target, source, length)

    }

    // TODO brute force, should improve??
    return seedRanges.minOf { seedRange ->
        val locations = seedRange.asSequence().map { seed ->
            val soil = seedtosoil.get(seed)
            val fertilizer = soiltofertilizer.get(soil)
            val water = fertilizertowater.get(fertilizer)
            val light = watertolight.get(water)
            val temperature = lighttotemperature.get(light)
            val humidity = temperaturetohumidity.get(temperature)
            val location = humiditytolocation.get(humidity)
            location
        }
        locations.min()
    }
}


class JumpingMap {
    private val jumpList = mutableListOf<RangeToTarget>()

    fun add(target: Long, source: Long, length: Long) {
        jumpList.add(RangeToTarget(source..<source + length, target))
    }

    fun get(toConvert: Long): Long {
        val range = jumpList.find { toConvert in it.range }
        val target = range?.target ?: return toConvert
        val toJump = toConvert - range.range.first
        return target + toJump
    }
}

data class RangeToTarget(val range: LongRange, val target: Long)
