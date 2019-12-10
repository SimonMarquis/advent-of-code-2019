/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 10")
internal class Day10Test {

    // Given
    val actualInput = Resources.resourceAsList("Day10.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() {
            // When
            val day = Day10(
                """
                ......#.#.
                #..#.#....
                ..#######.
                .#.#.###..
                .#..#.....
                ..#....#.#
                #..#....#.
                .##.#..###
                ##...#..#.
                .#....####
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1()).isEqualTo((5 to 8) to 33)
        }

        @Test
        fun `Matches example 2`() {
            // When
            val day = Day10(
                """
                #.#...#.#.
                .###....#.
                .#....#...
                ##.#.#.#.#
                ....#.#.#.
                .##..###.#
                ..#...##..
                ..##....##
                ......#...
                .####.###.
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1()).isEqualTo((1 to 2) to 35)
        }

        @Test
        fun `Matches example 3`() {
            // When
            val day = Day10(
                """
                .#..#..###
                ####.###.#
                ....###.#.
                ..###.##.#
                ##.##.#.#.
                ....###..#
                ..#.#..#.#
                #..#.#.###
                .##...##.#
                .....#.#..
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1()).isEqualTo((6 to 3) to 41)
        }

        @Test
        fun `Matches example 4`() {
            // When
            val day = Day10(
                """
                .#..##.###...#######
                ##.############..##.
                .#.######.########.#
                .###.#######.####.#.
                #####.##.#.##.###.##
                ..#####..#.#########
                ####################
                #.####....###.#.#.##
                ##.#################
                #####.##.###..####..
                ..######..##.#######
                ####.##.####...##..#
                .#####..#.######.###
                ##...#.##########...
                #.##########.#######
                .####.#.###.###.#.##
                ....##.##.###..#####
                .#.#.###########.###
                #.#.#.#####.####.###
                ###.##.####.##.#..##
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part1()).isEqualTo((11 to 13) to 210)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day10(actualInput)
            // Then
            assertThat(day.part1()).isEqualTo((28 to 29) to 340)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() {
            // When
            val day = Day10(
                """
                .#..##.###...#######
                ##.############..##.
                .#.######.########.#
                .###.#######.####.#.
                #####.##.#.##.###.##
                ..#####..#.#########
                ####################
                #.####....###.#.#.##
                ##.#################
                #####.##.###..####..
                ..######..##.#######
                ####.##.####...##..#
                .#####..#.######.###
                ##...#.##########...
                #.##########.#######
                .####.#.###.###.#.##
                ....##.##.###..#####
                .#.#.###########.###
                #.#.#.#####.####.###
                ###.##.####.##.#..##
            """.trimIndent().lines()
            )
            // Then
            assertThat(day.part2(index = 200)).isEqualTo(802)
        }

        @Test
        fun `Actual answer`() {
            // When
            val day = Day10(actualInput)
            // Then
            assertThat(day.part2(index = 200)).isEqualTo(2628)
        }
    }

}