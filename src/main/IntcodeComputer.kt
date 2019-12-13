import IntcodeComputer.Mode.*

/*
 * Copyright (c) 2019 Simon Marquis
 */

class IntcodeComputer(private val rom: List<Long>) {

    private sealed class Mode {
        object Position : Mode()
        object Immediate : Mode()
        object Relative : Mode()
    }

    fun run(
        input: () -> Long = { TODO() },
        output: (Long) -> Unit
    ) {
        // Allocate more ram?
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
