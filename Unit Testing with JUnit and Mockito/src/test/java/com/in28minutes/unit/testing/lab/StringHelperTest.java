package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class StringHelperTest {

	StringHelper helper = new StringHelper();

	@Test
	void testReplaceAInFirst2Positions() {
		assertEquals(helper.replaceAInFirst2Positions("ABCD"), "BCD");
		assertEquals(helper.replaceAInFirst2Positions("AACD"), "CD");
		assertEquals(helper.replaceAInFirst2Positions("BACD"), "BCD");
		assertEquals(helper.replaceAInFirst2Positions("AAAA"), "AA");
		assertEquals(helper.replaceAInFirst2Positions("MNAA"), "MNAA");
		assertEquals(helper.replaceAInFirst2Positions(""), "");
		assertEquals(helper.replaceAInFirst2Positions("A"), "");
		assertEquals(helper.replaceAInFirst2Positions("AA"), "");
		assertEquals(helper.replaceAInFirst2Positions("B"), "B");
		assertEquals(helper.replaceAInFirst2Positions("BC"), "BC");
	}

	@Test
	void testAreFirstTwoAndLastTwoCharsTheSameWithLenghtLessOf2Characters() {
		assertTrue(helper.areFirstTwoAndLastTwoCharsTheSame("ABCAB"));
		assertFalse(helper.areFirstTwoAndLastTwoCharsTheSame("ABCDEBA"));
		assertTrue(helper.areFirstTwoAndLastTwoCharsTheSame("AAA"));
		assertFalse(helper.areFirstTwoAndLastTwoCharsTheSame(""));
		assertFalse(helper.areFirstTwoAndLastTwoCharsTheSame("A"));
		assertTrue(helper.areFirstTwoAndLastTwoCharsTheSame("AB"));
	}
}