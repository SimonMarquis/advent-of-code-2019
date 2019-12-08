/*
 * Copyright (c) 2019 Simon Marquis
 */

fun <T> List<T>.permutations(): Set<List<T>> =
    if (this.size <= 1) listOf(this)
    else {
        val elementToInsert = first()
        drop(1).permutations().flatMap { permutation ->
            (0..permutation.size).map { e ->
                permutation.toMutableList().apply { add(e, elementToInsert) }
            }
        }
    }.toSet()
