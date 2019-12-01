/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day01(input: List<String>) {

    private val input: List<Int> = input.map { it.toInt() }

    fun part1() = input.sumBy { it / 3 - 2 }

    fun part2(): Int = input.sumBy {
        val initialFuel = it / 3 - 2
        var totalFuel = initialFuel

        var accumulator = initialFuel
        while (true) {
            val extraFuel = accumulator / 3 - 2
            if (extraFuel <= 0) break
            totalFuel += extraFuel
            accumulator = extraFuel
        }
        totalFuel
    }

}

