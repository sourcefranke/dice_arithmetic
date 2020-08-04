package com.github.sourcefranke.dicearithmetic.elements

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class MultiplicationTest {

    @Test
    fun `test all the different methods once`() {
        // Initialize input parameters
        val first = mock(Element::class.java)
        `when`(first.getMin()).thenReturn(3)
        `when`(first.getResult()).thenReturn(4)
        `when`(first.getMax()).thenReturn(5)

        val second = mock(Element::class.java)
        `when`(second.getMin()).thenReturn(6)
        `when`(second.getResult()).thenReturn(7)
        `when`(second.getMax()).thenReturn(8)

        val element = Multiplication(first, second)

        // Evaluate output
        assertThat(element.getMin()).isEqualTo(18)
        assertThat(element.getResult()).isEqualTo(28)
        assertThat(element.getMax()).isEqualTo(40)
    }
}
