/*
 * Copyright (c) 2019 Simon Marquis
 */


class Day05(input: String) {

    private val input = input.split(",").map { it.toInt() }

    fun part1(userInput: Int): Int {
        var output = 0
        val program = input.toMutableList()
        var index = 0
        parsing@ while (index < program.size) {
            val raw = program[index++]
            val first = Mode.parse((raw / 100) % 10)
            val second = Mode.parse((raw / 1000) % 10)
            val third = Mode.parse((raw / 10000) % 10)
            when (val opcode = raw % 100) {
                1 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, left + right)
                }
                2 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, left * right)
                }
                3 -> {
                    first.set(program, index++, userInput)
                }
                4 -> {
                    output = first.get(program, index++)
                }
                99 -> break@parsing
                else -> throw UnsupportedOperationException(opcode.toString())
            }
        }
        return output
    }

    fun part2(userInput: Int): Int {
        var output = 0
        val program = input.toMutableList()
        var index = 0
        parsing@ while (index < program.size) {
            val raw = program[index++]
            val first = Mode.parse((raw / 100) % 10)
            val second = Mode.parse((raw / 1000) % 10)
            val third = Mode.parse((raw / 10000) % 10)
            when (val opcode = raw % 100) {
                1 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, left + right)
                }
                2 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, left * right)
                }
                3 -> {
                    first.set(program, index++, userInput)
                }
                4 -> {
                    output = first.get(program, index++)
                }
                5 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    if (left != 0) index = right
                }
                6 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    if (left == 0) index = right
                }
                7 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, if (left < right) 1 else 0)
                }
                8 -> {
                    val left = first.get(program, index++)
                    val right = second.get(program, index++)
                    third.set(program, index++, if (left == right) 1 else 0)
                }
                99 -> break@parsing
                else -> throw UnsupportedOperationException(opcode.toString())
            }
        }
        return output
    }

    private sealed class Mode {

        companion object {
            fun parse(int: Int): Mode = when (int) {
                0 -> Position
                1 -> Immediate
                else -> throw UnsupportedOperationException()
            }
        }

        abstract fun get(program: List<Int>, index: Int): Int
        abstract fun set(program: MutableList<Int>, index: Int, value: Int)

        object Position : Mode() {
            override fun get(program: List<Int>, index: Int): Int = program[program[index]]
            override fun set(program: MutableList<Int>, index: Int, value: Int) {
                program[program[index]] = value
            }
        }

        object Immediate : Mode() {
            override fun get(program: List<Int>, index: Int): Int = program[index]
            override fun set(program: MutableList<Int>, index: Int, value: Int) = throw UnsupportedOperationException()
        }

    }

}


