package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.in28minutes.unit.testing.lab.others.SomeDataService;

public class SomeBusinessServiceStubTest {

	@Test
	public void testCalculateSum() {

		// Create stub SomeDataService implementation with hardcoded implementation for
		// retrieveData
		SomeDataService dataServiceStub = new SomeDataService() {
			public int[] retrieveData() {
				return new int[] { 10, 20 };
			}
		};

		// Create SomeBusinessService using the stub SomeDataService implementation
		SomeBusinessService someBusinessService = new SomeBusinessService(dataServiceStub);

		// Write your test scenario
		assertEquals(30, someBusinessService.calculateSum());
	}

	@Test
	public void testCalculateSum_empty() {

		// Create stub SomeDataService implementation with hardcoded implementation for
		// retrieveData
		SomeDataService dataServiceStub = new SomeDataService() {
			public int[] retrieveData() {
				return new int[] {};
			}
		};

		// Create SomeBusinessService using the stub SomeDataService implementation
		SomeBusinessService someBusinessService = new SomeBusinessService(dataServiceStub);

		// Write your test scenario
		assertEquals(0, someBusinessService.calculateSum());
	}

}
