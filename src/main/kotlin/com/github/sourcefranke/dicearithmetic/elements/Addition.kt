package com.github.sourcefranke.dicearithmetic.elements

class Addition (private val first: Element, private val second: Element): Element {

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
