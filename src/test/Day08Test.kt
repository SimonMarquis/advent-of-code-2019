/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 08")
internal class Day08Test {

    // Given
    val actualInput = Resources.resourceAsString("Day08.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day08("123456789012")
            // Then
            assertThat(day.part1(width = 3, height = 2)).isEqualTo(1)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day08(actualInput)
            // Then
            assertThat(day.part1(width = 25, height = 6)).isEqualTo(2520)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day08("0222112222120000")
            // Then
            assertThat(day.part2(width = 2, height = 2)).isEqualTo(
                """
                █░
                ░█
            """.trimIndent()
            )
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day08(actualInput)
            // Then
            assertThat(day.part2(width = 25, height = 6)).isEqualTo(
                """
                ░████░░░░██░░████░░█░███░
                ░████░████░██░████░█░███░
                ░████░░░██░███████░██░█░█
                ░████░████░█░░████░███░██
                ░████░████░██░█░██░███░██
                ░░░░█░░░░██░░░██░░████░██
            """.trimIndent()
            )
        }
    }

}