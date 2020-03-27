package com.github.sourcefranke.rpgdicearith

import kotlin.random.Random

fun rollDiceResult(formula: String): Int {
	return getElement(shorten(formula)).getResult()
}

fun shorten(string: String): String {
	return string.replace("\\s".toRegex(), "")
}

fun getElement(string: String): Element {
	var element: Element
	when {
		isAddition(string) 		-> element = createAddition(string)
		isSingleDice(string)	-> element = Dice(string.substring(1))
		isSingleNumber(string)	-> element = Number(string)
		isMultipleDices(string)	-> element = getMultipleDices(string)
		else					-> throw Exception()
	}
	return element
}

fun isAddition(string: String): Boolean {
	return string.matches(Regex(".*\\+.*"))
}

fun createAddition(string: String): Element {
	var list = string.split("+")
	return Addition(getElement(list.get(0)), getElement(list.get(1)))
}

fun isSingleDice(string: String): Boolean {
	return string.matches(Regex("[d][0-9]+"))
}

fun isSingleNumber(string: String): Boolean {
	return string.matches(Regex("[0-9]+"))
}

fun isMultipleDices(string: String): Boolean {
	return string.matches(Regex("[0-9]+[d][0-9]+"))
}

fun getMultipleDices(string: String): Element {
	var list = string.split("d")

	var repeats = list.get(0).toInt() - 1
	var diceMax = list.get(1).toInt()

	var element = Addition(Number(0), Dice(diceMax))
	repeat(repeats) {
		element = Addition(element, Dice(diceMax))
	}
	return element
}

interface Element {
	fun getResult(): Int
	fun getMin(): Int
	fun getMax(): Int
}

class Number (val number: Int): Element {
	
	constructor (numberString: String) : this(numberString.toInt())
	
	override fun getResult(): Int {
		return number
	}
	
	override fun getMin(): Int {
		return number
	}
	
	override fun getMax(): Int {
		return number
	}
}

class Dice (val number: Int): Element {

	constructor (maxString: String) : this(maxString.toInt())
	
	override fun getResult(): Int {
		return Random.nextInt(number) + 1
	}
	
	override fun getMin(): Int {
		return 1
	}
	
	override fun getMax(): Int {
		return number
	}
}

class Addition (val first: Element, val second: Element): Element {
	
	override fun getResult(): Int {
		return first.getResult() + second.getResult()
	}
	
	override fun getMin(): Int {
		return first.getMin() + second.getMin()
	}
	
	override fun getMax(): Int {
		return first.getMax() + second.getMax()
	}
}

class Multiplication (val first: Element, val second: Element): Element {
	
	override fun getResult(): Int {
		return first.getResult() * second.getResult()
	}
	
	override fun getMin(): Int {
		return first.getMin() * second.getMin()
	}
	
	override fun getMax(): Int {
		return first.getMax() * second.getMax()
	}
}