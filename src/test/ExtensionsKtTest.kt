/*
 * Copyright (c) 2019 Simon Marquis
 */

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test

internal class ExtensionsKtTest {

    @Test
    fun permutations() {
        val input = listOf(0, 1, 2)
        val permutation = listOf(listOf(0, 1, 2), listOf(1, 0, 2), listOf(1, 2, 0), listOf(0, 2, 1), listOf(2, 0, 1), listOf(2, 1, 0))
        assertThat(input.permutations()).containsAll(permutation)
    }

}