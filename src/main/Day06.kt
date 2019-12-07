/*
 * Copyright (c) 2019 Simon Marquis
 */

class Day06(input: List<String>) {

    private val graph = Graph(input)

    fun part1() = graph.totalNumberOfOrbits()

    fun part2(src: String, dst: String) = graph.numberOfOrbitalTransfers(src, dst)

    private class Graph(input: List<String>) {

        companion object {
            private val pattern = """(\w+)\)(\w+)""".toRegex()
        }

        val orbitedBy: Map<String, Set<String>>
        val adjacent: Map<String, Set<String>>

        init {
            val orbitedBy = mutableMapOf<String, MutableSet<String>>()
            val adjacent = mutableMapOf<String, MutableSet<String>>()
            input.forEach {
                val (parent, satellite) = pattern.find(it)!!.destructured
                orbitedBy.getOrPut(parent) { mutableSetOf() } += satellite
                adjacent.getOrPut(parent) { mutableSetOf() } += satellite
                adjacent.getOrPut(satellite) { mutableSetOf() } += parent
            }
            this.orbitedBy = orbitedBy
            this.adjacent = adjacent
        }

        fun totalNumberOfOrbits(): Int = orbitedBy.keys.sumBy { numberOfOrbitsOf(it) }

        fun numberOfOrbitsOf(name: String): Int = orbitedBy[name]?.sumBy { 1 + numberOfOrbitsOf(it) } ?: 0

        fun numberOfOrbitalTransfers(src: String, dst: String): Int = breadthFirstSearch(src, dst, mutableMapOf(), mutableListOf(src), mutableListOf()).minBy { it.size }!!.size - 3

        fun breadthFirstSearch(
            src: String,
            dst: String,
            visited: MutableMap<String, Boolean>,
            path: MutableList<String>,
            results: MutableList<List<String>>
        ): List<List<String>> {
            if (src == dst) {
                results.add(path.toList())
                return results
            }
            visited[src] = true
            adjacent[src]?.forEach {
                if (visited[it] == true) return@forEach
                path.add(it)
                breadthFirstSearch(it, dst, visited, path, results)
                path.remove(it)
            }
            visited[src] = false
            return results
        }

    }

}
