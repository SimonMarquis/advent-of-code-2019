import Day05.IntcodeComputer.Mode.Immediate
import Day05.IntcodeComputer.Mode.Position

/*
 * Copyright (c) 2019 Simon Marquis
 */


class Day05(input: String) {

    private val input = input.split(",").map { it.toInt() }

    fun part1(userInput: Int): Int = IntcodeComputer(input).diagnostic(userInput)

    fun part2(userInput: Int): Int = IntcodeComputer(input).diagnostic(userInput)

    private class IntcodeComputer(val rom: List<Int>) {

        private sealed class Mode {
            object Position : Mode()
            object Immediate : Mode()
        }

        fun diagnostic(userInput: Int? = null): Int {
            val ram = rom.toMutableList()
            var output = 0
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
                    3 -> write(1, userInput ?: throw UnsupportedOperationException()).also { index += 2 }
                    4 -> output = read(1).also { index += 2 }
                    5 -> if (read(1) != 0) index = read(2) else index += 3
                    6 -> if (read(1) == 0) index = read(2) else index += 3
                    7 -> write(3, if (read(1) < read(2)) 1 else 0).also { index += 4 }
                    8 -> write(3, if (read(1) == read(2)) 1 else 0).also { index += 4 }
                    99 -> break@parsing
                    else -> throw UnsupportedOperationException(opcode.toString())
                }
            }
            return output
        }
    }

}


