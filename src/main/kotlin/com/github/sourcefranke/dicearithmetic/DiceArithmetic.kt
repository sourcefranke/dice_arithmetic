package com.github.sourcefranke.dicearithmetic

import com.github.sourcefranke.dicearithmetic.elements.*


fun rollDiceResult(formula: String): Int = getElement(shorten(formula)).getResult()

fun shorten(string: String): String = string.replace("\\s".toRegex(), "")

fun getElement(string: String): Element =
	when {
		isAddition(string)			-> createAddition(string)
		isMultiplication(string)	-> createMultiplication(string)
		isMultipleDices(string)		-> getMultipleDices(string)
		isSingleDice(string)		-> Dice(string.substring(1))
		isSingleNumber(string)		-> Number(string)
		else						-> throw Exception()
	}


fun isAddition(string: String): Boolean = string.matches(Regex(".*\\+.*"))

fun createAddition(string: String): Element {
	val list = string.split("+")
	return Addition(getElement(list[0]), getElement(list[1]))
}


fun isMultiplication(string: String): Boolean = string.matches(Regex(".*\\*.*"))

fun createMultiplication(string: String): Element {
	val list = string.split("*")
	return Multiplication(getElement(list[0]), getElement(list[1]))
}


fun isMultipleDices(string: String): Boolean = string.matches(Regex("[0-9]+[d][0-9]+"))

fun getMultipleDices(string: String): Element {
	val list = string.split("d")

	val repeats = list[0].toInt() - 1
	val diceMax = list[1].toInt()

	var element = Addition(Number(0), Dice(diceMax))
	repeat(repeats) {
		element = Addition(element, Dice(diceMax))
	}
	return element
}


fun isSingleDice(string: String): Boolean = string.matches(Regex("[d][0-9]+"))

fun isSingleNumber(string: String): Boolean = string.matches(Regex("[0-9]+"))
