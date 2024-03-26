package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class StudentHelperTest {

	private static final int UPPER_LIMIT_FOR_NOT_MATHS = 80;
	private static final int UPPER_LIMIT_FOR_MATHS = UPPER_LIMIT_FOR_NOT_MATHS + 10;
	private static final int LOWER_LIMIT_FOR_NOT_MATHS = 51;
	private static final int LOWER_LIMIT_FOR_MATHS = LOWER_LIMIT_FOR_NOT_MATHS + 10;

	private static final boolean MATHS = true;

	private static final boolean NOT_MATHS = false;

	StudentHelper helper = new StudentHelper();

	@Test
	public void testIsGradeB() {
		assertFalse(helper.isGradeB(LOWER_LIMIT_FOR_NOT_MATHS - 1, NOT_MATHS));
		assertTrue(helper.isGradeB(LOWER_LIMIT_FOR_NOT_MATHS, NOT_MATHS));
		assertTrue(helper.isGradeB(LOWER_LIMIT_FOR_NOT_MATHS + 1, NOT_MATHS));
		assertTrue(helper.isGradeB(UPPER_LIMIT_FOR_NOT_MATHS, NOT_MATHS));
		assertFalse(helper.isGradeB(UPPER_LIMIT_FOR_NOT_MATHS + 1, NOT_MATHS));
		assertFalse(helper.isGradeB(LOWER_LIMIT_FOR_NOT_MATHS - 1, MATHS));
		assertTrue(helper.isGradeB(LOWER_LIMIT_FOR_NOT_MATHS, MATHS));
		assertTrue(helper.isGradeB(LOWER_LIMIT_FOR_MATHS, MATHS));
		assertTrue(helper.isGradeB(LOWER_LIMIT_FOR_MATHS + 1, MATHS));
		assertTrue(helper.isGradeB(UPPER_LIMIT_FOR_NOT_MATHS, MATHS));
		assertFalse(helper.isGradeB(UPPER_LIMIT_FOR_MATHS + 1, MATHS));
	}

}
