package com.github.sourcefranke.dicearithmetic.core.elements

interface Element {
    fun getResult(): Int
    fun getMin(): Int
    fun getMax(): Int

    fun getResultList(size: Int): List<Int> {
        val result = mutableListOf<Int>()
        repeat(size) {
            result.add(getResult())
        }
        return result
    }
}
