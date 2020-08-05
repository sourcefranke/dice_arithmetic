package com.github.sourcefranke.dicearithmetic.core.elements

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class AdditionTest {

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

        val element = Addition(first, second)

        // Evaluate output
        assertThat(element.getMin()).isEqualTo(9)
        assertThat(element.getResult()).isEqualTo(11)
        assertThat(element.getMax()).isEqualTo(13)
    }
}
