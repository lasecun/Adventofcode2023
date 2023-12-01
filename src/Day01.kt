fun main() {

    fun getNumber(result: String): Int{
        if (result.length == 1) {
            return Integer.parseInt(result + result)
        } else if (result.length == 2) {
            return Integer.parseInt(result)
        } else {
            val first = result.first().toString()
            val last = result.last().toString()
            return Integer.parseInt(first + last)
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

    fun part2(input: List<String>): Int {
        return input.size
    }



    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
