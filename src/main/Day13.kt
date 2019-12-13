import Day13.BlockingIntcodeComputer.Mode.*
import kotlin.math.sign

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day13(input: String) {

    private val input: List<Long> = input.split(",").map { it.toLong() }

    fun part1(): Int {
        val outputs = mutableListOf<Long>()
        BlockingIntcodeComputer(input).diagnostic({ throw Exception() }, { outputs.add(it) })
        return outputs.chunked(3).count { it.last() == 2L }
    }

    fun part2(): Long {
        var (ball, paddle, score) = listOf(0L, 0L, 0L)
        val buffer = mutableListOf<Long>()
        BlockingIntcodeComputer(
            rom = input.toMutableList().apply { set(0, 2L) }.toList()
        ).diagnostic(
            input = { (ball - paddle).sign.toLong() },
            output = {
                buffer.add(it)
                val (x, y, id) = if (buffer.size == 3) buffer else return@diagnostic
                when {
                    x == -1L && y == 0L -> score = id
                    id == 3L -> paddle = x
                    id == 4L -> ball = x
                }
                buffer.clear()
            })
        return score
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
