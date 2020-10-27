package com.github.sourcefranke.dicearithmetic.core

import assertk.all
import assertk.assertThat
import assertk.assertions.containsExactly
import assertk.assertions.hasSize
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlin.random.Random

class ElementsTest : ShouldSpec({
    context("Dice") {
        should("min/max") {
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 50, 100).forEach { input ->
                val dice = Dice(input)
                dice.min() shouldBe 1
                dice.max() shouldBe input
            }
        }

        should("resultList") {
            assertThat (Dice(6, Random(1)).resultList(7)).all {
                hasSize(7)
                containsExactly(4, 1, 5, 1, 4, 6, 2)
            }
        }
    }

    context("Addition") {
        // given
        val first = mockk<Element>()
        every { first.min() } returns 3
        every { first.roll() } returns 4
        every { first.max() } returns 5

        val second = mockk<Element>()
        every { second.min() } returns 6
        every { second.roll() } returns 7
        every { second.max() } returns 8

        // when
        val element = Addition(listOf(first, second))

        // then
        element.min() shouldBe 9
        element.roll() shouldBe 11
        element.max() shouldBe 13
    }

    context("Multiplication") {
        // given
        val first = mockk<Element>()
        every { first.min() } returns 3
        every { first.roll() } returns 4
        every { first.max() } returns 5

        val second = mockk<Element>()
        every { second.min() } returns 6
        every { second.roll() } returns 7
        every { second.max() } returns 8

        // when
        val element = Multiplication(listOf(first, second))

        // then
        element.min() shouldBe 18
        element.roll() shouldBe 28
        element.max() shouldBe 40
    }
})
