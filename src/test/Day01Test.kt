/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 1")
internal class Day01Test {

    // Given
    val sampleInput = listOf("12", "14", "1969", "100756")
    val actualInput = Resources.resourceAsList("Day01.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day01(sampleInput)
            // Then
            assertThat(day.part1()).isEqualTo(34241)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day01(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo(3474920)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day01(sampleInput)
            // Then
            assertThat(day.part2()).isEqualTo(2 + 2 + 966 + 50346)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day01(actualInput)
            // Then
            assertThat(day.part2()).isEqualTo(5209504)
        }
    }

}