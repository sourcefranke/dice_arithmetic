package com.github.sourcefranke.dicearithmetic.core

import kotlin.random.Random

interface Element {
    /**
     * Returns the minimum result possible for this given [Element]
     * @return minimum result
     */
    fun min(): Int

    /**
     * Returns the maximum result possible for this given [Element]
     * @return maximum result
     */
    fun max(): Int

    /**
     * Executes one dice roll
     * @return resulting value for the given dice roll
     */
    fun roll(): Int

    /**
     * Executes the dice roll a several number of times
     * @param rolls number of rolls to be executed
     * @return list of results for the predefined dice roll
     */
    fun resultList(rolls: Int): List<Int> {
        val result = mutableListOf<Int>()
        repeat(rolls) {
            result.add(roll())
        }
        return result
    }
}

/**
 * Represents a constant number value
 * @param number predefined constant value
 */
class Number (private val number: Int): Element {
    override fun min(): Int = number
    override fun max(): Int = number
    override fun roll(): Int = number
}

/**
 * Represents a single dice
 * @param maxInt maximum value for the given dice
 * @param random [Random] object used for generating the result of a dice roll
 */
class Dice (private val maxInt: Int, private val random: Random = Random): Element {
    override fun min(): Int = 1
    override fun max(): Int = maxInt
    override fun roll(): Int = random.nextInt(maxInt) + 1
}

/**
 * Represents the arithmetic operation of summing up several values
 * @param args list of [Element] objects to be summed up
 */
class Addition (private val args : List<Element>): Element {
    override fun min(): Int = calc { it.min() }
    override fun max(): Int = calc { it.max() }
    override fun roll(): Int = calc { it.roll() }

    private fun calc(_m: (Element) -> Int) = args.map { _m(it) }.sum()
}

/**
 * Represents the arithmetic operation of multiplying several values
 * @param args list of [Element] objects to be multiplied
 */
class Multiplication (private val args : List<Element>): Element {
    override fun min(): Int = calc { it.min() }
    override fun max(): Int = calc { it.max() }
    override fun roll(): Int = calc { it.roll() }

    private fun calc(_m: (Element) -> Int) = args.map { _m(it) }.reduce{ a, b -> a * b }
}
