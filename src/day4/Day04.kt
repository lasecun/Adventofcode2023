package day4

import java.io.File
import kotlin.math.pow

fun main() {

    val d = Day04(File("src/day4/Day04.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

private val NUMBER_REGEX = "\\d+".toRegex()

class Day04(private val input: List<String>) {
    fun solvedPart1(): Int {
        var total = 0
        input.forEach { line ->
            val card = line.split(":")
            val cardNumbers = card[1].split("|")
            var totalWins = 0

            // Get the list of winning numbers
            val winningGroup = cardNumbers[0]
            val winningList: MutableList<Int> = mutableListOf()
            NUMBER_REGEX.findAll(winningGroup).forEach {
                winningList.add(it.value.toInt())
            }

            // Get the list of elf numbers
            val elfNumbers = cardNumbers[1]
            val elfNumberList: MutableList<Int> = mutableListOf()
            NUMBER_REGEX.findAll(elfNumbers).forEach {
                elfNumberList.add(it.value.toInt())
            }

            winningList.forEach {
                if (findInAList(elfNumberList, it)) {
                    totalWins++
                }
            }
            total += calculateWorth(totalWins)
        }
        return total
    }

    private fun calculateWorth(totalWins: Int): Int {
        val baseWorth = 2
        return (baseWorth).toDouble().pow(totalWins - 1).toInt()
    }

    private fun findInAList(list: List<Int>, value: Int): Boolean {
        return list.any { it == value }
    }

    fun solvedPart2(): Int {
        return 2
    }
}