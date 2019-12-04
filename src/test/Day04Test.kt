/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 4")
internal class Day04Test {

    // Given
    val actualInput = Resources.resourceAsString("Day04.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day04(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo(2779)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day04(actualInput)
            // Then
            assertThat(day.part2()).isEqualTo(1972)
        }
    }

}