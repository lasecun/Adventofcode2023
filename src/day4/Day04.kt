package day4

import java.io.File
import kotlin.math.pow

fun main() {

    val d = Day04(File("src/day4/Day04_test.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

private val NUMBER_REGEX = "\\d+".toRegex()
const val BASE_WORTH: Double = 2.0

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
        return BASE_WORTH.pow(totalWins - 1).toInt()
    }

    private fun findInAList(list: List<Int>, value: Int): Boolean {
        return list.any { it == value }
    }

    fun solvedPart2(): Long {
        val cardCopies = mutableMapOf<Int, Long>()
        val cards = input.toCards()
        return cards.sumOf { card ->
            val winPoints = card.winNumbers.intersect(card.ownNumbers).count()
            val times = 1 + cardCopies.getOrDefault(card.num, 0)
            for (nextCardNum in card.num + 1..(card.num + winPoints).coerceAtMost(cards.size)) {
                cardCopies.merge(nextCardNum, times) { value, current -> value + current }
            }
            times
        }
    }

    data class Card(val num: Int, val winNumbers: Set<Long>, val ownNumbers: Set<Long>)

    private fun List<String>.toCards(): List<Card> =
        map { line ->
            val parts = line.split(':', '|').map { it.trim() }
            Card(
                num = parts[0].split(" ").last().toInt(),
                winNumbers = parts[1].split(" ").filter { it.isNotBlank() }.map { it.toLong() }.toSet(),
                ownNumbers = parts[2].split(" ").filter { it.isNotBlank() }.map { it.toLong() }.toSet()
            )
        }
}