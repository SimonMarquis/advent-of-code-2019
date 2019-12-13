/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day05(input: String) {

    private val input = input.split(",").map { it.toLong() }

    fun part1(userInput: Int) = Wrapper(0L).apply {
        IntcodeComputer(input).run(input = { userInput.toLong() }, output = { value = it })
    }.value

    fun part2(userInput: Int) = Wrapper(0L).apply {
        IntcodeComputer(input).run(input = { userInput.toLong() }, output = { value = it })
    }.value

}


