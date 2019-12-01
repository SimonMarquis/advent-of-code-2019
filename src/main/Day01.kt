/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day01(input: List<String>) {

    private val input: List<Int> = input.map { it.toInt() }

    fun part1() = input.sumBy { it / 3 - 2 }

    fun part2(): Int = input.sumBy {
        var total = 0
        var current = it / 3 - 2
        while (current > 0) {
            total += current
            current = current / 3 - 2
        }
        return@sumBy total
    }

}

