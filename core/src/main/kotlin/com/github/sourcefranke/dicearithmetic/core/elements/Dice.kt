package com.github.sourcefranke.dicearithmetic.core.elements

import kotlin.random.Random

class Dice (private val maxInt: Int): Element {

    constructor (maxString: String) : this(maxString.toInt())

    override fun getResult(): Int {
        return Random.nextInt(maxInt) + 1
    }

    override fun getMin(): Int {
        return 1
    }

    override fun getMax(): Int {
        return maxInt
    }
}
