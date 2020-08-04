package com.github.sourcefranke.dicearithmetic

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class DiceArithmeticTest {

	@Test
	fun `test getElement with '4d2'`() {
		val dice = getElement("4d2")

		assertThat(dice.getMin()).isEqualTo(4)
		assertThat(dice.getMax()).isEqualTo(8)

		assertThat(dice.getResultList(50))
				.containsOnly(4, 5, 6, 7, 8)
				.hasSize(50)
	}

	@Test
	fun `test getElement with '2d6*3'`() {
		val dice = getElement("2d3*6")

		assertThat(dice.getMin()).isEqualTo(12)
		assertThat(dice.getMax()).isEqualTo(36)

		assertThat(dice.getResultList(50))
				.containsOnly(12, 18, 24, 30, 36)
				.hasSize(50)
	}

	@Test
	fun `"rollDiceResult" returns the final result for a dice formula`() {
		assertThat(rollDiceResult("7")).isEqualTo(7)
		assertThat(rollDiceResult("4 + 7")).isEqualTo(11)
		assertThat(rollDiceResult("4d2")).isGreaterThanOrEqualTo(4).isLessThanOrEqualTo(8)
	}

	@Test
	fun `"shorten" eliminates all whitespace characters`() {
		assertThat(shorten(" d   6")).isEqualTo("d6")
		assertThat(shorten("2 d6  + 5 ")).isEqualTo("2d6+5")
		assertThat(shorten(" 2	d6")).isEqualTo("2d6")
	}

	@Test
	fun `"isSingleDice" returns True for a dice`() {
		assertThat(isSingleDice("d6")).isTrue()
	}

	@Test
	fun `"isSingleDice" returns False for everything else`() {
		assertThat(isSingleDice("6")).isFalse()
		assertThat(isSingleDice("3d6")).isFalse()
		assertThat(isSingleDice("3+d6")).isFalse()
		assertThat(isSingleDice("d6+3")).isFalse()
		assertThat(isSingleDice("d 6")).isFalse()
		assertThat(isSingleDice("d")).isFalse()
	}
}