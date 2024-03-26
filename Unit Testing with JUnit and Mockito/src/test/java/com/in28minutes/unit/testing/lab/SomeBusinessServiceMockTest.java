package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.in28minutes.unit.testing.lab.others.SomeDataService;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessServiceMockTest {

	@Mock
	SomeDataService dataService;

	@InjectMocks
	SomeBusinessService businessService;

	@Test
	public void testCalculateSum() {
		when(dataService.retrieveData()).thenReturn(new int[] { 10, 20 });
		assertEquals(30, businessService.calculateSum());
	}

	@Test
	public void testCalculateSum_empty() {
		when(dataService.retrieveData()).thenReturn(new int[] {});
		assertEquals(0, businessService.calculateSum());
	}

}
