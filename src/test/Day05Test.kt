/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 05")
internal class Day05Test {

    val actualInput = Resources.resourceAsString("Day05.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day05("3,0,4,0,99")
            // Then
            assertThat(day.part1(userInput = 1)).isEqualTo(1)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day05(actualInput)
            // Then
            assertThat(day.part1(userInput = 1)).isEqualTo(16348437)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches examples`() {
            // When
            val day = Day05("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99")
            // Then
            assertThat(day.part2(userInput = 7 /*<8*/)).isEqualTo(999)
            assertThat(day.part2(userInput = 8 /*=8*/)).isEqualTo(1000)
            assertThat(day.part2(userInput = 9 /*>8*/)).isEqualTo(1001)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day05(actualInput)
            // Then
            assertThat(day.part2(userInput = 5)).isEqualTo(6959377)
        }
    }

}