package com.github.sourcefranke.dicearithmetic.core

/**
 * Data object holding the result of a dice roll based on a given formula
 * @param formula string describing dice and arithmetic operations to be used
 * @param min minimum possible result for the given formula
 * @param max maximum possible result for the given formula
 * @param resultList list of results for the given formula
 */
data class Result(val formula: String, val min: Int, val max: Int, val resultList: List<Int>)

/**
 * Executes dice rolls based on the given formula
 * @param formula string
 * @param times number of rolls to be executed
 * @return object holding the results of the dice rolls defined by the given formula
 * @throws ConverterException
 * @throws IllegalArgumentException
 */
fun rollDice(formula: String, times: Int = 1): Result {
    val e = toElement(formula)
    return Result(formula, e.min(), e.max(), e.resultList(times))
}

/**
 * Shortens a given string
 * @param s string to be shortened
 * @return shortened string
 */
fun shorten(s: String): String {
    val regex = Regex("\\s")
    return if (s.contains(regex)) s.replace(regex, "")
        else s
}
