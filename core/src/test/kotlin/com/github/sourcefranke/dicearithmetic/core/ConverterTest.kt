package com.github.sourcefranke.dicearithmetic.core

import assertk.all
import assertk.assertThat
import assertk.assertions.*
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class ConverterTest : ShouldSpec({
	context("toElement") {
		should("4d2") {
			random = Random(1)

			val dice = toElement("4d2")

			dice.min() shouldBe 4
			dice.max() shouldBe 8
			assertThat (dice.resultList(5)).all {
				hasSize(5)
				containsExactly(7, 7, 5, 7, 6)
			}
		}

		should("2d3*6") {
			random = Random(1)

			val dice = toElement("2d3*6")

			dice.min() shouldBe 12
			dice.max() shouldBe 36

			assertThat (dice.resultList(5)).all {
				hasSize(5)
				containsExactly(12, 18, 24, 18, 24)
			}
		}

		should("3+3") {
			val dice = toElement("3+3")

			dice.min() shouldBe 6
			dice.max() shouldBe 6
			dice.roll() shouldBe 6
		}

		should("3+3+3") {
			val dice = toElement("3+3+3")

			dice.min() shouldBe 9
			dice.max() shouldBe 9
			dice.roll() shouldBe 9
		}

		should("3*4") {
			val dice = toElement("3*4")

			dice.min() shouldBe 12
			dice.max() shouldBe 12
			dice.roll() shouldBe 12
		}

		should("3*4*5") {
			val dice = toElement("3*4*5")

			dice.min() shouldBe 60
			dice.max() shouldBe 60
			dice.roll() shouldBe 60
		}
	}
})
