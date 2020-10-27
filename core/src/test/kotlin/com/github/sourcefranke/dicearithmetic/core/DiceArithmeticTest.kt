package com.github.sourcefranke.dicearithmetic.core

import assertk.all
import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.hasSize
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class DiceArithmeticTest : ShouldSpec({
	context("rollDice") {
		should("7") {
			val result = rollDice("7")
			result.formula shouldBe "7"
			result.max shouldBe 7
			result.min shouldBe 7
			result.resultList shouldBe listOf(7)
		}

		should("4 + 7") {
			val result = rollDice("4 + 7")
			result.formula shouldBe "4 + 7"
			result.max shouldBe 11
			result.min shouldBe 11
			result.resultList shouldBe listOf(11)
		}

		should("4d2 + 1") {
			random = Random(1)
			val result = rollDice("4d2 + 1", 5)

			result.formula shouldBe "4d2 + 1"
			result.max shouldBe 9
			result.min shouldBe 5
			assertThat(result.resultList).all {
				hasSize(5)
				containsOnly(8, 8, 6, 8, 7)
			}
		}
	}

	context("shorten") {
		listOf(
				" d   6" to "d6",
				"2 d6  + 5 " to "2d6+5",
				" 2   d6" to "2d6",
				"2d6" to "2d6"
		).forEach { (input, result) ->
			shorten(input) shouldBe result
		}
	}
})
