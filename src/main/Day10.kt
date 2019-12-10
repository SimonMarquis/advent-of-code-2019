import java.lang.Math.abs
import java.lang.Math.toDegrees
import kotlin.math.absoluteValue
import kotlin.math.atan2

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day10(input: List<String>) {

    private val asteroids: List<Pair<Int, Int>> = input.mapIndexed { y, s -> s.mapIndexedNotNull { x, c -> if (c == '#') x to y else null } }.flatten()

    fun part1(): Pair<Pair<Int, Int>, Int> = asteroids
        .map { asteroid -> asteroid to asteroid.targets() }
        .maxBy { it.second }!!

    private fun Pair<Int, Int>.targets(): Int = (asteroids - this).map { (it - this).reduce() }.distinct().count()

    private fun Pair<Int, Int>.reduce() = gcd().let { copy(first / it, second / it) }

    private fun Pair<Int, Int>.gcd(): Int {
        var n1 = first.absoluteValue
        var n2 = second.absoluteValue
        if (n1 == 0) return n2.takeUnless { it == 0 } ?: 1
        if (n2 == 0) return n1.takeUnless { it == 0 } ?: 1
        while (n1 != n2) {
            if (n1 > n2) n1 -= n2 else n2 -= n1
        }
        return n1
    }

    private operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>) = copy(first - other.first, second - other.second)

    fun part2(index: Int) = targetingOrder(asteroids.maxBy { it.targets() }!!)[index - 1].run { (first * 100) + second }

    private fun targetingOrder(asteroid: Pair<Int, Int>): List<Pair<Int, Int>> =
        (asteroids - asteroid)
            .groupBy { asteroid.angleTo(it) }
            .mapValues { it.value.sortedBy { target -> asteroid.distanceTo(target) } }
            .toSortedMap().values
            .flatMap { it.withIndex() }.sortedBy { it.index }.map { it.value }


    private fun Pair<Int, Int>.distanceTo(other: Pair<Int, Int>): Int = abs(first - other.first) + abs(second - other.second)

    private fun Pair<Int, Int>.angleTo(other: Pair<Int, Int>): Double {
        val d = toDegrees(atan2((other.second - second).toDouble(), (other.first - first).toDouble())) + 90
        return if (d < 0) d + 360 else d
    }
}
