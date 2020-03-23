package com.github.sourcefranke.rpgdicearith;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class RpgDiceArithmeticsTest {
	
	@ParameterizedTest
	@CsvFileSource(resources = "/testcases.csv")
	public void rollDiceResult(String formula, int lower, int upper) {
		Integer result =  com.github.sourcefranke.rpgdicearith.RpgDiceArithmeticsKt.rollDiceResult(formula);
		assertThat(result).isGreaterThanOrEqualTo(lower).isLessThanOrEqualTo(upper);
	}
	
	@Test
	public void getElement_Number() {
		List<Element> list = RpgDiceArithmeticsKt.getElement("5");
		
		assertThat(list).hasSize(1);
		Element e = list.get(0);
		assertThat(e.getResult()).isEqualTo(5);
	}
	
	@Test
	public void getElement_Dice() {
		List<Element> list = RpgDiceArithmeticsKt.getElement("d5");
		
		assertThat(list).hasSize(1);
		Element e = list.get(0);
		assertThat(e.getResult()).isGreaterThanOrEqualTo(1).isLessThanOrEqualTo(5);
	}
}
