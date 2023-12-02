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
        var solution = 0
        input.forEachIndexed { index, s ->
            val games = s.split(":")
            val turn = games[1].split(";")
            val targetList: MutableList<Pair<Int, String>> = mutableListOf()
            var totalGame = 1
            turn.forEach {
                val options = it.split(",")
                options.forEach { option ->
                    val input = option.split(" ")
                    if (targetList.isEmpty() || targetList.find { item -> item.second == input[2] } == null) {
                        targetList.add(Pair(input[1].toInt(), input[2]))
                    } else {
                        val tempTargetList: MutableList<Pair<Int, String>> = mutableListOf()
                        targetList.forEach { target ->
                            if (input[2] == target.second && input[1].toInt() > target.first) {
                                tempTargetList.add(Pair(input[1].toInt(), input[2]))
                            }
                        }
                        if (tempTargetList.isNotEmpty()) {
                            val test = targetList.find { it.second == tempTargetList[0].second }
                            val id = targetList.indexOf(test)
                            targetList.set(id, tempTargetList[0])
                        }
                    }
                }
            }
            targetList.forEach {
                totalGame *= it.first
            }
            solution += totalGame
        }
        return solution
    }

}
