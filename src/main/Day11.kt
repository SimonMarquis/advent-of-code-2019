import Day11.BlockingIntcodeComputer.Mode.*

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
        return BlockingIntcodeComputer(program).diagnostic(input, output).let { tiles }
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

    private class BlockingIntcodeComputer(val rom: List<Long>) {

        private sealed class Mode {
            object Position : Mode()
            object Immediate : Mode()
            object Relative : Mode()
        }

        fun diagnostic(input: () -> Long, output: (Long) -> Unit) {
            val ram = LongArray(10000) {
                rom.getOrElse(it) { 0L }
            }
            var index = 0
            var base = 0

            parsing@ while (index < ram.size) {
                val instruction = ram[index]

                fun mode(offset: Int): Mode = when (when (offset) {
                    1 -> (instruction / 100)
                    2 -> (instruction / 1000)
                    3 -> (instruction / 10000)
                    else -> throw UnsupportedOperationException()
                } % 10) {
                    0L -> Position
                    1L -> Immediate
                    2L -> Relative
                    else -> throw UnsupportedOperationException()
                }

                fun read(offset: Int) = when (mode(offset)) {
                    Position -> ram[ram[index + offset].toInt()]
                    Immediate -> ram[index + offset]
                    Relative -> ram[ram[index + offset].toInt() + base]
                }

                fun write(offset: Int, value: Long) = when (mode(offset)) {
                    Position -> ram[ram[index + offset].toInt()] = value
                    Immediate -> throw UnsupportedOperationException()
                    Relative -> ram[ram[index + offset].toInt() + base] = value
                }

                when (val opcode = (instruction % 100).toInt()) {
                    1 -> write(3, read(1) + read(2)).also { index += 4 }
                    2 -> write(3, read(1) * read(2)).also { index += 4 }
                    3 -> write(1, input()).also { index += 2 }
                    4 -> output(read(1)).also { index += 2 }
                    5 -> if (read(1) != 0L) index = read(2).toInt() else index += 3
                    6 -> if (read(1) == 0L) index = read(2).toInt() else index += 3
                    7 -> write(3, if (read(1) < read(2)) 1 else 0).also { index += 4 }
                    8 -> write(3, if (read(1) == read(2)) 1 else 0).also { index += 4 }
                    9 -> base += read(1).also { index += 2 }.toInt()
                    99 -> return
                    else -> throw UnsupportedOperationException(opcode.toString())
                }
            }
        }
    }
}

