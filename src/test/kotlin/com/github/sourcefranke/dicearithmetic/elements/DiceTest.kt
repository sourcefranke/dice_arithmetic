package com.github.sourcefranke.dicearithmetic.elements

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class DiceTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 50, 100])
    fun `test minimum and maximum values`(diceType: Int) {
        val dice = Dice(diceType)

        assertThat(dice.getMin()).isEqualTo(1)
        assertThat(dice.getMax()).isEqualTo(diceType)
    }

    @Test
    fun `test result list`() {
        assertThat(Dice(6).getResultList(50))
                .containsOnly(1, 2, 3, 4, 5, 6)
                .hasSize(50)
    }
}
