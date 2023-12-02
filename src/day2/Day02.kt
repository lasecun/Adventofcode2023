package day2

import java.io.File

fun main() {

    val d = Day01(File("src/day2/Day02.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day01(private val input: List<String>) {

    fun solvedPart1(): Int {
        return 1
    }

    fun solvedPart2(): Int {
        return 2
    }

}
