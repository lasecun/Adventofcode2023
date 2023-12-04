package day4

import java.io.File
import kotlin.math.pow

fun main() {

    val d = Day04(File("src/day4/Day04_test.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

const val BASE_WORTH: Double = 2.0

class Day04(private val input: List<String>) {
    fun solvedPart1(): Int {
        val cards = input.toCards()
        return cards.sumOf { card ->
            var totalWins = 0

            card.winNumbers.forEach {
                if (findInAList(card.elfNumbers.toList(), it)) {
                    totalWins++
                }
            }
            calculateWorth(totalWins)
        }
    }

    private fun calculateWorth(totalWins: Int): Int {
        return BASE_WORTH.pow(totalWins - 1).toInt()
    }

    private fun findInAList(list: List<Long>, value: Long): Boolean {
        return list.any { it == value }
    }

    fun solvedPart2(): Long {
        val cardCopies = mutableMapOf<Int, Long>()
        val cards = input.toCards()
        return cards.sumOf { card ->
            val winPoints = card.winNumbers.intersect(card.elfNumbers).count()
            val times = 1 + cardCopies.getOrDefault(card.num, 0)
            for (nextCardNum in card.num + 1..(card.num + winPoints).coerceAtMost(cards.size)) {
                cardCopies.merge(nextCardNum, times) { value, current -> value + current }
            }
            times
        }
    }

    data class Card(val num: Int, val winNumbers: Set<Long>, val elfNumbers: Set<Long>)

    private fun List<String>.toCards(): List<Card> =
        map { line ->
            val parts = line.split(':', '|').map { it.trim() }
            Card(
                num = parts[0].split(" ").last().toInt(),
                winNumbers = parts[1].split(" ").filter { it.isNotBlank() }.map { it.toLong() }.toSet(),
                elfNumbers = parts[2].split(" ").filter { it.isNotBlank() }.map { it.toLong() }.toSet()
            )
        }
}