/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 13")
internal class Day13Test {

    // Given
    val actualInput = Resources.resourceAsString("Day13.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day13(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo(376)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day13(actualInput)
            // Then
            assertThat(day.part2()).isEqualTo(18509)
        }
    }

}