import kotlin.math.absoluteValue
import kotlin.math.sign

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day12(input: List<String>) {

    private val input: List<Moon> = input.map { parse(it) }

    fun part1(times: Int): Int = step(input).drop(times - 1).first().sumBy { it.position.absoluteSum() * it.velocity.absoluteSum() }

    fun part2(): Long {
        val seenX = mutableSetOf<List<Pair<Int, Int>>>()
        val seenY = mutableSetOf<List<Pair<Int, Int>>>()
        val seenZ = mutableSetOf<List<Pair<Int, Int>>>()
        for (state in step(input)) {
            val axisX = state.map { it.position.first to it.velocity.first }
            val axisY = state.map { it.position.second to it.velocity.second }
            val axisZ = state.map { it.position.third to it.velocity.third }
            if (!seenX.add(axisX) && !seenY.add(axisY) && !seenZ.add(axisZ)) break
        }
        return lcm(seenX.size.toLong(), lcm(seenY.size.toLong(), seenZ.size.toLong()))
    }

    private fun step(moons: List<Moon>): Sequence<List<Moon>> = sequence {
        while (true) {
            val nextState = moons
                // compute new velocity
                .map {
                    it to (moons - it).fold(Triple(0, 0, 0)) { acc, moon ->
                        acc + Triple(
                            (moon.position.first - it.position.first).sign,
                            (moon.position.second - it.position.second).sign,
                            (moon.position.third - it.position.third).sign
                        )
                    }
                }
                // apply computed velocity
                .onEach { it.first.velocity += it.second }.map { it.first }
                // apply new position
                .onEach { it.position += it.velocity }
            yield(nextState)
        }
    }

    data class Moon(var position: Triple<Int, Int, Int>, var velocity: Triple<Int, Int, Int> = Triple(0, 0, 0))

    companion object {

        private val pattern = """<x=(-?\d+), y=(-?\d+), z=(-?\d+)>""".toRegex()

        private fun parse(input: String): Moon {
            val (x, y, z) = (pattern.find(input) ?: throw IllegalArgumentException("Cannot parse $input")).destructured
            return Moon(Triple(x.toInt(), y.toInt(), z.toInt()))
        }

        private fun lcm(x: Long, y: Long): Long {
            var a = x
            var b = y
            while (a != 0L) {
                a = (b % a).also { b = a }
            }
            return x / b * y
        }
    }

    private fun Triple<Int, Int, Int>.absoluteSum() = listOf(first, second, third).sumBy { it.absoluteValue }

    private operator fun Triple<Int, Int, Int>.plus(triple: Triple<Int, Int, Int>) = copy(first + triple.first, second + triple.second, third + triple.third)

}


