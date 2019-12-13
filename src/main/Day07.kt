import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day07(input: String) {

    private val program: List<Long> = input.split(",").map { it.toLong() }

    fun part1() = (0L..4L).toList().permutations()
        .map { permutation ->
            permutation.fold(0L) { acc: Long, i: Long ->
                Wrapper(0L).apply {
                    val inputs = mutableListOf(i /*phase*/, acc /*amplifier*/)
                    IntcodeComputer(program).run(
                        input = { inputs.removeAt(0) },
                        output = { value = it }
                    )
                }.value
            }
        }
        .max()

    fun part2() = (5L..9L).toList().permutations()
        .mapNotNull {
            val channels = it.map { LinkedBlockingQueue<Long>().apply { add(it) } }.apply { first().add(0) }
            for (i in 0 until it.lastIndex) {
                thread {
                    IntcodeComputer(program).run(
                        input = { channels[i].take() },
                        output = { value -> channels[i + 1].put(value) }
                    )
                }
            }
            var output: Pair<Long, List<Long>>? = null
            IntcodeComputer(program).run(
                input = { channels.last().take() },
                output = { value ->
                    output = value to it
                    channels.first().put(value)
                }
            )
            output
        }.maxBy { it.first }
        ?.let { println(it); it.first }

}
