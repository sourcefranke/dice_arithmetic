package com.github.sourcefranke.dicearithmetic.core

import kotlin.random.Random

/**
 * Random object to be used for [Dice] objects
 */
var random: Random = Random

/**
 * Exception type to be thrown, if something's wrong with the string formula
 * @param message text to be shown for the exception thrown
 */
class ConverterException(message: String) : Exception(message)

typealias Converter = (String) -> Element

/**
 * [Converter] splitting up a given formula and converting its different parts into [Element] objects
 * @throws ConverterException
 * @throws IllegalArgumentException
 */
val toElement: Converter = {
	val string = shorten(it)
	when {
		isAddition(string) -> toAddition(string)
		isMultiplication(string) -> toMultiplication(string)
		isMultipleDices(string) -> toMultipleDices(string)
		isDice(string) -> toDice(string)
		isNumber(string) -> toNumber(string)
		else -> throw ConverterException("Couldn't determine the element type for the given formula '$it'")
	}
}

/**
 * Converting a given formula into an [Addition] object
 * @throws IllegalArgumentException
 */
private val toAddition: Converter = {
	require(isAddition(it)) { "'$it' is not an Addition! Example: d6 + 2" }
	val list = it.split("+").map(toElement)
    Addition(list)
}

/**
 * Converting a given formula into an [Multiplication] object
 * @throws IllegalArgumentException
 */
private val toMultiplication: Converter = {
	require(isMultiplication(it)) { "'$it' is not a Multiplication! Example: d6 * 2" }
	val list = it.split("*").map(toElement)
	Multiplication(list)
}

/**
 * Converting a given formula into an [Addition] object holding multiple [Dice] objects
 * @throws IllegalArgumentException
 */
private val toMultipleDices: Converter = {
	require(isMultipleDices(it)) { "'$it' are not multiple dices! Example: 2d6" }

	val split = it.split("d")

	val amount = split[0].toInt()
	val maxInt = split[1].toInt()

	val list = mutableListOf<Dice>()
	repeat(amount) { list.add(Dice(maxInt, random)) }

	Addition(list)
}

/**
 * Converting a given formula into an [Dice] object
 * @throws IllegalArgumentException
 */
private val toDice: Converter = {
	require(isDice(it)) { "'$it' is not a single dice! Example: d6" }
	val maxInt = it.substring(1).toInt()
	Dice(maxInt, random)
}

/**
 * Converting a given formula into an [Number] object
 * @throws IllegalArgumentException
 */
private val toNumber: Converter = {
	require(isNumber(it)) { "'$it' is not a single number! Example: 42" }
	Number(it.toInt())
}
