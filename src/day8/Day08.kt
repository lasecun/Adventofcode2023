package day8

import lcm
import kotlin.io.path.Path
import kotlin.io.path.readText

fun main() {
    val d = Day08(Path("src/day8/Day08.txt").readText())
    println(d.solvedPart1())
    println(d.solvedPart2())
}

class Day08(input: String) {

    private val instructions = INSTRUCTIONS.matchAt(input, 0)!!.value
    private val table = NODE.findAll(input).associateBy(
        keySelector = { it.groupValues[1] },
        valueTransform = { it.groupValues[2] to it.groupValues[3] }
    )

    fun solvedPart1(): Int {
        val solution = instructions.length * generateSequence("AAA", ::step).indexOf("ZZZ")
        return solution
    }

    fun solvedPart2(): Long {
        return instructions.length *
                table.keys.filter { it.endsWith('A') }.fold(1L) { acc, start ->
                    val (index, end) = generateSequence(start, ::step).withIndex().first { (_, end) -> end.endsWith('Z') }
                    check(step(start) == step(end)) { "required for lcm solution" }
                    lcm(acc, index.toLong())
                }
    }



    private fun step(start: String) = instructions.fold(start) { acc, char ->
        when (char) {
            'L' -> table[acc]!!.first
            'R' -> table[acc]!!.second
            else ->
                @Suppress("ThrowingExceptionsWithoutMessageOrCause", "UseCheckOrError")
                throw IllegalStateException()
        }
    }

    companion object {
        private val INSTRUCTIONS = """[LR]+""".toRegex()
        private val NODE = """(\w+) = \((\w+), (\w+)\)""".toRegex()
    }
}