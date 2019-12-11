/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 00")
internal class Day00Test {

    // Given
    val sampleInput = listOf<String>()
    val actualInput = Resources.resourceAsList("Day00.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day00(sampleInput)
            // Then
            // assertThat(day.part1()).isEqualTo()
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day00(actualInput)
            // Then
            // assertThat(day.part1()).isEqualTo()
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day00(sampleInput)
            // Then
            // assertThat(day.part2()).isEqualTo()
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day00(actualInput)
            // Then
            // assertThat(day.part2()).isEqualTo()
        }
    }

}