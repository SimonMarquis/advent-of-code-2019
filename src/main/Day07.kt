import Day07.IntcodeComputer.Mode.Immediate
import Day07.IntcodeComputer.Mode.Position
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day07(input: String) {

    private val program: List<Int> = input.split(",").map { it.toInt() }

    fun part1() = (0..4).toList().permutations()
        .map { it.fold(0) { acc: Int, i: Int -> IntcodeComputer(program).diagnostic(phase = i, input = acc).first } to it }
        .maxBy { it.first }
        ?.let { println(it); it.first }

    private class IntcodeComputer(val rom: List<Int>) {

        private sealed class Mode {
            object Position : Mode()
            object Immediate : Mode()
        }

        fun diagnostic(phase: Int, input: Int): Pair<Int, Boolean> {
            val signals = mutableListOf(phase, input)
            val ram = rom.toMutableList()
            var index = 0

            parsing@ while (index < ram.size) {
                val instruction = ram[index]

                fun mode(offset: Int): Mode = when (when (offset) {
                    1 -> (instruction / 100)
                    2 -> (instruction / 1000)
                    3 -> (instruction / 10000)
                    else -> throw UnsupportedOperationException()
                } % 10) {
                    0 -> Position
                    1 -> Immediate
                    else -> throw UnsupportedOperationException()
                }

                fun read(offset: Int) = when (mode(offset)) {
                    Position -> ram[ram[index + offset]]
                    Immediate -> ram[index + offset]
                }

                fun write(offset: Int, value: Int) = when (mode(offset)) {
                    Position -> ram[ram[index + offset]] = value
                    Immediate -> throw UnsupportedOperationException()
                }

                when (val opcode = instruction % 100) {
                    1 -> write(3, read(1) + read(2)).also { index += 4 }
                    2 -> write(3, read(1) * read(2)).also { index += 4 }
                    3 -> write(1, signals.removeAt(0)).also { index += 2 }
                    4 -> signals.add(read(1)).also { index += 2 }
                    5 -> if (read(1) != 0) index = read(2) else index += 3
                    6 -> if (read(1) == 0) index = read(2) else index += 3
                    7 -> write(3, if (read(1) < read(2)) 1 else 0).also { index += 4 }
                    8 -> write(3, if (read(1) == read(2)) 1 else 0).also { index += 4 }
                    99 -> return (signals.last() to true)
                    else -> throw UnsupportedOperationException(opcode.toString())
                }
            }
            return signals.last() to false
        }
    }

    fun part2() = (5..9).toList().permutations()
        .mapNotNull {
            val channels = it.map { LinkedBlockingQueue<Int>().apply { add(it) } }.apply { first().add(0) }
            for (i in 0 until it.lastIndex) {
                thread {
                    BlockingIntcodeComputer(program).diagnostic(
                        input = { channels[i].take() },
                        output = { value -> channels[i + 1].put(value) }
                    )
                }
            }
            var output: Pair<Int, List<Int>>? = null
            BlockingIntcodeComputer(program).diagnostic(
                input = { channels.last().take() },
                output = { value ->
                    output = value to it
                    channels.first().put(value)
                }
            )
            output
        }.maxBy { it.first }
        ?.let { println(it); it.first }

    private class BlockingIntcodeComputer(val rom: List<Int>) {

        private sealed class Mode {
            object Position : Mode()
            object Immediate : Mode()
        }

        fun diagnostic(input: () -> Int, output: (Int) -> Unit) {
            val ram = rom.toMutableList()
            var index = 0

            parsing@ while (index < ram.size) {
                val instruction = ram[index]

                fun mode(offset: Int): Mode = when (when (offset) {
                    1 -> (instruction / 100)
                    2 -> (instruction / 1000)
                    3 -> (instruction / 10000)
                    else -> throw UnsupportedOperationException()
                } % 10) {
                    0 -> Mode.Position
                    1 -> Mode.Immediate
                    else -> throw UnsupportedOperationException()
                }

                fun read(offset: Int) = when (mode(offset)) {
                    Mode.Position -> ram[ram[index + offset]]
                    Mode.Immediate -> ram[index + offset]
                }

                fun write(offset: Int, value: Int) = when (mode(offset)) {
                    Mode.Position -> ram[ram[index + offset]] = value
                    Mode.Immediate -> throw UnsupportedOperationException()
                }

                when (val opcode = instruction % 100) {
                    1 -> write(3, read(1) + read(2)).also { index += 4 }
                    2 -> write(3, read(1) * read(2)).also { index += 4 }
                    3 -> write(1, input()).also { index += 2 }
                    4 -> output(read(1)).also { index += 2 }
                    5 -> if (read(1) != 0) index = read(2) else index += 3
                    6 -> if (read(1) == 0) index = read(2) else index += 3
                    7 -> write(3, if (read(1) < read(2)) 1 else 0).also { index += 4 }
                    8 -> write(3, if (read(1) == read(2)) 1 else 0).also { index += 4 }
                    99 -> return
                    else -> throw UnsupportedOperationException(opcode.toString())
                }
            }
        }
    }

}
