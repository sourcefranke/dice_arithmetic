package com.github.sourcefranke.dicearithmetic.core.elements

class Number (private val number: Int): Element {

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
