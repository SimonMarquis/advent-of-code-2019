/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 06")
internal class Day06Test {

    val actualInput = Resources.resourceAsList("Day06.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day06(listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L"))
            // Then
            assertThat(day.part1()).isEqualTo(42)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day06(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo(251208)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day06(listOf("COM)B", "B)C", "C)D", "D)E", "E)F", "B)G", "G)H", "D)I", "E)J", "J)K", "K)L", "K)YOU", "I)SAN"))
            // Then
            assertThat(day.part2("YOU", "SAN")).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day06(actualInput)
            // Then
            assertThat(day.part2("YOU", "SAN")).isEqualTo(397)
        }
    }

}