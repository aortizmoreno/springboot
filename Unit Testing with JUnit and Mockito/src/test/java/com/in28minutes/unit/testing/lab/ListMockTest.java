package com.in28minutes.unit.testing.lab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ListMockTest {

	List<String> mock = mock(List.class);

	@Test
	public void simpleMocking() {
		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	public void mockDefaultValues() {
		ArrayList<String> arrayListMock = mock(ArrayList.class);
		assertNull(arrayListMock.get(0));
		assertEquals(0, arrayListMock.size());
		arrayListMock.add("test1");
		arrayListMock.add("test2");
		assertEquals(0, arrayListMock.size());
	}

	@Test
	public void mockingWithMultipleReturnValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	public void verificationBasics() {
		String value1 = mock.get(0);
		String value2 = mock.get(1);
		verify(mock).get(0);
		verify(mock, times(2)).get(anyInt());
		verify(mock, atLeast(1)).get(anyInt());
		verify(mock, atLeastOnce()).get(anyInt());
		verify(mock, atMost(2)).get(anyInt());
		verify(mock, never()).get(2);
	}
}
