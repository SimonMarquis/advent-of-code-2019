import kotlin.math.absoluteValue

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day03(input: List<String>) {

    private val wires = input.map {
        it.split(",")
            .fold(listOf()) { acc: List<Point>, string: String ->
                acc + acc.lastOrNull().extend(string)
            }
    }

    fun part1() = intersections()
        .map { it.x.absoluteValue + it.y.absoluteValue } // By distance
        .min()

    fun part2() = intersections()
        .map { intersection -> wires.map { it.indexOf(intersection) + 1 }.sum() } // By length
        .min()

    private fun intersections() = wires.map { it.toSet() }
        .reduce { acc, it -> acc.intersect(it) }

    data class Point(val x: Int, val y: Int)

    companion object {
        val pattern = """([LURD])(\d+)""".toRegex()
    }

    private fun Point?.extend(input: String): List<Point> {
        val (direction, length) = (pattern.find(input) ?: throw IllegalArgumentException("Cannot parse $input")).destructured
        var x = this?.x ?: 0
        var y = this?.y ?: 0
        val init: (index: Int) -> Point = when (direction) {
            "L" -> { _ -> Point(--x, y) }
            "U" -> { _ -> Point(x, ++y) }
            "R" -> { _ -> Point(++x, y) }
            "D" -> { _ -> Point(x, --y) }
            else -> throw IllegalArgumentException("Unknown direction $direction")
        }
        return List(length.toInt(), init)
    }

}

