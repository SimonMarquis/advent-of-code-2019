/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 09")
internal class Day09Test {

    // Given
    val actualInput = Resources.resourceAsString("Day09.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // When
            val day = Day09("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99")
            // Then
            assertThat(day.part1(11111)).isEqualTo(99)
        }

        @Test
        fun `Matches example 2`() {
            // When
            val day = Day09("1102,34915192,34915192,7,4,7,99,0")
            // Then
            assertThat(day.part1(1)).isEqualTo(1219070632396864)
        }

        @Test
        fun `Matches example 3`() {
            // When
            val day = Day09("104,1125899906842624,99")
            // Then
            assertThat(day.part1(1)).isEqualTo(1125899906842624)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day09(actualInput)
            // Then
            assertThat(day.part1(1)).isEqualTo(3241900951)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day09(actualInput)
            // Then
            assertThat(day.part2(2)).isEqualTo(83089)
        }
    }

}