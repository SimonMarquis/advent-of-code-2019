/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day08(input: String) {

    private val input: Sequence<Int> = input.map { it.toString().toInt() }.asSequence()

    fun part1(width: Int, height: Int) = input
        .chunked(width * height)
        .minBy { layer -> layer.count { pixel -> pixel == 0 } }!!
        .let { layer -> layer.count { it == 1 } * layer.count { it == 2 } }

    fun part2(width: Int, height: Int) = input
        .chunked(width * height)
        .reduce { acc: List<Int>, list: List<Int> -> acc.zip(list) { a: Int, b: Int -> if (a != 2) a else b } }
        .map {
            when (it) {
                0 /*black*/ -> "█"
                1 /*white*/ -> "░"
                else /*transparent*/ -> " "
            }
        }
        .chunked(width)
        .joinToString(separator = "\n") { it.joinToString(separator = "") }

}
