package day1

import readInput

fun main() {

    val d = Day01(readInput("Day01"))
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day01(private val input: List<String>) {

    fun solvedPart1(): Int {
        var total = 0
        input.forEach { row ->
            val result = row.filter { it.isDigit() }
            total += getNumber(result)
        }
        return total
    }

    fun solvedPart2(): Int {
        var total = 0
        input.forEach { row ->
            val result = translateRow(row)
            total += getNumber(result)
        }
        return total
    }

    private fun findFirstDigit(row: String): String {
        var word = ""
        row.forEach { letter ->
            word += letter
            if (letter.isDigit()) return letter.toString()
            with(word) {
                when {
                    contains("one") -> return "1"
                    contains("two") -> return "2"
                    contains("three") -> return "3"
                    contains("four") -> return "4"
                    contains("five") -> return "5"
                    contains("six") -> return "6"
                    contains("seven") -> return "7"
                    contains("eight") -> return "8"
                    contains("nine") -> return "9"
                }
            }
        }
        return ""
    }

    private fun findLastDigit(row: String): String {
        var word = ""
        row.reversed().forEach { letter ->
            word += letter
            if (letter.isDigit()) return letter.toString()
            with(word) {
                when {
                    contains("one".reversed()) -> return "1"
                    contains("two".reversed()) -> return "2"
                    contains("three".reversed()) -> return "3"
                    contains("four".reversed()) -> return "4"
                    contains("five".reversed()) -> return "5"
                    contains("six".reversed()) -> return "6"
                    contains("seven".reversed()) -> return "7"
                    contains("eight".reversed()) -> return "8"
                    contains("nine".reversed()) -> return "9"
                }
            }
        }
        return ""
    }

    private fun translateRow(row: String): String {
        val firstDigit = findFirstDigit(row)
        val lastDigit = findLastDigit(row)
        return (firstDigit + lastDigit)
    }

    private fun getNumber(result: String): Int {
        return when (result.length) {
            0 -> {
                0
            }

            1 -> {
                Integer.parseInt(result + result)
            }

            2 -> {
                Integer.parseInt(result)
            }

            else -> {
                val first = result.first().toString()
                val last = result.last().toString()
                Integer.parseInt(first + last)
            }
        }
    }

}
