package com.github.sourcefranke.rpgdicearith

import kotlin.random.Random

fun rollDiceResult(formula: String): Int {
	return formula
		.filter{c -> !c.isWhitespace()}
		.split("+")
		.flatMap{string -> getElement(string)}
		.sumBy { e -> e.getResult() }
}

fun getElement(subString: String): List<Element> {
	when {
		subString.matches(Regex("[d][0-9]+"))		-> return listOf(Dice(subString.substring(1)))
		subString.matches(Regex("[0-9]+"))			-> return listOf(Number(subString))
		subString.matches(Regex("[0-9]+[d][0-9]+"))	-> return getMultipleDices(subString)
		else										-> throw Exception()
	}
}

fun getMultipleDices(string: String): List<Element> {
	var list = string.split("d")
	
	if(list.size != 2) throw Exception()
	
	var amount = list.get(0).toInt()
	var diceType = list.get(1).toInt()
	
	return generateSequence { Dice(diceType) }.take(amount).toList()
}

interface Element {
	fun getResult(): Int
}

class Number (val number: Int): Element {
	
	constructor (numberString: String) : this(numberString.toInt())
	
	override fun getResult(): Int {
		return number
	}
}

class Dice (val max: Int): Element {
	
	constructor (maxString: String) : this(maxString.toInt())
	
	override fun getResult(): Int {
		return Random.nextInt(max) + 1
	}
}