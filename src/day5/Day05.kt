package day5

import java.io.File

fun main() {

    val d = Day05(File("src/day5/Day05.txt").readText().split("\n\n"))
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day05(input: List<String>) {

    private val seeds = input.first().substringAfter(": ").split(" ").map { it.toLong() }
    private val seedRanges = seeds.chunked(2).map { (start, length) -> start..start + length }
    private val rangeMaps = input.filter { it.contains("map") }.map { mapBlock ->
        mapBlock.split("\n").drop(1).associate { line ->
            val (dst, src, length) = line.split(" ").map { it.toLong() }
            (src until src + length) to (dst until dst + length)
        }
    }

    fun solvedPart1(): Long {
        return seeds.map { it..it }.mapRangesIteratively()
    }

    fun solvedPart2(): Long {
        return seedRanges.mapRangesIteratively()
    }

    private fun mapRange(
        input: LongRange,
        rangeMap: Map<LongRange, LongRange>
    ): List<LongRange> = buildList {
        val outputs = rangeMap.mapNotNull { (src, dst) ->
            val start = maxOf(src.first, input.first)
            val end = minOf(src.last, input.last)
            val shift = dst.first - src.first
            if (end < start) null else (start + shift)..(end + shift)
        }.ifEmpty { listOf(input.first..input.last) }
        addAll(outputs)
        if (size > 1) {
            val theirMin = rangeMap.minOf { it.key.first }
            val theirMax = rangeMap.maxOf { it.key.last }
            if (input.first < theirMin) add(input.first until theirMin)
            if (input.last > theirMax) add(theirMax + 1..input.last)
        }
    }

    private fun List<LongRange>.mapRangesIteratively(): Long = flatMap { startingRange ->
        rangeMaps.fold(listOf(startingRange)) { ranges, rangeMap ->
            ranges.flatMap { range -> mapRange(range, rangeMap) }
        }
    }.minOf { it.first }
}


/*

Destination Range --> 50
Source Range to Start --> 98
range length --> 2

Destination Range --> 52
Source Range to Start --> 50
range length --> 48

Seed - Soil
98   - 50
99   - 51

 */