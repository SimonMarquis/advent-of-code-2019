import kotlin.math.sign

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day13(input: String) {

    private val input: List<Long> = input.split(",").map { it.toLong() }

    fun part1(): Int = mutableListOf<Long>().apply {
        IntcodeComputer(input).run(output = { add(it) })
    }.chunked(3).count { it.last() == 2L }

    fun part2(): Long {
        var (ball, paddle, score) = listOf(0L, 0L, 0L)
        val buffer = mutableListOf<Long>()
        IntcodeComputer(
            rom = input.toMutableList().apply { set(0, 2L) }.toList()
        ).run(
            input = { (ball - paddle).sign.toLong() },
            output = {
                buffer.add(it)
                val (x, y, id) = if (buffer.size == 3) buffer else return@run
                when {
                    x == -1L && y == 0L -> score = id
                    id == 3L -> paddle = x
                    id == 4L -> ball = x
                }
                buffer.clear()
            })
        return score
    }

}
