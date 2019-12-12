/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 12")
internal class Day12Test {

    // Given
    val actualInput = Resources.resourceAsList("Day12.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // When
            val day = Day12(
                """
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1(times = 10)).isEqualTo(179)
        }

        @Test
        fun `Matches example 2`() {
            // When
            val day = Day12(
                """
                <x=-8, y=-10, z=0>
                <x=5, y=5, z=10>
                <x=2, y=-7, z=3>
                <x=9, y=-8, z=-3>
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1(times = 100)).isEqualTo(1940)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day12(actualInput)
            // Then
            assertThat(day.part1(times = 1000)).isEqualTo(6220)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example 1`() {
            // When
            val day = Day12(
                """
                <x=-1, y=0, z=2>
                <x=2, y=-10, z=-7>
                <x=4, y=-8, z=8>
                <x=3, y=5, z=-1>
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part2()).isEqualTo(2772)
        }

        @Test
        fun `Matches example 2`() {
            // When
            val day = Day12(
                """
                <x=-8, y=-10, z=0>
                <x=5, y=5, z=10>
                <x=2, y=-7, z=3>
                <x=9, y=-8, z=-3>
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part2()).isEqualTo(4686774924)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day12(actualInput)
            // Then
            assertThat(day.part2()).isEqualTo(548525804273976)
        }
    }

}