package com.github.sourcefranke.dicearithmetic.core.elements

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NumberTest {

    @ParameterizedTest
    @CsvSource(
            "'1323', 1323",
            "'3463', 3463",
            "'-25', -25"
    )
    fun `test the String transforming constructor`(input: String, output: Int) {
        val number = Number(input)

        assertThat(number.getMin()).isEqualTo(output)
        assertThat(number.getResult()).isEqualTo(output)
        assertThat(number.getMax()).isEqualTo(output)
    }
}
