package com.in28minutes.unit.testing.lab;

import java.util.Arrays;

import com.in28minutes.unit.testing.lab.others.SomeDataService;

public class SomeBusinessService {

	private SomeDataService someData;

	public SomeBusinessService(SomeDataService someData) {
		super();
		this.someData = someData;
	}

	public int calculateSum() {
		return Arrays.stream(someData.retrieveData())
				.reduce(Integer::sum).orElse(0);
	}
}
