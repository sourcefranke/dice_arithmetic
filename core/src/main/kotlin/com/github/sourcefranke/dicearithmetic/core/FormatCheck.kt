package com.github.sourcefranke.dicearithmetic.core

typealias FormatCheck = (String) -> Boolean

val isAddition: FormatCheck = { check(it,".*\\+.*") }
val isMultiplication: FormatCheck = { check(it,".*\\*.*") }
val isMultipleDices: FormatCheck = { check(it,"[0-9]+[d][0-9]+") }
val isDice: FormatCheck = { check(it,"[d][0-9]+") }
val isNumber: FormatCheck = { check(it,"[0-9]+") }

fun check(s: String, regex: String) = s.matches(Regex(regex))
