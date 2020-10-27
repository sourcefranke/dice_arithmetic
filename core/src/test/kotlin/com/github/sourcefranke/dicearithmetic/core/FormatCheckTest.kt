package com.github.sourcefranke.dicearithmetic.core

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

class FormatCheckTest : ShouldSpec({
    context("FormatCheck") {
        should("isAddition") {
            listOf(
                    "42" to false,
                    "d6" to false,
                    "2d6" to false,
                    "d6+3" to true,
                    "3+d6" to true,
                    "d6*3" to false,
                    "3*d6" to false,
            ).forEach { (input: String, result: Boolean) ->
                isAddition(input) shouldBe result
            }
        }

        should("isMultiplication") {
            listOf(
                    "42" to false,
                    "d6" to false,
                    "2d6" to false,
                    "d6+3" to false,
                    "3+d6" to false,
                    "d6*3" to true,
                    "3*d6" to true,
            ).forEach { (input: String, result: Boolean) ->
                isMultiplication(input) shouldBe result
            }
        }

        should("isMultipleDices") {
            listOf(
                    "42" to false,
                    "d6" to false,
                    "2d6" to true,
                    "d6+3" to false,
                    "3+d6" to false,
                    "d6*3" to false,
                    "3*d6" to false,
            ).forEach { (input: String, result: Boolean) ->
                isMultipleDices(input) shouldBe result
            }
        }

        should("isDice") {
            listOf(
                    "42" to false,
                    "d6" to true,
                    "2d6" to false,
                    "d6+3" to false,
                    "3+d6" to false,
                    "d6*3" to false,
                    "3*d6" to false,
            ).forEach { (input: String, result: Boolean) ->
                isDice(input) shouldBe result
            }
        }

        should("isNumber") {
            listOf(
                    "42" to true,
                    "d6" to false,
                    "2d6" to false,
                    "d6+3" to false,
                    "3+d6" to false,
                    "d6*3" to false,
                    "3*d6" to false,
            ).forEach { (input: String, result: Boolean) ->
                isNumber(input) shouldBe result
            }
        }
    }
})
