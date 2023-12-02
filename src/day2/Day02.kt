package day2

import java.io.File

fun main() {

    val d = Day01(File("src/day2/Day02.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day01(private val input: List<String>) {

    fun solvedPart1(): Int {

        var total = 0
        input.forEachIndexed { index, s ->
            var isGameValid = true
            val games = s.split(":")
            val turn = games[1].split(";")
            turn.forEach {
                val option = it.split(",")
                option.forEach {
                    val input = it.split(" ")
                    if (input[2] == "red" && input[1].toInt() > "12".toInt() ||
                        input[2] == "green" && input[1].toInt() > "13".toInt() ||
                        input[2] == "blue" && input[1].toInt() > "14".toInt()
                    ) {
                        isGameValid = false
                    }
                }
            }
            if (isGameValid) {
                total += index + 1
            }

        }
        return total
    }

    fun solvedPart2(): Int {
        return 2
    }

}
