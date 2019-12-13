/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day09(input: String) {

    private val program: List<Long> = input.split(",").map { it.toLong() }

    fun part1(input: Int) = Wrapper(0L).apply {
        IntcodeComputer(program).run(input = { input.toLong() }, output = { value = it })
    }.value

    fun part2(input: Int) = Wrapper(0L).apply {
        IntcodeComputer(program).run(input = { input.toLong() }, output = { value = it })
    }.value
}
