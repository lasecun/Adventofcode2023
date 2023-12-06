package day6

import java.io.File

fun main() {
    val d = Day06(File("src/day6/Day06.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day06(private val input: List<String>) {
    fun solvedPart1(): Long {
        val (times, distances) = input.map { it.split(" ").mapNotNull(String::toIntOrNull) }
        return (times zip distances)
            .map { (time, record) -> (1..<time).count { (time - it) * it > record } }
            .fold(1L, Long::times)
    }

    fun solvedPart2(): Int {
        val (time, distance) = input.map { it.replace(" ", "").substringAfter(':').toLong() }
        return (1..<time).count { (time - it) * it > distance }
    }
}