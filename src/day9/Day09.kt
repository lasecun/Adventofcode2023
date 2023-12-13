package day9

import java.io.File

fun main() {
    val d = Day09(File("src/day9/Day09.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day09(input: List<String>) {
    private val nums = input.mapNotNull { line ->
        line.split(' ').mapNotNull { it.toIntOrNull() }.ifEmpty { null }
    }

    fun solvedPart1(): Long {
        return nums.sumOf { it.extrapolate() }
    }

    fun solvedPart2(): Long {
        return nums.sumOf { it.asReversed().extrapolate() }
    }

    companion object {
        private fun List<Int>.extrapolate(): Long {
            var c = 1L
            var s = 0L
            for ((i, x) in withIndex()) {
                s = c * x - s
                c = c * (size - i) / (i + 1)
            }
            return s
        }
    }
}