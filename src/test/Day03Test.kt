/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 3")
internal class Day03Test {

    // Given
    val sampleInput = listOf<String>("R75,D30,R83,U83,L12,D49,R71,U7,L72" , "U62,R66,U55,R34,D71,R55,D58,R83")
    val actualInput = Resources.resourceAsList("Day03.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day03(sampleInput)
            // Then
            assertThat(day.part1()).isEqualTo(159)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day03(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo(1285)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day03(sampleInput)
            // Then
            assertThat(day.part2()).isEqualTo(610)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day03(actualInput)
            // Then
            assertThat(day.part2()).isEqualTo(14228)
        }
    }

}