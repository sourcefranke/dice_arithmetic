package com.github.sourcefranke.rpgdicearith

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.*

class DiceArithmeticTest {

	@Test
	fun `"rollDiceResult" returns the final result for a dice formula`() {
		assertThat(rollDiceResult("7")).isEqualTo(7)
		assertThat(rollDiceResult("4 + 7")).isEqualTo(11)
		assertThat(rollDiceResult("4d2")).isGreaterThanOrEqualTo(4).isLessThanOrEqualTo(8)
	}

	@Test
	fun `"shorten" eleminates all whitespace characters`() {
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
