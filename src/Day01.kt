fun main() {

    fun getNumber(result: String): Int {
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

    fun part1(input: List<String>): Int {
        var total = 0
        input.forEach { row ->
            val result = row.filter { it.isDigit() }
            total += getNumber(result)
        }
        println("TOTAL --> $total")
        return total
    }

    fun findFirstDigit(row: String): String{
        var word = ""
        row.forEach { letter ->
            word += letter
            if(letter.isDigit()) return letter.toString()
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

    fun findLastDigit(row: String): String{
        var word = ""
        row.reversed().forEach { letter ->
            word += letter
            if(letter.isDigit()) return letter.toString()
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

    fun translateRow(row: String): String{
        val firstDigit = findFirstDigit(row)
        println("First Digit Found --> $firstDigit")
        val lastDigit = findLastDigit(row)
        println("Last Digit Found --> $lastDigit")
        return (firstDigit + lastDigit)
    }

    fun part2(input: List<String>): Int {
        var total = 0
        input.forEach { row ->
            val result = translateRow(row)
            println("Translated String = $result")
            println("example = ${getNumber(result)}")
            total += getNumber(result)
        }
        println("TOTAL --> $total")
        return total
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01")
    if (part2(testInput) == 281) println("✅") else println("❌")

    val input = readInput("Day01")
//    part1(input).println()
//    part2(input).println()
}
