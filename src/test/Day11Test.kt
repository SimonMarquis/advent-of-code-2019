/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 11")
internal class Day11Test {

    // Given
    val actualInput = Resources.resourceAsString("Day11.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day11(actualInput)
            // Then
            assertThat(day.part1(color = 0 /*black*/)).isEqualTo(2082)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Actual answer`() {
            // When
            val day = Day11(actualInput)
            // Then
            assertThat(day.part2(color = 1 /*white*/)).isEqualTo(
                """
                | ████  ██  ███  ███   ██  ████   ██ █  █   
                | █    █  █ █  █ █  █ █  █ █       █ █ █    
                | ███  █  █ █  █ ███  █    ███     █ ██     
                | █    ████ ███  █  █ █    █       █ █ █    
                | █    █  █ █ █  █  █ █  █ █    █  █ █ █    
                | █    █  █ █  █ ███   ██  █     ██  █  █   
            """.trimMargin()
            )
        }
    }

}