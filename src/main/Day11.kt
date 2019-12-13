/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day11(input: String) {

    private val program: List<Long> = input.split(",").map { it.toLong() }

    fun part1(color: Int): Int = paint(color).size

    fun part2(color: Int) = render(paint(color))

    private operator fun Pair<Int, Int>.plus(direction: Pair<Int, Int>): Pair<Int, Int> = copy(first + direction.first, second + direction.second)

    private fun Pair<Int, Int>.turn(turn: Int): Pair<Int, Int> = when (this) {
        /*UP*/ 0 to 1 -> if (turn == 0) -1 to 0 else 1 to 0
        /*RIGHT*/ 1 to 0 -> if (turn == 0) 0 to 1 else 0 to -1
        /*DOWN*/ 0 to -1 -> if (turn == 0) 1 to 0 else -1 to 0
        /*LEFT*/ -1 to 0 -> if (turn == 0) 0 to -1 else 0 to 1
        else -> throw java.lang.UnsupportedOperationException()
    }

    private fun paint(color: Int): MutableMap<Pair<Int, Int>, Int> {
        val tiles = mutableMapOf<Pair<Int, Int>, Int>()
        var position = 0 to 0
        var direction = 0 to 1
        var shouldPaintNotMove = true

        val input = {
            (tiles[position] ?: color).toLong()
        }
        val output: (Long) -> Unit = {
            if (shouldPaintNotMove) {
                tiles[position] = it.toInt()
            } else {
                direction = direction.turn(it.toInt())
                position += direction
            }
            shouldPaintNotMove = !shouldPaintNotMove
        }
        return IntcodeComputer(program).run(input, output).let { tiles }
    }

    private fun render(paint: MutableMap<Pair<Int, Int>, Int>): Any {
        val values = paint.keys
        val minX = values.minBy { it.first }!!.first
        val maxX = values.maxBy { it.first }!!.first
        val minY = values.minBy { it.second }!!.second
        val maxY = values.maxBy { it.second }!!.second

        return List(maxY - minY + 1) { y ->
            List(maxX - minX + 1) { x ->
                when (paint[x + minX to y + minY]) {
                    1 -> "█"
                    else -> " "
                }
            }.joinToString(separator = "")
        }.reversed().joinToString(separator = "\n")
    }

}

