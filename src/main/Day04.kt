/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day04(input: String) {

    private val input: IntRange = input.split("-").map { it.toInt() }.let { IntRange(it[0], it[1]) }

    fun part1() = input
        .map { it.toString() }
        .filter {
            with(it.windowedSequence(size = 2, step = 1)) {
                all { s -> s[0] <= s[1] } && any { s -> s[0] == s[1] }
            }
        }
        .count()

    fun part2() = input
        .map { it.toString() }
        .filter {
            with(it.windowedSequence(size = 2, step = 1)) {
                all { s -> s[0] <= s[1] } && any { s -> s[0] == s[1] }
            }
        }
        .filter { password ->
            password.windowedSequence(size = 2, step = 1).filter { s -> s[0] == s[1] }.distinct().map { it[0].toString().repeat(3) }.any { !password.contains(it) }
        }
        .count()


}
