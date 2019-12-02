/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 2")
internal class Day02Test {

    // Given
    val sampleInput = "1,1,1,4,99,5,6,0,99"
    val actualInput = Resources.resourceAsString("Day02.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day02(sampleInput)
            // Then
            assertThat(day.part1()).isEqualTo(30)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day02(actualInput)
            // Then
            assertThat(day.part1(12, 2)).isEqualTo(7594646)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day02(sampleInput)
            // Then
            assertThat(day.part2(30)).isEqualTo(0)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day02(actualInput)
            // Then
            assertThat(day.part2(19690720)).isEqualTo(3376)
        }
    }

}