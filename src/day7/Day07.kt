package day7

import java.io.File

fun main() {
    val d = Day07(File("src/day7/Day07.txt").readLines())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day07(private val input: List<String>) {

    fun solvedPart1(): Int {
        return totalWinnings(input)
    }

    fun solvedPart2(): Int {
        return totalWinnings(input, nonNumbers2)
    }

    private val nonNumbers2 = mapOf(
        'J' to 1,
        'T' to 10,
        'Q' to 11,
        'K' to 12,
        'A' to 13
    )

    private val nonNumbers = mapOf(
        'T' to 10,
        'J' to 11,
        'Q' to 12,
        'K' to 13,
        'A' to 14
    )

    private fun parse(input: List<String>, faces: Map<Char, Int>): List<Hand> {
        return input.mapIndexed { idx, line ->
            val (cards, bid) = line.split(" ")
            val parsedCards = cards.map {
                faces[it] ?: it.digitToInt()
            }
            val sets = setSizes(parsedCards)
            Hand(parsedCards, bid.toInt(), idx, sets)
        }
    }

    private val strengthComparator: Comparator<Hand> = Comparator { a, b ->
        a.setSizes.zip(b.setSizes).take(2).forEach { (aSize, bSize) ->
            if (aSize != bSize) {
                return@Comparator aSize.compareTo(bSize)
            }
        }
        a.cards.zip(b.cards).forEach { (aCard, bCard) ->
            if (aCard != bCard) {
                return@Comparator aCard.compareTo(bCard)
            }
        }

        return@Comparator 0
    }

    private fun setSizes(cards: List<Int>): List<Int> {
        val sets = (2..14).map { c ->
            cards.count { it == c }
        }.sortedDescending().toMutableList()
        sets[0] += cards.count { it == 1 }
        return sets
    }

    private fun totalWinnings(input: List<String>, faces: Map<Char, Int> = nonNumbers): Int {
        val hands = parse(input, faces).sortedWith(strengthComparator)
        return hands.mapIndexed { idx, hand ->
            hand.bid * (idx + 1)
        }.sum()
    }

}

data class Hand(
    val cards: List<Int>,
    val bid: Int,
    val position: Int,
    val setSizes: List<Int>
)
