/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day02(input: String) {

    private val input: List<Int> = input.split(",").map { it.toInt() }

    fun part1(noun: Int? = null, verb: Int? = null): Int {
        return input.toMutableList().apply {
            noun?.let { this[1] = it }
            verb?.let { this[2] = it }
            var index = 0
            parsing@ while (index < size) {
                when (val opcode = this[index]) {
                    1 -> {
                        this[this[index + 3]] = this[this[index + 1]] + this[this[index + 2]]
                        index += 4
                    }
                    2 -> {
                        this[this[index + 3]] = this[this[index + 1]] * this[this[index + 2]]
                        index += 4
                    }
                    99 -> break@parsing
                    else -> throw UnsupportedOperationException(opcode.toString())
                }
            }
        }[0]
    }

    fun part2(output: Int): Int {
        for (noun in 0..99) {
            for (verb in 0..99) {
                if (part1(noun, verb) == output) {
                    return 100 * noun + verb
                }
            }
        }
        throw IllegalArgumentException()
    }

}

